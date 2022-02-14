package com.example.bo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.bo.model.BoardDto;

import lombok.Getter;

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

    private String writer;

    private String title;

    private String content;

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
    ){
        super(createUserId, createDateTime, useYn, delYn);
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;

    }

    public static Supplier<Board> create(BoardDto.BoardCreateParam createParam){
        return () -> new Board(createParam);
    }

    public Board(BoardDto.BoardCreateParam createParam){
        this.title = createParam.getTitle();
        this.content = createParam.getContent();
    }

    @Override
    public Supplier<Board> identity() {
        // TODO Auto-generated method stub
        return () -> new Board();
    }

    @Override
    public Board clone(Board e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Board destroy(Board e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validate(Board e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void doSynchronizerRelationData() {
        // TODO Auto-generated method stub
        
    }


}
