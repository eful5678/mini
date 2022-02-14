package com.example.bo.service;

import java.util.List;

import com.example.bo.model.ReplyDto;

public interface ReplyService {

    ReplyDto.create replyCreate(ReplyDto.createParam createParam);

    List<ReplyDto.list> list(ReplyDto.listParam listParam);
    
}
