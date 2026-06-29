package ra.webmovieapplication.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.entity.Country;
import ra.webmovieapplication.service.ICountryService;

@Controller
@RequestMapping("/admin/country")
@RequiredArgsConstructor
public class CountryController {
    private final ICountryService countryService;

    @GetMapping("")
    public String country(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(defaultValue = "") String search
            , Model model) {
        Page<Country> page = countryService.findAllAdmin(search, pageable);
        //        String sort = page.getSort().toString().replace(": ",",");
////        System.out.println(sort);
//        model.addAttribute("sort", sort);
        model.addAttribute("countryList", page);
        model.addAttribute("pageNumber", pageable.getPageNumber());
        model.addAttribute("pageSize", pageable.getPageSize());

        return "admin/country/country";
    }


    @GetMapping("/add")
    public String openAddForm(Model model) {
        model.addAttribute("country", new Country());
        return "admin/country/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Country country, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/country/add";
        }

        if (countryService.existsByName(country.getName())) {
            result.rejectValue("name", "error.country", "This country already exist");
            return "admin/country/add";
        }

        countryService.save(country);

        return "redirect:/admin/country";
    }

    @GetMapping("/edit/{id}")
    public String openEditForm(@PathVariable Integer id, Model model) throws CustomException {
        model.addAttribute("country", countryService.findById(id));
        return "admin/country/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute Country country, BindingResult result
            , @PathVariable Integer id
            , Model model) throws CustomException {

        if (result.hasErrors()) {
            model.addAttribute("country", country);
            return "admin/country/edit";
        }

        if (!country.getName().equals(countryService.findById(id).getName()) &&
                countryService.existsByName(country.getName())) {
            result.rejectValue("name", "error.country", "This country already exist");
            return "admin/country/edit";
        }

        countryService.save(country);

        return "redirect:/admin/country";
    }


}
