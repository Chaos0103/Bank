package toyproject.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Account;
import toyproject.bank.domain.AccountTransaction;
import toyproject.bank.dto.AccountTransactionDto;
import toyproject.bank.exception.NoSuchException;
import toyproject.bank.repository.AccountRepository;
import toyproject.bank.repository.AccountTransactionRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository atRepository;
    private final AccountRepository accountRepository;

    /**
     * 거래
     */
    public Long transaction(String accountId, AccountTransactionDto trDto) {
        Account account = getAccount(accountId);
        AccountTransaction savedAt = atRepository.save(new AccountTransaction(account, trDto.getAmount(), trDto.getType(), trDto.getContent()));
        return savedAt.getId();
    }

    private Account getAccount(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 계좌입니다.");
        });
    }
}
