package com.example.HelloSpringBoot.controller;

import com.example.HelloSpringBoot.dto.MemberFormDto;
import com.example.HelloSpringBoot.entity.Member;
import com.example.HelloSpringBoot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    //@PostMapping(value = "/new")
    //public String memberForm(MemberFormDto memberFormDto){
      //  Member member = Member.createMember(memberFormDto, passwordEncoder);
        //memberService.saveMember(member);
        //return "redirect:/";
    //}

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("memberFormDto", memberFormDto);
            return "member/memberForm";
        }
        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        }catch(IllegalArgumentException e){
            model.addAttribute("memberFormDto", memberFormDto);
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("errorMessage", "아이디 비번 틀림");
        return "member/login";
    }
}
