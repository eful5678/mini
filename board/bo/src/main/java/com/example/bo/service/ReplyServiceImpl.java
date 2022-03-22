package com.example.bo.service;

import java.util.List;

import com.example.bo.domain.Board;
import com.example.bo.domain.Reply;
import com.example.bo.model.ReplyDto;
import com.example.bo.model.ReplyDto.create;
import com.example.bo.model.ReplyDto.createParam;
import com.example.bo.model.ReplyDto.deleteReplyParam;
import com.example.bo.model.ReplyDto.listParam;
import com.example.bo.model.ReplyDto.updateReplyParam;
import com.example.bo.repository.BoardRepositorySupport;
import com.example.bo.repository.ReplyRepositoryManager;
import com.example.bo.repository.ReplyRepositorySupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    final ReplyRepositoryManager replyRepositoryManager;
    final ReplyRepositorySupport replyRepositorySupport;

    final BoardRepositorySupport boardRepositorySupport;

    @Override
    public create replyCreate(createParam createParam) {
        Long boardId = 0L;
        // 부모의 replyID를 통해 boardId를 가져오자
        if(createParam.getBoardId() == null){
            boardId = replyRepositorySupport.findBoardIdbyReplyId(createParam.getReplyId());
        }else{
            boardId = createParam.getBoardId();
        }


        // final Long boardId = createParam.getBoardId();
        createParam.setBoardId(boardId);

        final Board board = boardRepositorySupport.findByBoardId(boardId);
        Long depth = 0L;

        if(createParam.getReplyId() == null){
            return replyRepositoryManager.replyFirstCreate(createParam, board, depth);
        }else{
            depth = replyRepositorySupport.findByReplyId(createParam.getReplyId()).getDepth() + 1;
            final Long parentId = createParam.getParentId(); 
            final Reply mainReply =replyRepositorySupport.findByParentId(parentId);
            return replyRepositoryManager.replyCreate(createParam, board, depth, mainReply);
        }



        
    }

    @Override
    public List<ReplyDto.list> list(listParam listParam) {

        if(listParam.getBoardId() == null){
            Long boardId = replyRepositorySupport.findBoardIdbyReplyId(listParam.getReplyId());
            listParam.setBoardId(boardId);
        }

        List<ReplyDto.list> list = replyRepositorySupport.list(listParam);

        Long depthMax = replyRepositorySupport.depthMax();

        list.get(0).setDepthMax(depthMax);

        return list;
    }

    @Override
    public ReplyDto.deleteReply deleteReply(deleteReplyParam deleteReplyParam) {

        // List<ReplyDto.deletedReply> deleteReplyList = replyRepositorySupport.findByReplyIdForDelete(deleteReplyParam.getReplyId());
        
        // Reply reply = null;

        List<Reply> deletedReplyList = replyRepositorySupport.findDeletedReplyByReplyId(deleteReplyParam.getReplyId());

        // 해당 댓글의 id값으로 해당 댓글의 자식 댓글들을 일괄적으로 지운다

        for (Reply deletedReply : deletedReplyList) {

            replyRepositoryManager.deleteReply(deletedReply);
            
        }

        return null;
    }

    @Override
    public ReplyDto.updateReply updateReply(updateReplyParam param) {
        Long replyId = param.getReplyId();
        String content = param.getContent();
        Reply reply = replyRepositorySupport.findReplyByReplyId(replyId);
        return replyRepositoryManager.updateReply(reply, content);
    }
    
}
