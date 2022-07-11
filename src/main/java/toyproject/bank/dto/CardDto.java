package toyproject.bank.dto;

import lombok.Data;
import toyproject.bank.domain.CardType;

@Data
public class CardDto {

    private String id;
    private String expirationDate;
    private String cvc;
    private CardType cardType;
    private int limitAmount;

    public CardDto(String id, String expirationDate, String cvc, CardType cardType, int limitAmount) {
        this.id = id;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.cardType = cardType;
        this.limitAmount = limitAmount;
    }
}
