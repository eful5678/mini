package com.example.bo.service;

import java.util.List;

import com.example.bo.domain.Board;
import com.example.bo.domain.Reply;
import com.example.bo.model.ReplyDto;
import com.example.bo.model.ReplyDto.create;
import com.example.bo.model.ReplyDto.createParam;
import com.example.bo.model.ReplyDto.listParam;
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
        // TODO Auto-generated method stub

        final Long boardId = createParam.getBoardId();

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



        // if(createParam.getReplyId() == null){
        //     return replyRepositoryManager.replyCreate(createParam, board, depth);
        // }else{
        
        //     return replyRepositoryManager.replyCreate(createParam, board, depth, mainReply);
        // }

        
    }

    @Override
    public List<ReplyDto.list> list(listParam listParam) {
        // TODO Auto-generated method stub
        return replyRepositorySupport.list(listParam);
    }
    
}
