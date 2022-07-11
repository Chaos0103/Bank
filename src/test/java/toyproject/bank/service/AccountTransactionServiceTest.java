package toyproject.bank.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.*;
import toyproject.bank.dto.AccountTransactionDto;
import toyproject.bank.exception.NotEnoughException;
import toyproject.bank.repository.AccountRepository;
import toyproject.bank.repository.AccountTransactionRepository;
import toyproject.bank.repository.ClientRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountTransactionServiceTest {

    @Autowired AccountTransactionService atService;
    @Autowired AccountTransactionRepository atRepository;
    @Autowired AccountRepository accountRepository;
    @Autowired ClientRepository clientRepository;

    @Test
    void transaction_in() {
        AccountTransactionDto test = new AccountTransactionDto(null, 10000, 0, TransactionType.IN, "입금 테스트");
        Account account = getAccount("89200352504362");
        Long transactionId = atService.transaction(account.getId(), test);

        AccountTransaction findAt = atRepository.findById(transactionId).get();
        Account findAccount = accountRepository.findById(account.getId()).get();

        assertThat(findAt.getBalance()).isEqualTo(findAccount.getBalance());
        assertThat(findAccount.getBalance()).isEqualTo(20000);
    }

    @Test
    void transaction_out() {
        AccountTransactionDto test = new AccountTransactionDto(null, 10000, 0, TransactionType.OUT, "입금 테스트");
        Account account = getAccount("89200352504362");
        Long transactionId = atService.transaction(account.getId(), test);

        AccountTransaction findAt = atRepository.findById(transactionId).get();
        Account findAccount = accountRepository.findById(account.getId()).get();

        assertThat(findAt.getBalance()).isEqualTo(findAccount.getBalance());
        assertThat(findAccount.getBalance()).isEqualTo(0);
    }

    @Test
    void transaction_exception() {
        AccountTransactionDto test = new AccountTransactionDto(null, 20000, 0, TransactionType.OUT, "입금 테스트");
        Account account = getAccount("89200352504362");

        assertThrows(NotEnoughException.class, () -> {
            atService.transaction(account.getId(), test);
        });
    }

    @Test
    void accountTransaction() {
        Account fromAccount = getAccount("89200352504362");
        Account toAccount = getAccount("12341234123400");
        AccountTransactionDto test = new AccountTransactionDto(null, 5000, 0, null, "입금 테스트");

        atService.accountTransaction(fromAccount.getId(), toAccount.getId(), test);

        Account from = accountRepository.findById(fromAccount.getId()).get();
        Account to = accountRepository.findById(toAccount.getId()).get();

        assertThat(from.getBalance()).isEqualTo(5000);
        assertThat(to.getBalance()).isEqualTo(15000);
    }

    private Account getAccount(String id) {
        return accountRepository.save(new Account(id, getClient(), 10000, AccountType.SALARY));
    }

    private Client getClient() {
        return clientRepository.save(new Client("tester", null, null, null, null));
    }
}