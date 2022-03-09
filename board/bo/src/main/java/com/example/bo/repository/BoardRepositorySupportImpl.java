package com.example.bo.repository;

import com.example.bo.domain.Board;
import com.example.bo.domain.QBoard;
import com.example.bo.model.BoardDto;
import com.example.bo.model.BoardDto.BoardInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BoardRepositorySupportImpl extends QuerydslRepositorySupport implements BoardRepositorySupport{

    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositorySupportImpl(EntityManager entityManager){
        super(Board.class);
        super.setEntityManager(entityManager);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<BoardDto.BoardList> boardList() {

        QBoard board = QBoard.board;

        final BooleanExpression isUseYn = board.useYn.eq('Y');
        final BooleanExpression isDelYn = board.delYn.eq('N');

        List<BoardDto.BoardList> boardList = jpaQueryFactory.select(Projections.constructor(BoardDto.BoardList.class
                                                                    , board.writer
                                                                    , board.title
                                                                    , board.content
                                                                    , board.id
                                                                    , board.createDateTime
                                                                    , board.likeCount
                                                            ))
                                                            .from(board)
                                                            .where(isUseYn
                                                                .and(isDelYn))
                                                            .orderBy(board.createDateTime.desc())
                                                            .fetch();

        return boardList;
    }

    @Override
    public BoardInfo boardInfo(Long id) {

        QBoard board = QBoard.board;

        final BooleanExpression isId = board.id.eq(id);

        return jpaQueryFactory.select(Projections.constructor(BoardDto.BoardInfo.class
                                            , board.title
                                            , board.content
                                            , board.id
                                            ))
                            .from(board)
                            .where(isId)
                            .fetchOne();
    }

    @Override
    public Board findByBoardId(Long boardId) {

        QBoard board = QBoard.board;

        final BooleanExpression isBoardId = board.id.eq(boardId);

        return jpaQueryFactory.select(board)
                            .from(board)
                            .where(isBoardId)
                            .fetchOne();
    }
}
