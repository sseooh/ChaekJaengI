package ChaekJaengI.ChaekJaengI.repository;

import ChaekJaengI.ChaekJaengI.domain.Board;
import ChaekJaengI.ChaekJaengI.domain.Review;

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

    public int ReviewFindCnt(String title) {
        return ((Number) em.createQuery("select count(*) from Review m where m.title = :title")
                .setParameter("title", title)
                .getSingleResult()).intValue();
    }


    public List<Board> findListPaging(int startIndex, int pageSize) {
        return em.createQuery("select b from Board b", Board.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }


    public List<Review> findReviewListPaging(int startIndex, int pageSize, String title) {
        return em.createQuery("select b from Review b where b.title = :title", Review.class)
                .setParameter("title", title)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }





}
