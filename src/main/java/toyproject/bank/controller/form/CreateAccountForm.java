package toyproject.bank.controller.form;

import lombok.Data;
import toyproject.bank.domain.AccountType;

@Data
public class CreateAccountForm {

    private Long clientId;
    private int balance;
    private AccountType accountType;
}
