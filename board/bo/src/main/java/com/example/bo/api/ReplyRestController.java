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
    
    @PostMapping("/create")
    public ReplyDto.create replyCreate(@RequestBody ReplyDto.createParam createParam){
        return replyService.replyCreate(createParam);
    }

    @GetMapping("/list")
    public List<ReplyDto.list> list(@ModelAttribute ReplyDto.listParam listParam){
        return replyService.list(listParam);
    }
}
