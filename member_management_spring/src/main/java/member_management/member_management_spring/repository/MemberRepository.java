package member_management.member_management_spring.repository;

import member_management.member_management_spring.domain.Member;

import java.util.List;
import java.util.Optional;

// 4가지 기능들의 Specification 작성 -> 이 Interface를 상속하는 Class들은 해당 기능들을 구현해야함
public interface MemberRepository {
    Member save(Member member);

    // Optional : 만약 데이터 없을 때 반환되는 null을 감싸줌 -> NPE 방지
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String Name);

    List<Member> findAll();
}
