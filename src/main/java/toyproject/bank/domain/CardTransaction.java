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
    private LocalDateTime transactionDate;
    @Column(updatable = false)
    private int amount;
    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private ApprovalStatus status;
    @Column(updatable = false)
    private int installment;
    @Column(updatable = false)
    private String content;
}
