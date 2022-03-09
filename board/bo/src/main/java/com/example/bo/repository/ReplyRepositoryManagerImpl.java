package com.example.bo.repository;

import javax.persistence.EntityManager;

import com.example.bo.domain.Board;
import com.example.bo.domain.Reply;
import com.example.bo.model.ReplyDto;
import com.example.bo.model.ReplyDto.create;
import com.example.bo.model.ReplyDto.createParam;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryManagerImpl implements ReplyRepositoryManager {

    private final EntityManager entityManager;

    @Override
    public ReplyDto.create replyCreate(createParam createParam, Board board, Long depth, Reply reply) {

        final Reply replys = Reply.create(createParam, board, depth, reply).get();
        entityManager.persist(replys);
        

        return new ReplyDto.create(reply.getContent(), reply.getBoard().getId());
    }

    @Override
    public create replyFirstCreate(createParam createParam, Board board, Long depth) {
        final Reply reply = Reply.createFirst(createParam, board, depth).get();
        entityManager.persist(reply);
        

        return new ReplyDto.create(reply.getContent(), reply.getBoard().getId());
    }

    @Override
    public ReplyDto.deleteReply deleteReply(Reply reply) {
        reply.destroy(reply);

        return null;
    }
    
}
