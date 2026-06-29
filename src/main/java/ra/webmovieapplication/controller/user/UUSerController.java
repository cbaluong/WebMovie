package ra.webmovieapplication.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/profile")
@RequiredArgsConstructor

public class UUSerController {

    @GetMapping("")
    public String profile(){
        return "user/profile";
    }

}
