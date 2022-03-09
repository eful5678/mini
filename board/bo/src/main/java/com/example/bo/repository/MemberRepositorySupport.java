package com.example.bo.repository;

import java.util.List;

import com.example.bo.model.MemberDto;

public interface MemberRepositorySupport {

    String findNameByUsername(String username);

    List<MemberDto.list> list();
    
}
