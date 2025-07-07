package member_management.member_management_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")            // 1. 브라우저 루트 경로(/)로 GET 요청 보냈을 때, 이 메서드 실행
    public String home() {
        return "home";          // 2. view(html 파일 이름) 반환 -> 해당 html 파일로 접근
    }
}
