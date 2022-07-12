package toyproject.bank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardTransaction {

    @Id @GeneratedValue
    @Column(name = "card_transaction_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(updatable = false)
    private int amount;
    @Column(updatable = false)
    private int installment;
    @Column(updatable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;
    @Column(updatable = false)
    private LocalDateTime transactionDate;

    public CardTransaction(Card card, int amount, int installment, String content) {
        this.card = card;
        this.amount = amount;
        this.installment = installment;
        this.content = content;

        this.status = ApprovalStatus.WAIT;
        this.transactionDate = LocalDateTime.now();
    }

    //==비즈니스 로직==//
    public void cancel() {
        this.status = ApprovalStatus.CANCEL;
    }

    public void approve() {
        this.status = ApprovalStatus.APPROVAL;
    }
}
