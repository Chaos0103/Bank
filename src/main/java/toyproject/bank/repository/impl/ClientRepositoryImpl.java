package toyproject.bank.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import toyproject.bank.domain.Client;
import toyproject.bank.dto.ClientSearch;
import toyproject.bank.repository.custom.ClientRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.*;
import static toyproject.bank.domain.QClient.*;

public class ClientRepositoryImpl implements ClientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ClientRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Client> findClientByCondition(ClientSearch searchClient) {
        return queryFactory
                .selectFrom(client)
                .where(
                        nameEq(searchClient.getName())
                )
                .fetch();
    }

    private BooleanExpression nameEq(String name) {
        return hasText(name) ? client.name.eq(name) : null;
    }
}
