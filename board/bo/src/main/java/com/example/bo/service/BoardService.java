package com.example.bo.service;

import com.example.bo.model.BoardDto;

import java.util.List;

public interface BoardService {

    List<BoardDto.BoardList> boardList();

    BoardDto.BoardCreate boardCreate(BoardDto.BoardCreateParam param);

    BoardDto.BoardInfo boardInfo(Long id);
}
