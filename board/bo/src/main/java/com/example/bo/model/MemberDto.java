package com.example.bo.model;

import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Getter;
import lombok.Setter;

public class MemberDto {
    
    @Getter
    @Setter
    public static class SingUp{

    }

    @Getter
    @Setter
    public static class SignUpParam{
        // 멤버 ID
        private String username;

        // 비밀번호
        private String password;

        // 이름
        private String name;

    }

    @Getter
    @Setter
    public static class loginMember{
        private String username;
        private String password;
        public loginMember(String username, String password){
            this.username = username;
            this.password = password;
        }
    }

    @Getter
    @Setter
    public static class loginMemberResponse{
        private String username;
        private String nickname;
        private String success;
        private String token;

        public loginMemberResponse(String username, String nickname, String success, String token){
            this.username = username;
            this.nickname = nickname;
            this.success = success;
            this.token = token;
        }
        public loginMemberResponse(String success){
            this.success = success;
        }
        public loginMemberResponse(){

        }
    }

    @Getter
    @Setter
    public static class list{
        private String username;

        private String name;

        private Long createDateTime;

        public list(String username
                    , String name
                    , LocalDateTime createDateTime
        ){
            this.username = username;
            this.name = name;
            this.createDateTime = createDateTime.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();;
        }
    }
}
