package ChaekJaengI.ChaekJaengI.controller;

import ChaekJaengI.ChaekJaengI.domain.Member;
import ChaekJaengI.ChaekJaengI.service.BoardService;
import ChaekJaengI.ChaekJaengI.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyPageController {

    @Autowired
    private final MemberService memberService;

    @Autowired
    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/myPage")
    public String myPage(MemberForm form, HttpServletRequest request) {

        Member loginMember = memberService.login(form.getId(), form.getPwd());


        return "myPage";
    }

}
