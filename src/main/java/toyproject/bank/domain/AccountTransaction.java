package toyproject.bank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransaction {

    @Id @GeneratedValue
    @Column(name = "account_transaction_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(updatable = false)
    private int amount;
    @Column(updatable = false)
    private int balance;
    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private TransactionType type;
    @Column(updatable = false)
    private String content;
    @Column(updatable = false)
    private LocalDateTime transactionDate;

    public AccountTransaction(Account account, int amount, TransactionType type, String content) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.content = content;
        this.transactionDate = LocalDateTime.now();
        this.balance = getAccountBalance(account, amount);
    }

    //==편의 메서드==//
    public int getAccountBalance(Account account, int amount) {
        if (this.type == TransactionType.OUT) {
            return account.removeBalance(amount);
        } else {
            return account.addBalance(amount);
        }
    }
}
