package com.example.bo.api;

import java.util.List;

import com.example.bo.config.JwtTokenProvider;
import com.example.bo.domain.Member;
import com.example.bo.model.MemberDto;
import com.example.bo.repository.MemberRepository;
import com.example.bo.service.MemberService;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("member")
@CrossOrigin("http://localhost:8081")
@RequiredArgsConstructor
public class MemberController {

    final MemberService memberService;

    final MemberRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;



    @PostMapping("/signup")
    public Long signUp(@RequestBody MemberDto.SignUpParam param){

        return memberService.signUp(param);
    }

    @PostMapping("/login")
    public MemberDto.loginMemberResponse login(@RequestBody @NotNull MemberDto.loginMember loginMember) {
        Member member = userRepository.findByUsername(loginMember.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
        if (!passwordEncoder.matches(loginMember.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return new MemberDto.loginMemberResponse(member.getUsername(),member.getName(),"true",jwtTokenProvider.createToken(member.getUsername(), member.getRoles()));
    }

    @GetMapping("/list")
    public List<MemberDto.list> list(){
        return memberService.list();
    }
    
}
