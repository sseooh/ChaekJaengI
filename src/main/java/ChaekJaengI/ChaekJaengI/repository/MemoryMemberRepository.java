package ChaekJaengI.ChaekJaengI.repository;

import ChaekJaengI.ChaekJaengI.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<String, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return (store.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny());

    }

    @Override
    public Optional<Member> findByPwd(String pwd) {
        return store.values().stream()
                .filter(member -> member.getPwd().equals(pwd))
                .findAny();
    }



    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }



}
