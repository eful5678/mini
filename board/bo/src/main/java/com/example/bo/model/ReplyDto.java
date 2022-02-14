package com.example.bo.model;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.bo.domain.Reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyDto {

    @Getter
    @Setter
    public static class create{

        private String content;

        public create(String content){
            this.content = content;
        }


        

    }

    @Getter
    @Setter
    public static class createParam{

        private Long boardId;

        private Long parentId;

        private String content;

        private Long replyId;

    }

    @Getter
    public static class list{

        private String content;

        private Long replyId;

        private Long depth;

        private Long parentId;

        private List<ReplyDto.list> reReplys;

        // private List<Reply> subReply;

        public list(
                    //  String content
                    // , Long id
                    // , Long depth
                    // , Long parentId
                    // , ReplyDto.list mainReply
                    Reply reply
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
            this.reReplys = reply.getSubReply().stream()
                                            .map(x -> new ReplyDto.list(x))
                                            .collect(Collectors.toList());

        }

    }

    @Getter
    @Setter
    public static class listParam{

        private Long boardId;
        
    }
    
}
