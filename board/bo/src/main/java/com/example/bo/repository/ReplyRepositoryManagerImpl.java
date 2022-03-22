package com.example.bo.repository;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import com.example.bo.domain.Board;
import com.example.bo.domain.QReply;
import com.example.bo.domain.Reply;
import com.example.bo.model.ReplyDto;
import com.example.bo.model.ReplyDto.create;
import com.example.bo.model.ReplyDto.createParam;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import lombok.NonNull;

@Repository
public class ReplyRepositoryManagerImpl extends QuerydslRepositorySupport implements ReplyRepositoryManager {

    @NonNull
    private final JPAQueryFactory jpaQueryFactory;

    public ReplyRepositoryManagerImpl(EntityManager entityManager) {
        super(Reply.class);
        super.setEntityManager(entityManager);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public ReplyDto.create replyCreate(createParam createParam, Board board, Long depth, Reply reply) {
        final EntityManager entityManager = super.getEntityManager();

        final Reply replys = Reply.create(createParam, board, depth, reply).get();
        entityManager.persist(replys);
        

        return new ReplyDto.create(reply.getContent(), reply.getBoard().getId());
    }

    @Override
    public create replyFirstCreate(createParam createParam, Board board, Long depth) {
        final EntityManager entityManager = super.getEntityManager();
        final Reply reply = Reply.createFirst(createParam, board, depth).get();
        entityManager.persist(reply);
        

        return new ReplyDto.create(reply.getContent(), reply.getBoard().getId());
    }

    @Override
    public ReplyDto.deleteReply deleteReply(Reply reply) {
        reply.destroy(reply);

        return null;
    }

    @Override
    public ReplyDto.updateReply updateReply(Reply rep, String content) {
        QReply reply = QReply.reply;
        Long replyId = rep.getId();
        final BooleanExpression isReplyId = reply.id.eq(replyId);
        jpaQueryFactory.update(reply)
                    .set(reply.content, content)
                    .set(reply.updateDateTime, LocalDateTime.now())
                    .where(isReplyId)
                    .execute();
        return new ReplyDto.updateReply(rep.getId(), rep.getContent());
    }
    
}
