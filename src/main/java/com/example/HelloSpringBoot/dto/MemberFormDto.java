package com.example.HelloSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {
    @NotEmpty(message = "이필(이름 필수라는 뜻)")
    private String name;
    @NotEmpty(message = "이필(이메일 필수라는 뜻)")
    private String email;
    @NotEmpty(message = "비필(비번 필수라는 뜻)")
    @Length(min = 8, max = 16, message = "비8이16이(비밀번호는 8자 이상 16자 이하로 작성해주세요라는 뜻)")
    private String password;
    @NotEmpty(message = "주필(주소 필수라는 뜻)")
    private String address;
}
