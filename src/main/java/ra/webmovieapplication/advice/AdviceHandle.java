package ra.webmovieapplication.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ra.webmovieapplication.exception.CustomException;

@ControllerAdvice
public class AdviceHandle {

    @ExceptionHandler(CustomException.class)
    public String handleError(CustomException e, Model model){
        model.addAttribute("error", e.getMessage());
        return "admin/404";
    }
}
