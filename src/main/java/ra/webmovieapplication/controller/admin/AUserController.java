package ra.webmovieapplication.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AUserController {
    @GetMapping("")
    public String listPagination(){
        return "admin/users";
    }
}
