package com.example.bo.repository;

import java.util.List;

import com.example.bo.domain.Board;
import com.example.bo.domain.Reply;
import com.example.bo.model.ReplyDto;

public interface ReplyRepositorySupport {
    
    List<ReplyDto.list> list(ReplyDto.listParam listParam);

    Reply findByReplyId(Long id);

    Reply findByParentId(Long id);

    Long depthMax();

    /**
     * 삭제 대상 댓글 조회
     */
    List<ReplyDto.deletedReply> findByReplyIdForDelete(Long replyId);

    /**
     * 게시글 키 조회
     */
    Long findBoardIdbyReplyId(Long replyId);

    List<Reply> findDeletedReplyByReplyId(Long replyId);

}
