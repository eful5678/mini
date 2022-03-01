package com.example.bo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.bo.model.ReplyDto;
import com.example.bo.model.ReplyDto.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseEntity implements BaseFunction<Reply> {
    // PK
    @Id
    @GeneratedValue
    @Column(name = "replyId")
    private Long id;

    // 댓글 내용
    private String content;

    // 댓글 작성자
    private String writer;

    // 게시글 테이블
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    // 부모 키
    @Column
    private Long parentId;

    // 댓글 계층
    @Column
    private Long depth;

    // 셀프조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mainReplyId")
    private Reply mainReply;

    @OneToMany(mappedBy = "mainReply")
    private List<Reply> subReply = new ArrayList<>();

    public Reply(Long createUserId
                , LocalDateTime createDateTime
                , char useYn
                , char delYn
                , Board board
                , Long replyParentId
                , String content
                ){
        super(createUserId, createDateTime, useYn, delYn);
        this.board = board;
        this.content = content;
    }

    public Reply(ReplyDto.createParam createParam, Board board, Long depth, Reply reply){
        this.content = createParam.getContent();
        this.parentId = createParam.getReplyId();
        this.board = board;
        this.depth = depth;
        this.mainReply = reply;
        this.writer = createParam.getReplyWriter();

    }

    public Reply(ReplyDto.createParam createParam, Board board, Long depth){
        this.content = createParam.getContent();
        this.parentId = createParam.getReplyId();
        this.board = board;
        this.depth = depth;
        this.writer = createParam.getReplyWriter();

    }

    public static Supplier<Reply> create(ReplyDto.createParam createParam, Board board, Long depth, Reply reply){
        return () -> new Reply(createParam, board, depth, reply);
    }

    public static Supplier<Reply> createFirst(ReplyDto.createParam createParam, Board board, Long depth){
        return () -> new Reply(createParam, board, depth);
    }

    @Override
    public Supplier<Reply> identity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Reply clone(Reply e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Reply destroy(Reply reply) {
        reply.delYn = 'Y';
        reply.useYn = 'N';
        return null;
    }

    @Override
    public boolean validate(Reply e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void doSynchronizerRelationData() {
        // TODO Auto-generated method stub
        
    }
    
}
