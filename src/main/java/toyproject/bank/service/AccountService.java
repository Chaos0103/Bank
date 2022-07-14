package toyproject.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Account;
import toyproject.bank.domain.Client;
import toyproject.bank.dto.AccountDto;
import toyproject.bank.exception.NoSuchException;
import toyproject.bank.repository.AccountRepository;
import toyproject.bank.repository.ClientRepository;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    /**
     * 신규발급
     */
    public String create(Long clientId, AccountDto accountDto) {
        Account savedAccount = accountRepository.save(new Account(getAccountNumber(), getClient(clientId), accountDto.getBalance(), accountDto.getAccountType()));
        return savedAccount.getId();
    }

    /**
     * 재발급
     */
    public String reissue(Long clientId, String oldAccountId, AccountDto accountDto) {
        delete(oldAccountId);
        Account savedAccount = accountRepository.save(new Account(getAccountNumber(), getClient(clientId), accountDto.getBalance(), accountDto.getAccountType()));
        return savedAccount.getId();
    }

    /**
     * 삭제
     */
    public void delete(String accountId) {
        accountRepository.delete(getAccount(accountId));
    }

    private Account getAccount(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 계좌입니다.");
        });
    }

    private String getAccountNumber() {
        Random rand = new Random();
        while (true) {
            String newAccountNumber = "";
            for (int i = 0; i < 14; i++) {
                newAccountNumber += Integer.toString(rand.nextInt(10));
            }
            Optional<Account> findAccount = accountRepository.findById(newAccountNumber);
            if (findAccount.isPresent()) {
                continue;
            }
            return newAccountNumber;
        }
    }

    private Client getClient(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });
    }
}
