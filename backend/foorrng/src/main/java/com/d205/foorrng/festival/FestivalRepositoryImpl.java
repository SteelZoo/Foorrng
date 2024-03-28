package com.d205.foorrng.festival;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.d205.foorrng.festival.QFestival.festival;

@Repository
public class FestivalRepositoryImpl extends QuerydslRepositorySupport implements FestivalRepository{
    private final JPAQueryFactory jpaQueryFactory;
    public FestivalRepositoryImpl(EntityManager em){
        super(Festival.class);
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<Festival> findFutureFestivals() {
        Long currentDateTime = System.currentTimeMillis();
        return jpaQueryFactory
                .selectFrom(festival)
                .where(festival.startDay.gt(currentDateTime))
                .fetch();
    }
}
