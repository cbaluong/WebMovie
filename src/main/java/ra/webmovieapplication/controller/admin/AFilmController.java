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
import ra.webmovieapplication.model.dto.request.FilmRequest;
import ra.webmovieapplication.model.entity.Actor;
import ra.webmovieapplication.model.entity.Category;
import ra.webmovieapplication.model.entity.Director;
import ra.webmovieapplication.service.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/film")
@RequiredArgsConstructor
public class AFilmController {
    private final IFilmService filmService;
    private final ICategoryService categoryService;
    private final ICountryService countryService;
    private final IActorService actorService;
    private final IDirectorService directorService;


    @GetMapping("")
    public String listPagination(@RequestParam(defaultValue = "") String search
            , @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , Model model) {
        model.addAttribute("filmList", filmService.findAllAdmin(search, pageable));
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("actorList", actorService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("directorList", directorService.findAll());
        model.addAttribute("pageNumber", pageable.getPageNumber());
        model.addAttribute("pageSize", pageable.getPageSize());
        return "admin/film/film";
    }

    @GetMapping("/add")
    public String openAddForm(Model model) {
        model.addAttribute("filmRequest", new FilmRequest());
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("actorList", actorService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("directorList", directorService.findAll());
        return "admin/film/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute FilmRequest filmRequest, BindingResult result, Model model) throws CustomException {

        if (result.hasErrors()) {
            model.addAttribute("countryList", countryService.findAll());
            model.addAttribute("actorList", actorService.findAll());
            model.addAttribute("categoryList", categoryService.findAll());
            model.addAttribute("directorList", directorService.findAll());
            return "admin/film/add";
        }

        filmService.save(filmRequest);

        return "redirect:/admin/film";
    }

    @GetMapping("/edit/{id}")
    public String openEditForm(@PathVariable Integer id, Model model) throws CustomException {

        model.addAttribute("filmRequest", filmService.findById(id));
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("actorList", actorService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("directorList", directorService.findAll());

        model.addAttribute("setCategoryId",filmService.findById(id).getCategorySet().stream().map(Category::getId).collect(Collectors.toSet()));
        model.addAttribute("setDirectorId",filmService.findById(id).getDirectorSet().stream().map(Director::getId).collect(Collectors.toSet()));
        model.addAttribute("setActorId",filmService.findById(id).getActorSet().stream().map(Actor::getId).collect(Collectors.toSet()));
        model.addAttribute("countryId",filmService.findById(id).getCountry().getId());
        return "admin/film/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute FilmRequest filmRequest, BindingResult result, Model model, @PathVariable Integer id) throws CustomException {
        if (result.hasErrors()) {
//            model.addAttribute("filmRequest", filmRequest);
            model.addAttribute("countryList", countryService.findAll());
            model.addAttribute("actorList", actorService.findAll());
            model.addAttribute("categoryList", categoryService.findAll());
            model.addAttribute("directorList", directorService.findAll());

            model.addAttribute("setCategoryId",filmService.findById(id).getCategorySet().stream().map(Category::getId).collect(Collectors.toSet()));
            model.addAttribute("setDirectorId",filmService.findById(id).getDirectorSet().stream().map(Director::getId).collect(Collectors.toSet()));
            model.addAttribute("setActorId",filmService.findById(id).getActorSet().stream().map(Actor::getId).collect(Collectors.toSet()));
            model.addAttribute("countryId",filmService.findById(id).getCountry().getId());
            return "admin/film/edit";
        }


        filmService.save(filmRequest);
        return "redirect:/admin/film";
    }


}
