package toyproject.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.bank.domain.Card;

public interface CardRepository extends JpaRepository<Card, String> {
}
