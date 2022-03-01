package com.example.bo.repository;

import javax.persistence.EntityManager;

import com.example.bo.domain.Board;
import com.example.bo.model.BoardDto;
import com.example.bo.model.BoardDto.BoardDelete;
import com.example.bo.model.BoardDto.BoardDeleteParam;
import com.example.bo.model.BoardDto.BoardLike;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryManagerImpl implements BoardRepositoryManager{

    private final EntityManager entityManager;

    @Override
    public BoardDto.BoardCreate boardCreate(BoardDto.BoardCreateParam param) {

        final Board board = Board.create(param).get();
        entityManager.persist(board);
        return new BoardDto.BoardCreate(board.getTitle());
    }

    @Override
    public BoardDelete boardDelete(Board board) {

        board.destroy(board);

        return null;
    }

    @Override
    public BoardLike boardLike(Board board) {
        
        return new BoardDto.BoardLike(board.boardLike(board));
    }
}
