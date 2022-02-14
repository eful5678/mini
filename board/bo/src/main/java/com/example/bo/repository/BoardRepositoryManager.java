package com.example.bo.repository;

import com.example.bo.model.BoardDto;

public interface BoardRepositoryManager {

    BoardDto.BoardCreate boardCreate(BoardDto.BoardCreateParam param);
}
