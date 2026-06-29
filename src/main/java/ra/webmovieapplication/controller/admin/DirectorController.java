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
import ra.webmovieapplication.model.dto.request.DirectorRequest;
import ra.webmovieapplication.service.ICountryService;
import ra.webmovieapplication.service.IDirectorService;

@Controller
@RequestMapping("/admin/director")
@RequiredArgsConstructor
public class DirectorController {
    private final ICountryService countryService;
    private final IDirectorService directorService;

    @GetMapping("")
    public String director(@RequestParam(defaultValue = "") String search
            , @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , Model model) {
        model.addAttribute("directorList", directorService.findAllAdmin(search,pageable));
        model.addAttribute("pageNumber", pageable.getPageNumber());
        model.addAttribute("pageSize", pageable.getPageSize());

        return "admin/director/director";
    }

    @GetMapping("/add")
    public String openAddForm(Model model) {
        model.addAttribute("directorRequest", new DirectorRequest());
        model.addAttribute("countryList", countryService.findAll());
        return "admin/director/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute DirectorRequest directorRequest, BindingResult result, Model model) throws CustomException {

        if (result.hasErrors()) {
            model.addAttribute("countryList", countryService.findAll());
            return "admin/director/add";
        }

        directorService.save(directorRequest);

        return "redirect:/admin/director";
    }

    @GetMapping("/edit/{id}")
    public String openEditForm(@PathVariable Long id, Model model) throws CustomException {

        model.addAttribute("directorRequest", directorService.findById(id));
        model.addAttribute("countryId", directorService.findById(id).getCountry().getId());
        model.addAttribute("countryList", countryService.findAll());
        return "admin/director/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute DirectorRequest directorRequest, BindingResult result
            , Model model) throws CustomException {

        if (result.hasErrors()) {
//            model.addAttribute("directorRequest", directorRequest);
            model.addAttribute("countryId", directorRequest.getCountryId());
            model.addAttribute("countryList", countryService.findAll());
            return "admin/director/edit";
        }


        directorService.save(directorRequest);
        return "redirect:/admin/director";
    }

}
