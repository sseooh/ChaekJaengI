package ChaekJaengI.ChaekJaengI.repository;

import ChaekJaengI.ChaekJaengI.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    List<Board> findAll();

    Optional<Board> findBoardByTitle(String title);

    int findAllCnt();

    List<Board> findListPaging(int startIndex, int pageSize);
}
