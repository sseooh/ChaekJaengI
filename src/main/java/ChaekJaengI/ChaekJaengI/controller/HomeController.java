package ChaekJaengI.ChaekJaengI.controller;

import ChaekJaengI.ChaekJaengI.domain.Member;
import ChaekJaengI.ChaekJaengI.web.SessionConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false)Member loginMember, Model model) {
        // 로그인 되어있지 않은 상태인 경우 로그인 화면으로 이동
        if(loginMember == null) {
            return "home";
        }

        // 로그인 되어있는 경우 메인페이지로 바로 접속
        model.addAttribute("member", loginMember);

        return "mainPage";
    }

}
