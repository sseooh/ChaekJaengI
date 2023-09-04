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
    public String storeCheck(ReviewForm reviewForm, @SessionAttribute(name = "loginMember") Member member, Model model, @RequestParam(defaultValue = "1") int page)throws Exception{

        Review review = new Review();

        review.setTitle(reviewForm.getTitle());
        review.setId(member.getId());
        review.setName(reviewForm.getName());
        review.setContent(reviewForm.getContent());

        reviewService.store(review);

        board = boardService.getBookInfo(reviewForm.getTitle());

        ReviewListCnt = boardService.ReviewFindCnt(reviewForm.getTitle());

        model.addAttribute("cover", board.get().cover);
        model.addAttribute("title", board.get().title);
        model.addAttribute("author", board.get().author);
        model.addAttribute("publisher", board.get().publisher);

        Pagination_review pagination_review = new Pagination_review(ReviewListCnt, page);

        int startIndex = pagination_review.getStartIndex();

        int pageSize = pagination_review.getPageSize();


        List<Review> list = boardService.findReviewListPaging(startIndex, pageSize, board.get().title);

        model.addAttribute("list", list);
        model.addAttribute("pagination_review", pagination_review);

        return "/reviewList";
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

        model.addAttribute("cover", board.get().cover);
        model.addAttribute("title", board.get().title);
        model.addAttribute("author", board.get().author);
        model.addAttribute("publisher", board.get().publisher);

        if(ReviewListCnt==0)
            return "zeroReviewList";
        else{
            Pagination_review pagination_review = new Pagination_review(ReviewListCnt, page);

            int startIndex = pagination_review.getStartIndex();

            int pageSize = pagination_review.getPageSize();


            List<Review> list = boardService.findReviewListPaging(startIndex, pageSize, board.get().title);

            model.addAttribute("list", list);
            model.addAttribute("currentPage",page);
            model.addAttribute("pagination_review", pagination_review);

            return "reviewList";
        }
    }

    @GetMapping("/reviewList")
    public String getReviewPage(String title, Model model, @RequestParam(defaultValue = "1") int page) {
//        Optional<Board> board = boardService.getBookInfo(title);

        if (board.isPresent()){
//            int ReviewListCnt = boardService.ReviewFindCnt(title);

            model.addAttribute("cover", board.get().cover);
            model.addAttribute("title", board.get().title);
            model.addAttribute("author", board.get().author);
            model.addAttribute("publisher", board.get().publisher);

            //model.addAttribute("list",reviewService.getTitleInfo(board.get().title));

            if(ReviewListCnt==0)
                return "zeroReviewList";
            else{
                Pagination_review pagination_review = new Pagination_review(ReviewListCnt, page);

                int startIndex = pagination_review.getStartIndex();

                int pageSize = pagination_review.getPageSize();


                List<Review> list = boardService.findReviewListPaging(startIndex, pageSize, board.get().title);

                model.addAttribute("list", list);
                model.addAttribute("currentPage",page);
                model.addAttribute("pagination_review", pagination_review);

                return "reviewList";
            }

        }else{
            model.addAttribute("error", "도서 정보가 없습니다.");
            return "reviewList";
        }

    }

    @PostMapping("zeroReviewList")
    public String zeroReview(Model model) {
        model.addAttribute("cover", board.get().cover);
        model.addAttribute("title", board.get().title);
        model.addAttribute("author", board.get().author);
        model.addAttribute("publisher", board.get().publisher);
        return "/zeroReviewList";
    }

    @GetMapping("zeroReviewList")
    public String getZeroReview(Model model) {
        model.addAttribute("cover", board.get().cover);
        model.addAttribute("title", board.get().title);
        model.addAttribute("author", board.get().author);
        model.addAttribute("publisher", board.get().publisher);
        return "/zeroReviewList";
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
