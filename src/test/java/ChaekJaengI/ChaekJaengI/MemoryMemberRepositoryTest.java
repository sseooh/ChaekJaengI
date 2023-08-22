package ChaekJaengI.ChaekJaengI;

import chaekjaengi.chaekjaengispring.domain.Member;
import chaekjaengi.chaekjaengispring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //given
        Member member= new Member();
        member.setId("leejieun");
        member.setPwd("12345");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        Member result2 = repository.findByPwd(member.getPwd()).get();
        assertThat(member).isEqualTo(result);
        // member.getId와 result의 값이 같으면 정상 실행 (save 실행 했을 때)
        assertThat(member).isEqualTo(result2);
    }

    @Test
    public void findById(){
        Member member1 = new Member();
        member1.setId("leejieun");
        repository.save(member1);

        Member member2 = new Member();
        member2.setId("leejieun2");
        repository.save(member2);

        Member result = repository.findById("leejieun").get();
        assertThat(result).isEqualTo(member1);


    }

}
