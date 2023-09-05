package ChaekJaengI.ChaekJaengI.controller;

import ChaekJaengI.ChaekJaengI.domain.Board;
import ChaekJaengI.ChaekJaengI.domain.Member;
import ChaekJaengI.ChaekJaengI.domain.Review;
import ChaekJaengI.ChaekJaengI.service.BoardService;
import ChaekJaengI.ChaekJaengI.service.MemberService;
import ChaekJaengI.ChaekJaengI.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MyPageController {

    @Autowired
    private final MemberService memberService;

    private final BoardService boardService;

    private final ReviewService reviewService;

    @Autowired
    public MyPageController(MemberService memberService, ReviewService reviewService, BoardService boardService) {
        this.memberService = memberService;
        this.reviewService = reviewService;
        this.boardService = boardService;
    }

    @GetMapping("/myPage")
    public String myPage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("id"));

        List<Review> myReview = reviewService.getMyBook((String)session.getAttribute("id"));

        List<Board> myBook = new ArrayList<>();

        for(Review r : myReview) {
            myBook.addAll(boardService.getBookInfo(r.title).stream().collect(Collectors.toList()));

        }

        model.addAttribute("boardList", myBook);

        return "myPage";
    }

}
