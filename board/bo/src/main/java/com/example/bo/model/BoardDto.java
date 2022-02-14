package com.example.bo.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class BoardDto {

    @Getter
    public static class BoardList{

        private String writer;

        private String title;

        private String content;

        private Long id;

        public BoardList(String writer
                         , String title
                         , String content
                         , Long id
                        //  , LocalDateTime createDateTime
        ){
          this.writer = writer;
          this.title = title;
          this.content = content;
          this.id = id;
        }

    }

    public static class BoardListParam{

    }

    @Getter
    @Setter
    public static class BoardInfo{

      private String title;

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

      private String title;

      public BoardCreate(String title){        
        this.title = title;
      }

    }

    @Getter
    @Setter
    public static class BoardCreateParam{

      private String title;

      private String content;

    }
}
