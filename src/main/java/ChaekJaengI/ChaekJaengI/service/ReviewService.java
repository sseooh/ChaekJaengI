package ChaekJaengI.ChaekJaengI.service;

import ChaekJaengI.ChaekJaengI.domain.Review;
import ChaekJaengI.ChaekJaengI.repository.ReviewRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review store (Review review){
        reviewRepository.save(review);
        return review;

    }

    public List<Review> getTitleInfo(String title){
        return reviewRepository.findByTitle(title);
    }

    public List<Review> getMyBook(String id) {
        return reviewRepository.findMyBook(id);
    }
}
