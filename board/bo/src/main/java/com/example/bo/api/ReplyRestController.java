package com.example.bo.api;

import java.util.List;

import com.example.bo.model.ReplyDto;
import com.example.bo.service.ReplyService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "reply")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8080")
public class ReplyRestController {

    private final ReplyService replyService;
    
    /**
     * 댓글 작성
     * @param createParam
     * @return
     */
    @PostMapping("/create")
    public ReplyDto.create replyCreate(@RequestBody ReplyDto.createParam createParam){
        return replyService.replyCreate(createParam);
    }

    /**
     * 댓글 목록 전체 조회
     * @param listParam
     * @return
     */
    @GetMapping("/list")
    public List<ReplyDto.list> list(@ModelAttribute ReplyDto.listParam listParam){
        return replyService.list(listParam);
    }

    /**
     * 댓글 삭제
     */
    @PostMapping("/deleteReply")
    public ReplyDto.deleteReply deleteReply(@RequestBody ReplyDto.deleteReplyParam deleteReplyParam){
        return replyService.deleteReply(deleteReplyParam);
    }
}

