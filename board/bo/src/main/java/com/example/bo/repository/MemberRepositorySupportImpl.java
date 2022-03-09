package com.example.bo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.bo.domain.Member;
import com.example.bo.domain.QMember;
import com.example.bo.model.MemberDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositorySupportImpl extends QuerydslRepositorySupport implements MemberRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;


    public MemberRepositorySupportImpl(EntityManager entityManager) {
        super(Member.class);
        super.setEntityManager(entityManager);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public String findNameByUsername(String username) {

        QMember member = QMember.member;

        final BooleanExpression isUsername = member.username.eq(username);

        return jpaQueryFactory.select(member.name)
                            .from(member)
                            .where(isUsername)
                            .fetchOne();
    }

    @Override
    public List<MemberDto.list> list() {
        QMember member = QMember.member;
        return jpaQueryFactory.select(Projections.constructor(MemberDto.list.class
                                                                , member.username
                                                                , member.name
                                                                , member.createDateTime
        ))
                            .from(member)
                            .fetch();
    }
    
}
