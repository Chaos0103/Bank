package toyproject.bank.dto;

import lombok.Data;
import toyproject.bank.domain.ApprovalStatus;
import toyproject.bank.domain.CardTransaction;

import java.time.LocalDateTime;

@Data
public class CardTransactionDto {

    private Long id;
    private String cardNumber;
    private int amount;
    private ApprovalStatus status;
    private int installment;
    private String content;
    private LocalDateTime createdDate;

    public CardTransactionDto(Long id, int amount, ApprovalStatus status, int installment, String content) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.installment = installment;
        this.content = content;
    }

    public CardTransactionDto(CardTransaction transaction) {
        this.id = transaction.getId();
        this.cardNumber = transaction.getCard().getId();
        this.amount = transaction.getAmount();
        this.status = transaction.getStatus();
        this.installment = transaction.getInstallment();
        this.content = transaction.getContent();
        this.createdDate = transaction.getTransactionDate();
    }
}
