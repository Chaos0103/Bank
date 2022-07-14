package toyproject.bank.controller.form;

import lombok.Data;

@Data
public class PaymentRequest {

    private String cardNumber;
    private String expirationDate;
    private String cvc;

    private int amount;
    private int installment;
    private String content;

    private Long paymentId;
}
