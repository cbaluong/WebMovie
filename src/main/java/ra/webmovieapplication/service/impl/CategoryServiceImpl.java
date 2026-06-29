package ra.webmovieapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.entity.Category;

import ra.webmovieapplication.repository.ICategoryRepo;
import ra.webmovieapplication.service.ICategoryService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepo categoryRepo;

    @Override
    public Page<Category> findAllAdmin(String search, Pageable pageable) {
        return categoryRepo.findAllByNameContains(search,pageable);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Integer id) throws CustomException {
        return categoryRepo.findById(id).orElseThrow(()-> new CustomException("Not found this category"));
    }

    @Override
    public Category save(Category category) {
        category.setStatus(true);
        return categoryRepo.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepo.existsByName(name);
    }
}
