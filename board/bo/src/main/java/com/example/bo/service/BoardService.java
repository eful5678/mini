package com.example.bo.service;

import com.example.bo.model.BoardDto;

import java.util.List;

public interface BoardService {

    /**
     * 작성된 글 전체 조회
     * @return
     */
    List<BoardDto.BoardList> boardList();

    /**
     * 글 작성
     * @param param
     * @return
     */
    BoardDto.BoardCreate boardCreate(BoardDto.BoardCreateParam param);

    BoardDto.BoardInfo boardInfo(Long id);

    /**
     * 
     * @param updateParam
     * @return
     */
    BoardDto.BoardUpdate boardUpdate(BoardDto.BoardUpdateParam updateParam);

    /**
     * 글 삭제
     */
    BoardDto.BoardDelete boardDelete(BoardDto.BoardDeleteParam param);
    

    /**
     * 좋아요
     */
    BoardDto.BoardLike boardLike(BoardDto.BoardLikeParam boardLikeParam);
}
