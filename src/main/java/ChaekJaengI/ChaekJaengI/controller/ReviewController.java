package ChaekJaengI.ChaekJaengI.controller;

import ChaekJaengI.ChaekJaengI.domain.*;
import ChaekJaengI.ChaekJaengI.repository.ReviewRepository;
import ChaekJaengI.ChaekJaengI.service.BoardService;
import ChaekJaengI.ChaekJaengI.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    private final BoardService boardService;

    /* 이렇게 꺼내놔도 되는건가 다시 찾아보기! */
    Optional<Board> board;
    int ReviewListCnt;



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

    @PostMapping("/reviewList")
    public String ReviewList(String title, Model model, @RequestParam(defaultValue = "1") int page){

//        Optional<Board> board = boardService.getBookInfo(title);
        board = boardService.getBookInfo(title);


//        int ReviewListCnt = boardService.ReviewFindCnt(title);
        ReviewListCnt = boardService.ReviewFindCnt(title);

        System.out.println();
        System.out.println("ReviewListCnt "+ ReviewListCnt);



        Pagination_review pagination_review = new Pagination_review(ReviewListCnt, page);

        int startIndex = pagination_review.getStartIndex();

        int pageSize = pagination_review.getPageSize();

        model.addAttribute("cover", board.get().cover);
        model.addAttribute("title", board.get().title);
        model.addAttribute("author", board.get().author);
        model.addAttribute("publisher", board.get().publisher);

        List<Review> list = boardService.findReviewListPaging(startIndex, pageSize, board.get().title);
        System.out.println();

        for (Review review : list) {
            System.out.println("Review id: " + review.getId());
        }
        System.out.println();

        model.addAttribute("list", list);
        model.addAttribute("pagination_review", pagination_review);

        return "reviewList";
    }


    @GetMapping("/reviewList")
    public String getReviewPage(String title, Model model, @RequestParam(defaultValue = "1") int page) {
//        Optional<Board> board = boardService.getBookInfo(title);

        if (board.isPresent()){
//            int ReviewListCnt = boardService.ReviewFindCnt(title);

            Pagination_review pagination_review = new Pagination_review(ReviewListCnt, page);


            int startIndex = pagination_review.getStartIndex();

            int pageSize = pagination_review.getPageSize();

            model.addAttribute("cover", board.get().cover);
            model.addAttribute("title", board.get().title);
            model.addAttribute("author", board.get().author);
            model.addAttribute("publisher", board.get().publisher);

            //model.addAttribute("list",reviewService.getTitleInfo(board.get().title));
            List<Review> list = boardService.findReviewListPaging(startIndex, pageSize, board.get().title);

            model.addAttribute("list", list);
            model.addAttribute("pagination_review", pagination_review);
        }else{
            model.addAttribute("error", "도서 정보가 없습니다.");
        }

        return "reviewList";
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
