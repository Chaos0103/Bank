package toyproject.bank.dto;

import lombok.Data;
import toyproject.bank.domain.ApprovalStatus;

@Data
public class CardTransactionDto {

    private Long id;
    private int amount;
    private ApprovalStatus status;
    private int installment;
    private String content;

    public CardTransactionDto(Long id, int amount, ApprovalStatus status, int installment, String content) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.installment = installment;
        this.content = content;
    }
}
