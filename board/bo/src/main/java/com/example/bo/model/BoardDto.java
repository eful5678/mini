package com.example.bo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Getter;
import lombok.Setter;

public class BoardDto {

    @Getter
    public static class BoardList{

      // 작성자
      private String writer;
      // 제목
      private String title;
      // 내용
      private String content;
      
      private Long id;
      // 작성일자
      private Long createDateTime;

      private BigDecimal like;

      public BoardList(String writer
                         , String title
                         , String content
                         , Long id
                         , LocalDateTime createDateTime
                         , BigDecimal likeCount
                        //  , LocalDateTime createDateTime
        ){
          this.writer = writer;
          this.title = title;
          this.content = content;
          this.id = id;
          this.createDateTime = createDateTime.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
          this.like = likeCount;
        }

    }

    public static class BoardListParam{

    }

    @Getter
    @Setter
    public static class BoardInfo{

      // 제목
      private String title;
      // 내용
      private String content;

      private Long id;

      public BoardInfo(String title
                      , String content
                      , Long id
                      ){
        this.title = title;
        this.content = content;
        this.id = id;
      }

    }

    @Getter
    public static class BoardCreate {
      // 제목
      private String title;

      // 작성자
      private String writer;

      public BoardCreate(String title
                        , String writer
      ){        
        this.title = title;
        this.writer = writer;
      }

    }

    @Getter
    @Setter
    public static class BoardCreateParam{
      //제목
      private String title;
      //내용
      private String content;
      // 작성자
      private String username;

    }

    @Getter
    @Setter
    public static class BoardUpdate{
      
      private String content;

      public BoardUpdate(String content){
        this.content = content;
      }

    }

    @Getter
    @Setter
    public static class BoardUpdateParam{
      // 수정할 게시글 ID값
      private Long boardId;

      // 게시글 내용
      private String content;
      
    }

    @Getter
    @Setter
    public static class BoardDelete{

    }

    @Getter
    @Setter
    public static class BoardDeleteParam{
      // 삭제할 글의 id 값
      private Long boardId;
    }

    @Getter
    @Setter
    public static class BoardLike{
      private BigDecimal like;

      public BoardLike(BigDecimal like){
        this.like = like;
      }
    }

    @Getter
    @Setter
    public static class BoardLikeParam{
      private Long boardId;
    }
}
