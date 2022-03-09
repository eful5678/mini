package com.example.bo.repository;

import javax.persistence.EntityManager;

import com.example.bo.domain.Board;
import com.example.bo.domain.QBoard;
import com.example.bo.model.BoardDto;
import com.example.bo.model.BoardDto.BoardDelete;
import com.example.bo.model.BoardDto.BoardLike;
import com.example.bo.model.BoardDto.BoardUpdateParam;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import lombok.NonNull;

@Repository
public class BoardRepositoryManagerImpl extends QuerydslRepositorySupport implements BoardRepositoryManager{

    @NonNull
    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryManagerImpl(EntityManager entityManager) {
        super(Board.class);
        super.setEntityManager(entityManager);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public BoardDto.BoardCreate boardCreate(BoardDto.BoardCreateParam param) {
        final EntityManager entityManager = super.getEntityManager();
        final Board board = Board.create(param).get();
        entityManager.persist(board);
        return new BoardDto.BoardCreate(board.getTitle(), board.getWriter());
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

    @Override
    public BoardDto.BoardUpdate boardUpdate(BoardUpdateParam updateParam) {
        // return Board.update(updateParam, board);
        QBoard board = QBoard.board;
        final BooleanExpression isBoardId = board.id.eq(updateParam.getBoardId());
        jpaQueryFactory.update(board)
                        .set(board.content, updateParam.getContent())
                        .where(isBoardId)
                        .execute();

        return new BoardDto.BoardUpdate("d");
    }
}
