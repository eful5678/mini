package com.example.bo.service;

import java.util.List;

import com.example.bo.model.MemberDto;

public interface MemberService {
    
    Long signUp(MemberDto.SignUpParam param);

    List<MemberDto.list> list();
}
