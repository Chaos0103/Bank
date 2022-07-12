package toyproject.bank.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.ApprovalStatus;
import toyproject.bank.domain.Card;
import toyproject.bank.domain.CardTransaction;
import toyproject.bank.domain.CardType;
import toyproject.bank.dto.CardDto;
import toyproject.bank.dto.CardTransactionDto;
import toyproject.bank.repository.CardRepository;
import toyproject.bank.repository.CardTransactionRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CardTransactionServiceTest {

    @Autowired CardTransactionService cardTransactionService;
    @Autowired CardTransactionRepository cardTransactionRepository;
    @Autowired CardRepository cardRepository;

    @Test
    void payment() {
        Card card = new Card("1234123412341234", null, CardType.CREDIT, 1000000);
        Card savedCard = cardRepository.save(card);

        CardDto cardDto = new CardDto(card.getId(), card.getExpirationDate(), card.getCvc(), card.getType(), card.getLimitAmount());
        CardTransactionDto cardTransactionDto = new CardTransactionDto(null, 10000, null, 0, null);
        Long paymentId = cardTransactionService.payment(cardDto, cardTransactionDto);

        Optional<CardTransaction> findCardTransaction = cardTransactionRepository.findById(paymentId);
        assertThat(findCardTransaction).isPresent();
    }

    @Test
    void paymentCancel() {
        CardTransaction cardTransaction = new CardTransaction(null, 10000, 0, "test");
        CardTransaction savedCardTransaction = cardTransactionRepository.save(cardTransaction);

        Long id = cardTransactionService.paymentCancel(savedCardTransaction.getId());

        CardTransaction find = cardTransactionRepository.findById(id).get();
        assertThat(find.getStatus()).isEqualTo(ApprovalStatus.CANCEL);
    }

    @Test
    void paymentApproval() {
        CardTransaction cardTransaction = new CardTransaction(null, 10000, 0, "test");
        CardTransaction savedCardTransaction = cardTransactionRepository.save(cardTransaction);

        Long id = cardTransactionService.paymentApproval(savedCardTransaction.getId());

        CardTransaction find = cardTransactionRepository.findById(id).get();
        assertThat(find.getStatus()).isEqualTo(ApprovalStatus.APPROVAL);
    }
}