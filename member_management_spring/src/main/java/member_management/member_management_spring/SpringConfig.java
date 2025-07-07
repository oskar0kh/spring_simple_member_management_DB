package member_management.member_management_spring;

import member_management.member_management_spring.repository.MemberRepository;
import member_management.member_management_spring.repository.MemoryMemberRepository;
import member_management.member_management_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 2. Service 구현체에 Repository Bean 연결
    }                                                 // -> 나중에 Service 불러올 떄 Repository도 자동으로 불러옴

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();        // 1. 먼저 Repository 구현체의 Bean을 생성
    }                                               // (MemberRepository는 인터페이스니깐
}                                                   // 구현체인 MemoryMemberRepository 사용)