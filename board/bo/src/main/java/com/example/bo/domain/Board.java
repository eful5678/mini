package com.example.bo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.bo.model.BoardDto;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Entity
@Getter
public class Board extends BaseEntity implements BaseFunction<Board>{

    @Id
    @GeneratedValue
    @Column(name = "boardId")
    private Long id;

    // 작성자
    private String writer;

    // 제목
    private String title;

    // 글 내용
    private String content;

    // 좋아요 카운팅
    @ColumnDefault("0")
    private BigDecimal likeCount;

    // 댓글 테이블
    @OneToMany(mappedBy = "board")
    private List<Reply> reply = new ArrayList<>();

    public Board(){

    }

    public Board(Long createUserId
                 , LocalDateTime createDateTime
                 , char useYn
                 , char delYn
                 , Long id
                 , String writer
                 , String title
                 , String content
                 , Long like
    ){
        super(createUserId, createDateTime, useYn, delYn);
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;

    }
    // 글 저장
    public static Supplier<Board> create(BoardDto.BoardCreateParam createParam){
        return () -> new Board(createParam);
    }

    public Board(BoardDto.BoardCreateParam createParam){
        this.title = createParam.getTitle();
        this.content = createParam.getContent();
    }


    public BigDecimal boardLike(Board b){
        b.likeCount = b.likeCount.add(BigDecimal.ONE);
        return b.likeCount;
    }

    @Override
    public Supplier<Board> identity() {
        return () -> new Board();
    }

    @Override
    public Board clone(Board e) {
        return null;
    }

    @Override
    public Board destroy(Board e) {
        e.delYn = 'Y';
        e.useYn = 'N';
        return null;
    }

    @Override
    public boolean validate(Board e) {
        return false;
    }

    @Override
    public void doSynchronizerRelationData() {
        
    }


}
