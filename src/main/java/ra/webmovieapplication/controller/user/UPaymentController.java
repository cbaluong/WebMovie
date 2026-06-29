package ra.webmovieapplication.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.webmovieapplication.exception.CustomException;

@Controller
@RequestMapping("/user/payment")
@RequiredArgsConstructor
public class UPaymentController {


    @GetMapping("")
    public String payment() throws CustomException {
//        model.addAttribute("filmDetail",filmService.findById(id));
        return "user/pricing";
    }
}
