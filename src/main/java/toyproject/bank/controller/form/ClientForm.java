package toyproject.bank.controller.form;

import lombok.Data;
import toyproject.bank.dto.ClientDto;

@Data
public class ClientForm {

    private String name;
    private String email;
    private String phone;
    private String rrn;
    private String zipcode;
    private String mainAddress;
    private String detailAddress;

    public void insertForm(ClientDto clientDto) {
        this.name = clientDto.getName();
        this.email = clientDto.getEmail();
        this.phone = clientDto.getPhone();
        this.rrn = clientDto.getRrn();
        this.zipcode = clientDto.getZipcode();
        this.mainAddress = clientDto.getMainAddress();
        this.detailAddress = clientDto.getDetailAddress();
    }
}
