package toyproject.bank.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    @Column(length = 5)
    private String zipcode;
    private String mainAddress;
    private String detailAddress;
}
