package toyproject.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Card;
import toyproject.bank.domain.CardTransaction;
import toyproject.bank.dto.CardDto;
import toyproject.bank.dto.CardTransactionDto;
import toyproject.bank.exception.NoSuchException;
import toyproject.bank.exception.PaymentException;
import toyproject.bank.repository.CardRepository;
import toyproject.bank.repository.CardTransactionRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CardTransactionService {

    private final CardTransactionRepository ctRepository;
    private final CardRepository cardRepository;

    /**
     * 결제
     */
    public Long payment(CardDto cardDto, CardTransactionDto cardTransactionDto) {
        //카드 데이터 확인, cvc, expirationDate
        Card card = findCard(cardDto.getId());
        validation(card, cardDto);

        //결제
        CardTransaction cardTransaction = new CardTransaction(card, cardTransactionDto.getAmount(), cardTransactionDto.getInstallment(), cardTransactionDto.getContent());
        CardTransaction savedCardTransaction = ctRepository.save(cardTransaction);
        return savedCardTransaction.getId();
    }

    /**
     * 결제취소
     */
    public Long paymentCancel(Long id) {
        CardTransaction cardTransaction = findCardTransaction(id);
        cardTransaction.cancel();
        return cardTransaction.getId();
    }

    /**
     * 결제승인
     */
    public Long paymentApproval(Long id) {
        CardTransaction cardTransaction = findCardTransaction(id);
        cardTransaction.approve();
        return cardTransaction.getId();
    }

    private CardTransaction findCardTransaction(Long id) {
        return ctRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 결제내역입니다.");
        });
    }

    private Card findCard(String cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 카드입니다.");
        });
    }

    private void validation(Card findCard, CardDto cardDto) {
        checkCvcNumber(findCard.getCvc(), cardDto.getCvc());
        checkExpirationDate(findCard.getExpirationDate(), cardDto.getExpirationDate());
    }

    private void checkExpirationDate(String cardExpirationDate, String inputExpirationDate) {
        if (!cardExpirationDate.equals(inputExpirationDate)) {
            throw new PaymentException("유효기간이 일치하지 않습니다.");
        }
    }

    private void checkCvcNumber(String cardCvc, String inputCvc) {
        if (!cardCvc.equals(inputCvc)) {
            throw new PaymentException("CVC 번호가 일치하지 않습니다.");
        }
    }
}
