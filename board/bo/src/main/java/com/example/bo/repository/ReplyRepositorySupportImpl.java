package com.example.bo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.bo.domain.Board;
import com.example.bo.domain.QBoard;
import com.example.bo.domain.QReply;
import com.example.bo.domain.Reply;
import com.example.bo.model.ReplyDto;
import com.example.bo.model.ReplyDto.listParam;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyRepositorySupportImpl extends QuerydslRepositorySupport implements ReplyRepositorySupport{

    private final JPAQueryFactory jpaQueryFactory;


    public ReplyRepositorySupportImpl(EntityManager entityManager) {
        super(Reply.class);
        super.setEntityManager(entityManager);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        //TODO Auto-generated constructor stub
    }


    @Override
    public List<ReplyDto.list> list(listParam listParam) {
        // TODO Auto-generated method stub
        QReply reply = QReply.reply;

        Long boardId = listParam.getBoardId() == null ? null : listParam.getBoardId();
        
        final BooleanExpression isBoardId = reply.board.id.eq(boardId);

        return jpaQueryFactory.select(Projections.constructor(ReplyDto.list.class
                                        // , reply.content
                                        // , reply.id
                                        // , reply.depth
                                        // , reply.parentId
                                        , reply
                                        ))
                            .from(reply)
                            .where(isBoardId)
                            .fetch();
    }


    @Override
    public Reply findByReplyId(Long id) {
        // TODO Auto-generated method stub
        QReply reply = QReply.reply;
        final BooleanExpression isReplyId = reply.id.eq(id);
        return jpaQueryFactory.select(reply)
                            .from(reply)
                            .where(isReplyId)
                            .fetchOne();
    }


    @Override
    public Reply findByParentId(Long id) {
        // TODO Auto-generated method stub

        QReply reply = QReply.reply;

        final BooleanExpression isParentId = reply.id.eq(id);
        return jpaQueryFactory.select(reply)
                                .from(reply)
                                .where(isParentId)
                                .fetchOne();
    }


    
    
}
