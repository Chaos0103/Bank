package toyproject.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.bank.domain.Client;
import toyproject.bank.repository.custom.ClientRepositoryCustom;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {
    Optional<Client> findByRrn(String rrn);
}
