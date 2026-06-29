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
import ra.webmovieapplication.model.entity.Category;
import ra.webmovieapplication.service.ICategoryService;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class ACategoryController {

    private final ICategoryService categoryService;

    @GetMapping("")
    public String category(@RequestParam(defaultValue = "") String search
            , @PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable
            , Model model) {
        model.addAttribute("categoryList", categoryService.findAllAdmin(search, pageable));
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("pageSize",pageable.getPageSize());
        return "admin/category/catalog";
    }

    @GetMapping("/add")
    public String openAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Category category, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/category/add";
        }

        if (categoryService.existsByName(category.getName())){
            result.rejectValue("name","error.category","This category already exist");
            return "admin/category/add";
        }

        categoryService.save(category);

        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{id}")
    public String openEditForm(@PathVariable Integer id, Model model) throws CustomException {
        model.addAttribute("category", categoryService.findById(id));
        return "admin/category/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute Category category, BindingResult result, Model model, @PathVariable Integer id) throws CustomException {

        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return "admin/category/edit";
        }

        if (!category.getName().equals(categoryService.findById(id).getName()) &&
                categoryService.existsByName(category.getName())) {
            result.rejectValue("name", "error.category", "This category already exist");
            return "admin/category/edit";
        }

        categoryService.save(category);

        return "redirect:/admin/category";
    }

}
