package toyproject.bank.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyproject.bank.controller.form.PaymentRequest;
import toyproject.bank.dto.CardDto;
import toyproject.bank.dto.CardTransactionDto;
import toyproject.bank.service.CardTransactionService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentApiController {

    private final CardTransactionService cardTransactionService;

    @PostMapping("/payment")
    public String payment(@RequestBody PaymentRequest paymentRequest) {
        CardDto cardDto = new CardDto(paymentRequest.getCardNumber(), paymentRequest.getExpirationDate(), paymentRequest.getCvc(), null, 0);
        CardTransactionDto cardTransactionDto = new CardTransactionDto(null, paymentRequest.getAmount(), null, paymentRequest.getInstallment(), paymentRequest.getContent());
        cardTransactionService.payment(cardDto, cardTransactionDto);
        return "payment";
    }

    @PostMapping("/payment/cancel")
    public String cancel(@RequestBody PaymentRequest paymentRequest) {
        cardTransactionService.paymentCancel(paymentRequest.getPaymentId());
        return "payment cancel";
    }

}
