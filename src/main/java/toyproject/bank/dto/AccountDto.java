package toyproject.bank.dto;

import lombok.Data;
import toyproject.bank.domain.AccountType;

@Data
public class AccountDto {

    private String id;
    private int balance;
    private AccountType accountType;

    public AccountDto(String id, int balance, AccountType accountType) {
        this.id = id;
        this.balance = balance;
        this.accountType = accountType;
    }
}
