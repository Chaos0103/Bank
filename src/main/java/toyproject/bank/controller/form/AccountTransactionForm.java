package toyproject.bank.controller.form;

import lombok.Data;

@Data
public class AccountTransactionForm {

    private String accountNumber;
    private int amount;
    private String content;
}
