package toyproject.bank.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.*;
import toyproject.bank.dto.CardDto;
import toyproject.bank.repository.AccountRepository;
import toyproject.bank.repository.CardRepository;
import toyproject.bank.repository.ClientRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CardServiceTest {

    @Autowired CardService cardService;
    @Autowired CardRepository cardRepository;
    @Autowired AccountRepository accountRepository;
    @Autowired ClientRepository clientRepository;

    @Test
    void create() {
        CardDto cardDto = new CardDto(null, null, null, CardType.CREDIT, 1000000);
        Account savedAccount = getAccount();

        String cardNumber = cardService.create(savedAccount.getId(), cardDto);

        Optional<Card> findCard = cardRepository.findById(cardNumber);
        assertThat(findCard).isPresent();
    }

    @Test
    void delete() {
        Card card = new Card("1234123412341234", getAccount(), CardType.CREDIT, 1000000);
        Card savedCard = cardRepository.save(card);

        cardService.delete(savedCard.getId());

        Optional<Card> findCard = cardRepository.findById("1234123412341234");
        assertThat(findCard).isEmpty();
    }

    @Test
    void reissue() {
        CardDto cardDto = new CardDto(null, null, null, CardType.CREDIT, 1000000);
        Account account = getAccount();
        Card card = new Card("1234123412341234", account, CardType.CREDIT, 1000000);
        cardRepository.save(card);

        String newCardId = cardService.reissue(account.getId(), "1234123412341234", cardDto);

        Optional<Card> oldCard = cardRepository.findById("1234123412341234");
        Optional<Card> newCard = cardRepository.findById(newCardId);
        assertThat(oldCard).isEmpty();
        assertThat(newCard).isPresent();
    }

    private Account getAccount() {
        return accountRepository.save(new Account("89200352504362", getClient(), 10000, AccountType.SALARY));
    }

    private Client getClient() {
        return clientRepository.save(new Client("tester", null, null, null, null));
    }
}