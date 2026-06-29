package ra.webmovieapplication.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.dto.request.ActorRequest;
import ra.webmovieapplication.service.IActorService;
import ra.webmovieapplication.service.ICountryService;

@Controller
@RequestMapping("/admin/actor")
@RequiredArgsConstructor
public class ActorController {
    private final IActorService actorService;
    private final ICountryService countryService;

    @GetMapping("")
    public String actor(@RequestParam(defaultValue = "") String search
            , @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , Model model) {
        model.addAttribute("actorList", actorService.findAllAdmin(search, pageable));
        model.addAttribute("pageNumber", pageable.getPageNumber());
        model.addAttribute("pageSize", pageable.getPageSize());
        return "admin/actor/actor";
    }

    @GetMapping("/add")
    public String openAddForm(Model model) {
        model.addAttribute("actorRequest", new ActorRequest());
        model.addAttribute("countryList", countryService.findAll());
        return "admin/actor/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute ActorRequest actorRequest, BindingResult result, Model model) throws CustomException {

        if (result.hasErrors()) {
            model.addAttribute("countryList", countryService.findAll());
            return "admin/actor/add";
        }

        actorService.save(actorRequest);

        return "redirect:/admin/actor";
    }

    @GetMapping("/edit/{id}")
    public String openEditForm(@PathVariable Long id, Model model) throws CustomException {
        model.addAttribute("actorRequest", actorService.findById(id));
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("countryId", actorService.findById(id).getCountry().getId());
        return "admin/actor/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute ActorRequest actorRequest, BindingResult result
            ,@PathVariable Long id, Model model) throws CustomException {

        if (result.hasErrors()) {
//            model.addAttribute("actorRequest", actorRequest);
            model.addAttribute("countryId", actorRequest.getCountryId());
            model.addAttribute("countryList", countryService.findAll());
            return "admin/actor/edit";
        }

        actorService.save(actorRequest);
        return "redirect:/admin/actor";
    }
}
