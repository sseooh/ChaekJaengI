package ChaekJaengI.ChaekJaengI.repository;

import ChaekJaengI.ChaekJaengI.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class JpaBoardRepository implements BoardRepository {

    @PersistenceContext
    private final EntityManager em;

    public JpaBoardRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Board> findBoardByTitle(String title) {
        List<Board> result = em.createQuery("select m from Board m where m.title = :title", Board.class)
                .setParameter("title", title)
                .getResultList();
        return result.stream().findAny();
    }

    public List<Board> findAll() {
        return em.createQuery("select m from Board m", Board.class).getResultList();
    }

    public int findAllCnt() {
        return ((Number) em.createQuery("select count(*) from Board").getSingleResult()).intValue();
    }

    public List<Board> findListPaging(int startIndex, int pageSize) {
        return em.createQuery("select b from Board b", Board.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
