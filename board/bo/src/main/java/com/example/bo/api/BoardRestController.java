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

    /**
     * 작성된 글 목록 전체조회
     */
    @GetMapping("/list")
    public List<BoardDto.BoardList> boardList(){
        return boardService.boardList();
    }

    @GetMapping("/boardInfo")
    public BoardDto.BoardInfo boardInfo(@RequestParam Long id){
        return boardService.boardInfo(id);
    }

    /**
     * 글 작성
     * @param param
     * @return
     */
    @PostMapping("/create")
    public BoardDto.BoardCreate boardCreate(@RequestBody BoardDto.BoardCreateParam param){
        return boardService.boardCreate(param);
    }

    /**
     * 게시글 업데이트 기능
     * 현재 글 내용만 수정 가능
     */
    @PostMapping("/updateBoard")
    public BoardDto.BoardUpdate boardUpdate(@RequestBody BoardDto.BoardUpdateParam updateParam){
        return boardService.boardUpdate(updateParam);
    }

    /**
     * 글 삭제
     */
    @PostMapping("/deleteBoard")
    public BoardDto.BoardDelete boardDelete(@RequestBody BoardDto.BoardDeleteParam boardDeleteParam){
        return boardService.boardDelete(boardDeleteParam);
    }

    /**
     * 게시글 좋아요기능
     */
    @PostMapping("/likeBoard")
    public BoardDto.BoardLike likeBoard(@RequestBody BoardDto.BoardLikeParam boardLikeParam){
        return boardService.boardLike(boardLikeParam);
    }
}
