package ChaekJaengI.ChaekJaengI.repository;

import ChaekJaengI.ChaekJaengI.domain.Review;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaReviewRepository implements ReviewRepository{

    private final EntityManager em;

    public JpaReviewRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    @Override
    public List<Review> findByTitle(String title) {
        List<Review> result = em.createQuery("select m from Review m where m.title = :title", Review.class)
                .setParameter("title", title)
                .getResultList();
        return result;
    }
}
