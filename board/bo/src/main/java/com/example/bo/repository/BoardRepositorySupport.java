package com.example.bo.repository;

import com.example.bo.domain.Board;
import com.example.bo.model.BoardDto;

import java.util.List;

public interface BoardRepositorySupport {

    List<BoardDto.BoardList> boardList();

    BoardDto.BoardInfo boardInfo(Long id);

    Board findByBoardId(Long boardId);

}

