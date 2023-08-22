package ChaekJaengI.ChaekJaengI;

import ChaekJaengI.ChaekJaengI.repository.*;
import ChaekJaengI.ChaekJaengI.service.BoardService;
import ChaekJaengI.ChaekJaengI.service.MemberService;
import ChaekJaengI.ChaekJaengI.service.ReviewService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class springConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public springConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new JpaReviewRepository(em);
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewService(reviewRepository());
    }

    @Bean
    public BoardRepository boardRepository() {
        return new JpaBoardRepository(em);
    }

    @Bean
    public BoardService boardService() {
        return new BoardService(boardRepository());
    }

}
