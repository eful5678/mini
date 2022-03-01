package com.example.bo.repository;

import com.example.bo.domain.Board;
import com.example.bo.model.BoardDto;

public interface BoardRepositoryManager {

    BoardDto.BoardCreate boardCreate(BoardDto.BoardCreateParam param);

    BoardDto.BoardDelete boardDelete(Board param);

    BoardDto.BoardLike boardLike(Board board);
}
