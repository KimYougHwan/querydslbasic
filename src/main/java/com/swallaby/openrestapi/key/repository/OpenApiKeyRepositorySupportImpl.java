package com.swallaby.openrestapi.key.repository;

import static com.swallaby.openrestapi.entity.QOpenRestApiKey.openRestApiKey;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swallaby.openrestapi.entity.OpenRestApiKey;


@Repository
public class OpenApiKeyRepositorySupportImpl extends QuerydslRepositorySupport
        implements OpenApiKeyRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public OpenApiKeyRepositorySupportImpl(JPAQueryFactory jpaQueryFactory,
            EntityManager entityManager) {
        super(OpenRestApiKey.class);
        super.setEntityManager(entityManager);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public String findByKey(String apiKey) {
        return jpaQueryFactory.select(openRestApiKey.key).from(openRestApiKey)
                .where(openRestApiKey.key.eq(apiKey)).fetchOne();
    }

}
