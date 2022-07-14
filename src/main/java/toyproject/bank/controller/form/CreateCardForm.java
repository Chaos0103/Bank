package toyproject.bank.controller.form;

import lombok.Data;
import toyproject.bank.domain.CardType;

@Data
public class CreateCardForm {

    private String accountNumber;
    private CardType cardType;
    private int limitAmount;
}
