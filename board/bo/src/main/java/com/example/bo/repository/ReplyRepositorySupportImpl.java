package com.example.bo.repository;

import java.util.List;

import javax.persistence.EntityManager;

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
    }


    @Override
    public List<ReplyDto.list> list(listParam listParam) {
        QReply reply = QReply.reply;



        Long boardId = listParam.getBoardId() == null ? null : listParam.getBoardId();
        
        final BooleanExpression isDelYn = reply.delYn.eq('N');
        final BooleanExpression isUseYn = reply.useYn.eq('Y');

        final BooleanExpression isBoardId = reply.board.id.eq(boardId);

        return jpaQueryFactory.select(Projections.constructor(ReplyDto.list.class
                                        // , reply.content
                                        // , reply.id
                                        // , reply.depth
                                        // , reply.parentId
                                        // , reply.subReply
                                        , reply
                                        , reply.board.id
                                        ))
                            .from(reply)
                            .where(isBoardId
                                .and(isDelYn
                                .and(isUseYn)))
                            .fetch();
    }

    public Long depthMax(){
        QReply reply = QReply.reply;

        return jpaQueryFactory.select(reply.depth.max())
                            .from(reply)
                            .fetchOne();
    }


    @Override
    public Reply findByReplyId(Long id) {
        QReply reply = QReply.reply;
        final BooleanExpression isReplyId = reply.id.eq(id);
        return jpaQueryFactory.select(reply)
                            .from(reply)
                            .where(isReplyId)
                            .fetchOne();
    }


    @Override
    public Reply findByParentId(Long id) {

        QReply reply = QReply.reply;

        final BooleanExpression isParentId = reply.id.eq(id);
        return jpaQueryFactory.select(reply)
                                .from(reply)
                                .where(isParentId)
                                .fetchOne();
    }


    @Override
    public List<ReplyDto.deletedReply> findByReplyIdForDelete(Long replyId) {
        QReply reply = QReply.reply;

        final BooleanExpression isParentId = reply.parentId.eq(replyId);
        
        return jpaQueryFactory.select(Projections.constructor(ReplyDto.deletedReply.class
                                        , reply.id
                                        , reply.parentId
                                        ))
                            .from(reply)
                            .where(isParentId)
                            .fetch();
    }


    @Override
    public Long findBoardIdbyReplyId(Long replyId) {
        QReply reply = QReply.reply;

        final BooleanExpression isReplyId = reply.id.eq(replyId);
        return jpaQueryFactory.select(reply.board.id)
                            .from(reply)
                            .where(isReplyId)
                            .fetchOne();
    }


    @Override
    public List<Reply> findDeletedReplyByReplyId(Long replyId) {

        QReply reply = QReply.reply;

        final BooleanExpression isReplyId = reply.id.eq(replyId);
        final BooleanExpression isParentId = reply.parentId.eq(replyId);
        return jpaQueryFactory.select(reply)
                            .from(reply)
                            .where(isReplyId
                                .or(isParentId))
                            .fetch();
    }


    @Override
    public Reply findReplyByReplyId(Long replyId) {
        QReply reply = QReply.reply;

        final BooleanExpression isReplyId = reply.id.eq(replyId);

        return jpaQueryFactory.select(reply)
                            .from(reply)
                            .where(isReplyId)
                            .fetchOne();
    }


    
    
}
