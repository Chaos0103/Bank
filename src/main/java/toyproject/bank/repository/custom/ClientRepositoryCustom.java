package toyproject.bank.repository.custom;

import toyproject.bank.domain.Client;
import toyproject.bank.dto.ClientSearch;

import java.util.List;

public interface ClientRepositoryCustom {

    List<Client> findClientByCondition(ClientSearch searchClient);
}
