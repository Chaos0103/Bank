package toyproject.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.bank.domain.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByRrn(String rrn);
}
