package member_management.member_management_spring.controller;

import member_management.member_management_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    
    // 1. 기존 객체 생성 방식 : new 사용
    // private final MemberService memberService = new MemberService();
    // -> new 부분은, MemberService라는 객체를 우리가 직접 생성한다는 의미임

    // 2. @Autowired를 사용하면, 우리가 객체를 직접 생성 안해도, spring이 알아서 객체를 생성해줌

    private final MemberService memberService;

                                                            // < Dependency Injection (DI) >
    @Autowired                                              // spring container에서 MemberService 객체 가져와서 넣어줌
    public MemberController(MemberService memberService){   // MemberController 클래스의 생성자
        this.memberService = memberService;                 // -> controller랑 service 객체 연결!
    }
}