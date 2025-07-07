package member_management.member_management_spring.controller;

import member_management.member_management_spring.domain.Member;
import member_management.member_management_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    /////////////////////////////////////////////////////////////////////////////

    @GetMapping("/members/new")
    public String createForm() {
        return "/members/createMemberForm"; // 3. 홈페이지에서 '회원 가입' 누르면, 다음 html링크로 넘어감
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);         // 4. form에서 입력한 문자열을 repository에 저장

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}