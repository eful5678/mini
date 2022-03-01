package com.example.bo.service;

import java.util.List;

import com.example.bo.model.ReplyDto;

public interface ReplyService {

    /**
     * 댓글 작성
     * @param createParam
     * @return
     */
    ReplyDto.create replyCreate(ReplyDto.createParam createParam);

    /**
     * 댓글 전체 조회
     * @param listParam
     * @return
     */
    List<ReplyDto.list> list(ReplyDto.listParam listParam);

    /**
     * 댓글 삭제
     */
    ReplyDto.deleteReply deleteReply(ReplyDto.deleteReplyParam deleteReplyParam);
    
    
}
