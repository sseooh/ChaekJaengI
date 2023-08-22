package ChaekJaengI.ChaekJaengI.repository;

import ChaekJaengI.ChaekJaengI.domain.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository {
    Review save (Review review);

    List<Review> findByTitle(String title);
}
