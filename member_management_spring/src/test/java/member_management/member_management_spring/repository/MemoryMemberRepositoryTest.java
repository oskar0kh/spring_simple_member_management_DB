// MemoryMemberRepository가 들어있는 전체 main 패키지를 가져왔음
package member_management.member_management_spring.repository;

import member_management.member_management_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;

import org.springframework.util.Assert;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();     // Test 환경에서 사용할 저장소

    // 1. 1개 메서드 끝나면 repository 초기화
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    // 2. @Test를 넣으면, 바로 실행할 수 있음
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);                                    // 저장소에 member 이름 넣기
        Member result = repository.findById(member.getId()).get();  // 저장소에서 member 이름 가져오기 -> result에 저장

        // 1. junit 사용: Assertions.asserEquals(기대값, 실제값);
        //Assertions.assertEquals(member, result);                    // 기대하는값이, find한 실제값이랑 같은지 확인
                                                                      // 실제 출력되는 값은 없지만, 녹색불 들어오면 성공한거임

        // 2. assertThat 사용 : '기대값 - 실제값' 둘이 같은지 확인
        assertThat(member).isEqualTo(result);                       // 얘도 녹색불 들어오면 성공한거임
                                                                    // 주의) static import 해야함
                                                                    // import static org.assertj.core.api.Assertions.*;
                                                                    // 이 다음부터는 assertThat만 쳐서 사용 가능!
    }

    // 3. main 패키지에서 구현한 'findByName()' 메서드가 잘 작동하는지 테스트하는 메서드
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();     // findByName 사용 -> 문제 없으면 녹색불 들어올거임

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();                 // 'result' list에 'spring1', 'spring2' 들어가있음
        assertThat(result.size()).isEqualTo(2);                     // 'result' list 속 요소개수 == 2개 인지 확인
    }
}
