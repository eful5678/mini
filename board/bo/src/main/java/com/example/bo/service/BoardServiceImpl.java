package com.example.bo.service;

import com.example.bo.domain.Board;
import com.example.bo.model.BoardDto;
import com.example.bo.model.BoardDto.BoardDelete;
import com.example.bo.model.BoardDto.BoardDeleteParam;
import com.example.bo.model.BoardDto.BoardInfo;
import com.example.bo.model.BoardDto.BoardLike;
import com.example.bo.repository.BoardRepositoryManager;
import com.example.bo.repository.BoardRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    final BoardRepositorySupport boardRepositorySupport;

    final BoardRepositoryManager boardRepositoryManager;

    @Override
    public List<BoardDto.BoardList> boardList() {
        return boardRepositorySupport.boardList();
    }

    @Override
    public BoardDto.BoardCreate boardCreate(BoardDto.BoardCreateParam param) {
        return boardRepositoryManager.boardCreate(param);
    }

    @Override
    public BoardInfo boardInfo(Long id) {
        return boardRepositorySupport.boardInfo(id);
    }

    @Override
    public BoardDelete boardDelete(BoardDeleteParam param) {
        Board board = boardRepositorySupport.findByBoardId(param.getBoardId());
        return boardRepositoryManager.boardDelete(board);
    }

    @Override
    public BoardLike boardLike(BoardDto.BoardLikeParam boardLikeParam) {
        Board board = boardRepositorySupport.findByBoardId(boardLikeParam.getBoardId());
        return boardRepositoryManager.boardLike(board);
    }
}
