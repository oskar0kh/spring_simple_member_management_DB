package member_management.member_management_spring.service;

import member_management.member_management_spring.domain.Member;
import member_management.member_management_spring.repository.MemoryMemberRepository;
import member_management.member_management_spring.repository.MemoryMemberRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    ////////////////////////////////////////////////////////////////////////////////////

    MemberService memberService;
    MemoryMemberRepository memberRepository; // Test에서 새로 repository를 만들면,
                                             // 기존의 MemberService에서 만든 repository와 다른 새로운 repository가 생성됨
                                             // ∴ 저장소가 달라짐! -> 내부 데이터가 다름!!

                                             // ∴ MemberService에서 new 부분 삭제
                                             // -> 외부에서 값 넣어주도록 바꾸기

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    ////////////////////////////////////////////////////////////////////////////////////

    // 1. 1개 메서드 끝나면 repository 초기화
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();      // 매번 memberRepository 초기화
    }

    // 2. 회원가입 잘 되는지 테스트
    @Test
    void join() {
        // given : 테스트에 필요한 입력값
        Member member = new Member();
        member.setName("hello");

        // when : 테스트할 메서드 (join)
        Long savedId = memberService.join(member);

        // then : 결과값이 올바른지 확인 (assertThat 사용)
        Member findMember = memberService.findOne(savedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 3. 중복 회원이 저장되는지 확인 (예외 상황 확인)
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

            // 1. try-catch 문법 사용 : 중복 이름 확인
        /*try{
            memberService.join(member2);        // 중복 이름이 들어가는 상황
        } catch(IllegalStateException e) {      // 예외 발생하면, catch 내부 로직 실행
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");  // exception 메시지가 같은지 확인
        }*/

            // 2. assertThrow 문법 사용

        // member2를 join 했을 때, IllegalStateException 오류가 터져야 한다!
        // exception 문구를 e에 저장하고, 나중에 assert할때 비교!
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }
}