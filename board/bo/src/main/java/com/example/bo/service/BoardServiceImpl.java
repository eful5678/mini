package com.example.bo.service;

import com.example.bo.model.BoardDto;
import com.example.bo.model.BoardDto.BoardInfo;
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
        // TODO Auto-generated method stub
        return boardRepositorySupport.boardInfo(id);
    }
}
