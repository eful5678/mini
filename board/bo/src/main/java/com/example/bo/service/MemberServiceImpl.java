package com.example.bo.service;

import java.util.Collections;
import java.util.List;

import com.example.bo.domain.Member;
import com.example.bo.domain.MemberTypeEnum;
import com.example.bo.model.MemberDto;
import com.example.bo.model.MemberDto.SignUpParam;
import com.example.bo.repository.MemberRepository;
import com.example.bo.repository.MemberRepositorySupport;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final MemberRepositorySupport memberRepositorySupport;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long signUp(SignUpParam param) {
        return memberRepository.save(Member.builder()
            .username(param.getUsername())
            .name(param.getName())
            .memberType(MemberTypeEnum.NORMAL)
            .password(passwordEncoder.encode(param.getPassword()))
            .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
            .build()).getId();
        
    }

    @Override
    public List<MemberDto.list> list() {
        return memberRepositorySupport.list();
    }
    
}
