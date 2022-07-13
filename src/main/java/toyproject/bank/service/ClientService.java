package toyproject.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Address;
import toyproject.bank.domain.Client;
import toyproject.bank.dto.ClientDto;
import toyproject.bank.dto.ClientSearch;
import toyproject.bank.exception.DuplicateException;
import toyproject.bank.exception.NoSuchException;
import toyproject.bank.repository.ClientRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * 고객등록
     */
    public Long create(ClientDto clientDto) {
        duplicate(clientDto.getRrn());

        Address address = new Address(clientDto.getZipcode(), clientDto.getMainAddress(), clientDto.getDetailAddress());
        Client client = new Client(clientDto.getName(), clientDto.getEmail(), clientDto.getPhone(), clientDto.getRrn(), address);
        Client savedClient = clientRepository.save(client);

        return savedClient.getId();
    }

    /**
     * 고객수정
     */
    public void update(Long id, ClientDto clientDto) {
        Client client = findClient(id);
        client.update(clientDto.getEmail(), clientDto.getPhone(), new Address(clientDto.getZipcode(), clientDto.getMainAddress(), clientDto.getDetailAddress()));
    }

    /**
     * 고객삭제
     */
    public void delete(Long id) {
        Client client = findClient(id);
        clientRepository.delete(client);
    }

    /**
     * 고객조회
     */
    public List<ClientDto> searchList(ClientSearch searchClient) {
        List<Client> clientList = clientRepository.findClientByCondition(searchClient);
        return clientList.stream()
                .map(ClientDto::new)
                .toList();
    }

    public ClientDto searchOne(Long id) {
        return new ClientDto(findClient(id));
    }

    private void duplicate(String rrn) {
        if (clientRepository.findByRrn(rrn).isPresent()) {
            throw new DuplicateException("이미 가입된 회원입니다.");
        }
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });
    }
}
