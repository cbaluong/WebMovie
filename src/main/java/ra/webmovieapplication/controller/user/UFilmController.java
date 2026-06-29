package ra.webmovieapplication.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.service.IFilmService;

@Controller
@RequestMapping("/film")
@RequiredArgsConstructor
public class UFilmController {
    private final IFilmService filmService;

    @GetMapping("/{id}")
    public String filmDetail(Model model, @PathVariable Integer id) throws CustomException {
        model.addAttribute("filmDetail",filmService.findById(id));
        return "user/details";
    }
}
