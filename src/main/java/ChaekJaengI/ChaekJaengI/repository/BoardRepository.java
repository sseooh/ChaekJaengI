package ChaekJaengI.ChaekJaengI.repository;

import ChaekJaengI.ChaekJaengI.domain.Board;
import ChaekJaengI.ChaekJaengI.domain.Review;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    List<Board> findAll();

    Optional<Board> findBoardByTitle(String title);

    int findAllCnt();

    int ReviewFindCnt(String title);

    List<Board> findListPaging(int startIndex, int pageSize);

    List<Review> findReviewListPaging(int startIndex, int pageSize, String title);
}
