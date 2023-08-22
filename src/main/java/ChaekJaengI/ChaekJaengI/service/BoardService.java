package ChaekJaengI.ChaekJaengI.service;

import ChaekJaengI.ChaekJaengI.domain.Board;
import ChaekJaengI.ChaekJaengI.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Transactional
@Transactional
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /*
    public void write(Board board) {
        boardRepository.save(board);
    }

     */

    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBookInfo(String title) {
        return boardRepository.findBoardByTitle(title);
    }

    public int findAllCnt() {
        return boardRepository.findAllCnt();
    }

    public List<Board> findListPaging(int startIndex, int pageSize) {
        return boardRepository.findListPaging(startIndex, pageSize);
    }

//    public Page<Board> pageList(Pageable pageable) {
//        return boardRepository.findAll(pageable);
//    }
}
