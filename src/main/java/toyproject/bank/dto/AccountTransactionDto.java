package toyproject.bank.dto;

import lombok.Data;
import toyproject.bank.domain.AccountTransaction;
import toyproject.bank.domain.TransactionType;

import java.time.LocalDateTime;

@Data
public class AccountTransactionDto {

    private Long id;
    private String accountNumber;
    private int amount;
    private int balance;
    private TransactionType type;
    private String content;
    private LocalDateTime createdDate;

    public AccountTransactionDto(Long id, int amount, int balance, TransactionType type, String content) {
        this.id = id;
        this.amount = amount;
        this.balance = balance;
        this.type = type;
        this.content = content;
    }

    public AccountTransactionDto(AccountTransaction transaction) {
        this.id = transaction.getId();
        this.accountNumber = transaction.getAccount().getId();
        this.amount = transaction.getAmount();
        this.balance = transaction.getBalance();
        this.type = transaction.getType();
        this.content = transaction.getContent();
        this.createdDate = transaction.getTransactionDate();
    }
}
