package ChaekJaengI.ChaekJaengI.service;

import ChaekJaengI.ChaekJaengI.domain.Member;
import ChaekJaengI.ChaekJaengI.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

//@Service

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    public boolean idCheck;
    public boolean pwdCheck;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String join(Member member, String pwd_check){
        idCheck = validateDuplicateMember(member);
        pwdCheck = validatePwd(member, pwd_check);
        if(idCheck && pwdCheck) {
            memberRepository.save(member);
            return "success";
        } else if(idCheck) {
            return "pwdCheck fail";
        } else if(pwdCheck) {
            return "idCheck fail";
        } else {
            return "fail";
        }
    }

    private boolean validatePwd(Member member, String pwd_check) {
        if(member.getPwd().equals(pwd_check)) {
            return true;
        }else
            return false;
    }


    // 중복 검증
    private boolean validateDuplicateMember(Member member){
        if(memberRepository.findById(member.getId()).isPresent()){
            return false;
        }
        else
            return true;
    }

    public Member login(String id, String pwd) {

        // 아이디와 비밀번호 일치하는 경우 해당 Member 리턴
        // 일치하지 않는 경우 null 리턴
        return memberRepository.findById(id)
                .filter(m -> m.getPwd().equals(pwd))
                .orElse(null);

    }


    /*
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId) {
        return memberRepository.findById(memberId);
    }

     */
}
