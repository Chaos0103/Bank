package toyproject.bank.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Address;
import toyproject.bank.domain.Client;
import toyproject.bank.dto.ClientDto;
import toyproject.bank.repository.ClientRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientServiceTest {

    @Autowired ClientService clientService;
    @Autowired ClientRepository clientRepository;

    @Test
    void create() {
        ClientDto clientDto = new ClientDto(null, "tester", "test@naver.com", "01012345678", "010101-1111111", "12345", "서울특별시", "강남구");
        Long clientId = clientService.create(clientDto);

        Optional<Client> findClient = clientRepository.findById(clientId);

        assertThat(findClient).isPresent();
    }

    @Test
    void update() {
        Client client = new Client("tester", "test@naver.com", "01012345678", "010101-1111111", new Address("12345", "서울특별시", "강남구"));
        Client savedClient = clientRepository.save(client);
        ClientDto clientDto = new ClientDto(savedClient.getId(), "tester", "newTest@naver.com", "01012345678", "010101-1111111", "12345", "서울특별시", "강남구");

        clientService.update(savedClient.getId(), clientDto);

        Client findClient = clientRepository.findById(savedClient.getId()).get();
        assertThat(findClient.getEmail()).isEqualTo("newTest@naver.com");
    }

    @Test
    void delete() {
        Client client = new Client("tester", "test@naver.com", "01012345678", "010101-1111111", new Address("12345", "서울특별시", "강남구"));
        Client savedClient = clientRepository.save(client);

        clientService.delete(savedClient.getId());

        Optional<Client> findClient = clientRepository.findById(savedClient.getId());
        assertThat(findClient).isEmpty();
    }
}