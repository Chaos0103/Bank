package toyproject.bank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card {

    @Id
    @Column(name = "card_id", length = 16)
    private String id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(updatable = false, length = 6)
    private String expirationDate;
    @Column(updatable = false, length = 3)
    private String cvc;
    @Column(updatable = false)
    private CardType type;
    private int limitAmount;
    @Column(updatable = false)
    private LocalDateTime createdDate;

    public Card(String id, Account account, CardType type, int limitAmount) {
        this.id = id;
        this.account = account;
        this.type = type;
        this.limitAmount = limitAmount;

        this.cvc = newCvcNumber();
        this.createdDate = LocalDateTime.now();
        this.expirationDate = newExpirationDate(createdDate);
    }

    //==편의 메서드==//
    private String newCvcNumber() {
        Random rand = new Random();
        String newCvcNumber = "";
        for (int i = 0; i < 3; i++) {
            newCvcNumber += Integer.toString(rand.nextInt(10));
        }
        return newCvcNumber;
    }

    private String newExpirationDate(LocalDateTime now) {
        int year = now.getYear() + 10;
        int month = now.getMonthValue();
        return Integer.toString(year) + Integer.toString(month);
    }
}
