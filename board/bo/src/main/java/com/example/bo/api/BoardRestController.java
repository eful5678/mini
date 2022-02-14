package com.example.bo.api;

import com.example.bo.model.BoardDto;
import com.example.bo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8080")
public class BoardRestController {

    final BoardService boardService;

    @GetMapping("/list")
    public List<BoardDto.BoardList> boardList(){
        return boardService.boardList();
    }

    @GetMapping("/boardInfo")
    public BoardDto.BoardInfo boardInfo(@RequestParam Long id){
        return boardService.boardInfo(id);
    }

    @PostMapping("/create")
    public BoardDto.BoardCreate boardCreate(@RequestBody BoardDto.BoardCreateParam param){
        return boardService.boardCreate(param);
    }
}
