package toyproject.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Account;
import toyproject.bank.domain.AccountTransaction;
import toyproject.bank.domain.TransactionType;
import toyproject.bank.dto.AccountTransactionDto;
import toyproject.bank.exception.NoSuchException;
import toyproject.bank.repository.AccountRepository;
import toyproject.bank.repository.AccountTransactionRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository atRepository;
    private final AccountRepository accountRepository;

    /**
     * 입출금거래
     */
    public Long transaction(String accountId, AccountTransactionDto trDto) {
        Account account = getAccount(accountId);
        AccountTransaction savedAt = atRepository.save(new AccountTransaction(account, trDto.getAmount(), trDto.getType(), trDto.getContent()));
        return savedAt.getId();
    }

    /**
     * 계좌이체
     */
    public void accountTransaction(String fromAccountId, String toAccountId, AccountTransactionDto trDto) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        atRepository.save(new AccountTransaction(fromAccount, trDto.getAmount(), TransactionType.OUT, trDto.getContent()));
        atRepository.save(new AccountTransaction(toAccount, trDto.getAmount(), TransactionType.IN, trDto.getContent()));
    }

    /**
     * 거래내역조회
     */
    public List<AccountTransactionDto> search(String accountNumber) {
        return atRepository.findByAccountId(accountNumber).stream()
                .map(AccountTransactionDto::new)
                .toList();
    }

    private Account getAccount(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 계좌입니다.");
        });
    }
}
