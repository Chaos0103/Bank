package toyproject.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.bank.domain.Address;
import toyproject.bank.domain.Client;
import toyproject.bank.dto.ClientDto;
import toyproject.bank.exception.DuplicateException;
import toyproject.bank.exception.NoSuchException;
import toyproject.bank.repository.ClientRepository;

import java.util.Optional;

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
        Client findClient = clientRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });

        findClient.update(clientDto.getEmail(), clientDto.getPhone(), new Address(clientDto.getZipcode(), clientDto.getMainAddress(), clientDto.getDetailAddress()));
    }

    /**
     * 고객삭제
     */
    public void delete(Long id) {
        Client findClient = clientRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });
        clientRepository.delete(findClient);
    }

    private void duplicate(String rrn) {
        if (clientRepository.findByRrn(rrn).isPresent()) {
            throw new DuplicateException("이미 가입된 회원입니다.");
        }
    }

    //조회
}
