package com.example.bo.repository;

import java.util.List;

import com.example.bo.domain.Board;
import com.example.bo.domain.Reply;
import com.example.bo.model.ReplyDto;

public interface ReplyRepositorySupport {
    
    List<ReplyDto.list> list(ReplyDto.listParam listParam);

    Reply findByReplyId(Long id);

    Reply findByParentId(Long id);

}
