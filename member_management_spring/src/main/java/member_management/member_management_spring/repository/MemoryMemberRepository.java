package member_management.member_management_spring.repository;

import member_management.member_management_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    // 데이터 저장용 객체 생성
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 1. store 비우는 코드
    public void clearStore() {
        store.clear();
    }

    // 2. 데이터 저장, 조회 기능 구현
    @Override
    public Member save(Member member){
        member.setId(++sequence);           // member 수 증가 (sequence가 i랑 같은 역할)
        store.put(member.getId(), member);  // (id : member 이름) 형태로 store map에 저장

        return member;
    }

    // 3. Map에서 key값인 'id'로 데이터 찾기
    @Override
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }

    // 4. stream 사용 (읽기 전용) : 입력받은 Name과 같은 이름을 가진 member 객체 찾기
    @Override
    public Optional<Member> findByName(String Name) {
        return store.values().stream()
                .filter(member ->  member.getName().equals((Name))) // 찾는 조건 : 이름이 같은가
                .findAny();                                         // 조건 만족하는 member 찾음 -> Optional로 감싸서 반환
    }

    // 5. 모든 데이터를 ArrayList 형태로 반환
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
