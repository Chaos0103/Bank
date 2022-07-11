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
    private int limit;
    @Column(updatable = false)
    private LocalDateTime createdDate;

}
