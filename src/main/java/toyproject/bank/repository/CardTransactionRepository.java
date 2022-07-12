package toyproject.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.bank.domain.CardTransaction;

public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {
}
