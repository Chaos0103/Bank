package toyproject.bank.dto;

import lombok.Data;
import toyproject.bank.domain.Client;

@Data
public class ClientDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String rrn;
    private String zipcode;
    private String mainAddress;
    private String detailAddress;

    public ClientDto(Long id, String name, String email, String phone, String rrn, String zipcode, String mainAddress, String detailAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rrn = rrn;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
    }

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.phone = client.getPhone();
        this.rrn = client.getRrn();
        this.zipcode = client.getAddress().getZipcode();
        this.mainAddress = client.getAddress().getMainAddress();
        this.detailAddress = client.getAddress().getDetailAddress();
    }

}
