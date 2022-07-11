package toyproject.bank.dto;

import lombok.Data;
import toyproject.bank.domain.TransactionType;

@Data
public class AccountTransactionDto {

    private Long id;
    private int amount;
    private int balance;
    private TransactionType type;
    private String content;

    public AccountTransactionDto(Long id, int amount, int balance, TransactionType type, String content) {
        this.id = id;
        this.amount = amount;
        this.balance = balance;
        this.type = type;
        this.content = content;
    }
}
