package toyproject.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.bank.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
