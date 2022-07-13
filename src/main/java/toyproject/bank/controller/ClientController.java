package toyproject.bank.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.bank.controller.form.ClientForm;
import toyproject.bank.dto.ClientDto;
import toyproject.bank.dto.ClientSearch;
import toyproject.bank.service.ClientService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/client/new")
    public String createClient(@ModelAttribute("clientForm") ClientForm clientForm) {
        return "client/createClientForm";
    }

    @PostMapping("/client/new")
    public String create(ClientForm clientForm) {
        ClientDto clientDto = new ClientDto(null, clientForm.getName(), clientForm.getEmail(), clientForm.getPhone(), clientForm.getRrn(), clientForm.getZipcode(), clientForm.getMainAddress(), clientForm.getDetailAddress());
        clientService.create(clientDto);
        return "redirect:/";
    }

    @GetMapping("/clients")
    public String searchClient(@ModelAttribute("clientSearch") ClientSearch clientSearch, Model model) {
        List<ClientDto> clientDtoList = clientService.searchList(clientSearch);
        model.addAttribute("clientDtoList", clientDtoList);
        return "client/clientList";
    }

    @GetMapping("client/{clientId}/update")
    public String updateClient(@PathVariable Long clientId, @ModelAttribute("clientForm") ClientForm clientForm) {
        ClientDto clientDto = clientService.searchOne(clientId);
        clientForm.insertForm(clientDto);
        return "client/updateClientForm";
    }

    @PostMapping("client/{clientId}/update")
    public String update(@PathVariable Long clientId, @ModelAttribute("clientForm") ClientForm clientForm) {
        ClientDto clientDto = new ClientDto(null, clientForm.getName(), clientForm.getEmail(), clientForm.getPhone(), clientForm.getRrn(), clientForm.getZipcode(), clientForm.getMainAddress(), clientForm.getDetailAddress());
        clientService.update(clientId, clientDto);
        return "redirect:/clients";
    }

    @PostMapping("client/{clientId}/remove")
    public String deleteClient(@PathVariable Long clientId) {
        clientService.delete(clientId);
        return "redirect:/clients";
    }
}
