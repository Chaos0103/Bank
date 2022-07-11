package toyproject.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Account;
import toyproject.bank.domain.Card;
import toyproject.bank.domain.CardType;
import toyproject.bank.dto.CardDto;
import toyproject.bank.exception.NoSuchException;
import toyproject.bank.repository.AccountRepository;
import toyproject.bank.repository.CardRepository;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    /**
     * 발급
     */
    public String create(String accountId, CardDto cardDto) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 계좌입니다.");
        });

        Card savedCard = cardRepository.save(new Card(getCardNumber(), account, cardDto.getCardType(), cardDto.getLimitAmount()));
        return savedCard.getId();
    }

    /**
     * 취소
     */
    public void delete(String cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 카드입니다.");
        });
        cardRepository.delete(card);
    }

    /**
     * 재발급
     */
    public String reissue(String accountId, String oldCardId, CardDto cardDto) {
        delete(oldCardId);
        return create(accountId, cardDto);
    }


    private String getCardNumber() {
        Random rand = new Random();
        while (true) {
            String newCardNumber = "";
            for (int i = 0; i < 16; i++) {
                newCardNumber += Integer.toString(rand.nextInt(10));
            }
            Optional<Card> findCard = cardRepository.findById(newCardNumber);
            if (findCard.isPresent()) {
                continue;
            }
            return newCardNumber;
        }
    }
}
