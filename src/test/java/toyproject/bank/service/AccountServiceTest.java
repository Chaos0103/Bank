package toyproject.bank.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Account;
import toyproject.bank.domain.AccountType;
import toyproject.bank.domain.Client;
import toyproject.bank.dto.AccountDto;
import toyproject.bank.repository.AccountRepository;
import toyproject.bank.repository.ClientRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired ClientRepository clientRepository;

    @Test
    void create() {
        String accountNumber = accountService.create(getClient().getId(), new AccountDto(null, 10000, AccountType.SALARY));

        Optional<Account> findAccount = accountRepository.findById(accountNumber);

        assertThat(findAccount).isPresent();
    }

    @Test
    void delete() {
        Account savedAccount = accountRepository.save(new Account("89200352504362", getClient(), 10000, AccountType.SALARY));

        accountService.delete(savedAccount.getId());

        Optional<Account> findAccount = accountRepository.findById("89200352504362");
        assertThat(findAccount).isEmpty();
    }

    @Test
    void reissue() {
        Client client = getClient();
        accountRepository.save(new Account("89200352504362", client, 10000, AccountType.SALARY));

        String reAccountNumber = accountService.reissue(client.getId(), "89200352504362", new AccountDto(null, 10000, AccountType.SALARY));

        Optional<Account> oldAccount = accountRepository.findById("89200352504362");
        Optional<Account> newAccount = accountRepository.findById(reAccountNumber);

        assertThat(oldAccount).isEmpty();
        assertThat(newAccount).isPresent();
    }

    private Client getClient() {
        return clientRepository.save(new Client("tester", null, null, null, null));
    }
}