package member_management.member_management_spring.service;

import member_management.member_management_spring.domain.Member;
import member_management.member_management_spring.repository.MemberRepository;
import member_management.member_management_spring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    ////////////////////////////////////////////////////////////////////////////////////

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //-> Test 코드떄문에, 외부에서 값 넣어주도록 바꾸기
            // *

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){    // 요렇게 바꾸기!
        this.memberRepository = memberRepository;               // 그러면, Test 파일에서 실행될때마다
                                                                // 1개의 같은 저장소에서 데이터 삭제, 추가 이루어짐!
    }

    ////////////////////////////////////////////////////////////////////////////////////

    /*
        1. 회원가입 만들기
     */
    public Long join(Member member){

        duplicateMember(member);         // 중복 이름 안받음 : 같은 이름이 이미 repository에 있는지 확인

        memberRepository.save(member);

        return member.getId();
    }

    // 중복 이름 있는지 검사
    private void duplicateMember(Member member) {
        memberRepository.findByName(member.getName())                       // findByName의 return값 == Optional
            .ifPresent(m -> {                                               // -> ifPresent 사용 가능!
                throw new IllegalStateException(("이미 존재하는 회원입니다"));
            });
    }

    /*
        2. 전체 member 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*
        3. member 한명만 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
