package com.example.bo.model;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import com.example.bo.domain.Reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyDto {

    @Getter
    @Setter
    public static class create{

        private String content;

        private Long boardId;

        public create(String content
                    , Long boardId){
            this.content = content;
            this.boardId = boardId;
        }


        

    }

    @Getter
    @Setter
    public static class createParam{

        private Long boardId;

        private Long parentId;

        private String content;

        private Long replyId;

        private String replyWriter;

    }

    @Getter
    @Setter
    public static class list{

        //댓글 내용
        private String content;
        
        private Long replyId;
        // 댓글 계층
        private Long depth;
        // 부모댓글 참조용 키
        private Long parentId;
        // 계층형 자식 댓글들
        private List<ReplyDto.list> replys;

        private Long depthMax;

        private Long boardId;
        // 작성자
        private String replyWriter;

        private Long createDateTime;

        // private List<Reply> subReply;

        public list(
                    //  String content
                    // , Long id
                    // , Long depth
                    // , Long parentId
                    // , ReplyDto.list mainReply
                    Reply reply
                    , Long boardId
                    ){

            // this.content = content;
            // this.replyId = id;
            // this.depth = depth;
            // this.parentId = parentId;
            // this.reReplys = mainReply.getReReplys();
            this.content = reply.getContent();
            this.replyId = reply.getId();
            this.depth = reply.getDepth();
            this.parentId = reply.getParentId();
            this.replys = reply.getSubReply().stream()
                                            .filter(x -> x.getDelYn() == 'N')
                                            .map(x -> new ReplyDto.list(x, boardId))
                                            .collect(Collectors.toList());
            this.boardId = boardId;
            this.replyWriter = reply.getWriter();
            this.createDateTime = reply.getCreateDateTime().atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();

        }

    }

    @Getter
    @Setter
    public static class listParam{

        private Long boardId;

        private Long replyId;
        
    }

    @Getter
    @Setter
    public static class deleteReply{

        
    }

    @Getter
    @Setter
    public static class deleteReplyParam{
        // 삭제할 댓글의 키
        private Long replyId;
    }

    @Getter
    @Setter
    public static class deletedReply {
        // 댓글 키
        private Long replyId;

        // 부모 키
        private Long parentId;

        public deletedReply(){
            
        }
    }
    
}
