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
public class Account {

    @Id
    @Column(name = "account_id", length = 14)
    private String id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    private int balance;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private AccountType type;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    public Account(String id, Client client, int balance, AccountType type) {
        this.id = id;
        this.client = client;
        this.balance = balance;
        this.type = type;
    }
}
