package ChaekJaengI.ChaekJaengI.controller;

import ChaekJaengI.ChaekJaengI.domain.Board;
import ChaekJaengI.ChaekJaengI.domain.Member;
import ChaekJaengI.ChaekJaengI.domain.Review;
import ChaekJaengI.ChaekJaengI.repository.ReviewRepository;
import ChaekJaengI.ChaekJaengI.service.BoardService;
import ChaekJaengI.ChaekJaengI.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    private final BoardService boardService;


    @Autowired
    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository, BoardService boardService) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
        this.boardService = boardService;
    }


    @RequestMapping(value = "/saveReview", method = RequestMethod.POST, produces = "application/html; charset=UTF-8")
    public String storeCheck(ReviewForm reviewForm, @SessionAttribute(name = "loginMember") Member member, Model model)throws Exception{

        Review review = new Review();

        review.setTitle(reviewForm.getTitle());
        review.setId(member.getId());
        review.setName(reviewForm.getName());
        review.setContent(reviewForm.getContent());

        reviewService.store(review);

        model.addAttribute("list", boardService.boardList());

        return "/mainPage";
    }

    @PostMapping("review")
    public String getWritePage(String title, Model model) {
        model.addAttribute("title", title);
        return "/review";
    }

    @PostMapping("reviewList")
    public String getReviewPage(String title, Model model) {
        Optional<Board> board = boardService.getBookInfo(title);

        model.addAttribute("list",reviewService.getTitleInfo(board.get().title));

        model.addAttribute("cover", board.get().cover);
        model.addAttribute("title", board.get().title);
        model.addAttribute("author", board.get().author);
        model.addAttribute("publisher", board.get().publisher);

        return "/reviewList";
    }



    /*
    @ResponseBody
    @RequestMapping(value = "bookReview.write", method = RequestMethod.POST)
    public void toReview(String title, HttpServletRequest request) {
        //System.out.println(request.getParameter("title"));
        //System.out.println(title);
        //return "reviewList";
    }

     */

    /*
    @RequestMapping(value = "/review", method = {RequestMethod.POST})
    public String test(@ModelAttribute("title") @RequestParam("title") String bookTitle) {
        System.out.println(bookTitle);
        return "redirect:/";
    }

     */

}
