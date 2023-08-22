package ChaekJaengI.ChaekJaengI;

import chaekjaengi.chaekjaengispring.domain.Member;
import chaekjaengi.chaekjaengispring.repository.MemoryMemberRepository;
import chaekjaengi.chaekjaengispring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입(){
        Member member = new Member();

        //given
        member.setId("leejieun");
        member.setPwd("12345");

        //when
        String saveId = memberService.join(member, "12345");

        //then
        Member findMember = memberRepository.findById(saveId).get();
        assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setId("leejieun");

        Member member2 = new Member();
        member2.setId("leejieun");

        //when
        memberService.join(member1, "12345");

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2, "123"));
                                 // 뒤의 로직이 실행이 될 때 앞의 예외가 터짐
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //메세지가 같은지 확인



    }
}
