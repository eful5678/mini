package com.example.bo.domain;

import lombok.Getter;

public enum MemberTypeEnum {
    ADMIN(0, "ADMIN", "관리자"),
    NORMAL(10, "NORMAL", "일반회원");

    private int code;

    @Getter
    private String name;

    @Getter
    private String description;

    private MemberTypeEnum(int code, String name, String description){
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
