package com.example.bo.repository;


import com.example.bo.domain.Board;
import com.example.bo.domain.Reply;
import com.example.bo.model.ReplyDto;

public interface ReplyRepositoryManager {
    
    ReplyDto.create replyCreate(ReplyDto.createParam createParam, Board board, Long depth, Reply reply);

    ReplyDto.create replyFirstCreate(ReplyDto.createParam createParam, Board board, Long depth);

    /**
     * 댓글 삭제기능
     */
    ReplyDto.deleteReply deleteReply(Reply deletedId);
}
