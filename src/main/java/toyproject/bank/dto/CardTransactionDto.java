package toyproject.bank.dto;

import lombok.Data;
import toyproject.bank.domain.ApprovalStatus;

@Data
public class CardTransactionDto {

    private Long id;
    private int amount;
    private ApprovalStatus status;
    private int installment;
    private String Content;
}
