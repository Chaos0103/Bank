package toyproject.bank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client {

    @Id @GeneratedValue
    @Column(name = "client_id")
    private Long id;

    @Column(updatable = false, length = 50)
    private String name;
    @Column(length = 100)
    private String email;
    @Column(length = 11)
    private String phone;
    @Column(updatable = false, length = 14)
    private String rrn;
    @Embedded
    private Address address;

    public Client(String name, String email, String phone, String rrn, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rrn = rrn;
        this.address = address;
    }

    //==비즈니스 로직==//
    public void update(String email, String phone, Address address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
