package ChaekJaengI.ChaekJaengI.repository;

import ChaekJaengI.ChaekJaengI.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //데이터 저장
        return member;
    }


    /**
     * 여기 둘 중 뭐할지 보기
     */

    @Override
    public Optional<Member> findById(String id) {

        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
        return result.stream().findAny();

        // 2번 Member member = em.find(Member.class, id);
        //return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByPwd(String pwd) {
        List<Member> result = em.createQuery("select m from Member m where m.pwd = :pwd", Member.class)
                .setParameter("pwd", pwd)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { // 서현 추가
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

}
