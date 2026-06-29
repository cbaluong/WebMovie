package ra.webmovieapplication.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.entity.Category;

import java.util.List;

public interface ICategoryService {
    Page<Category> findAllAdmin(String search, Pageable pageable);

    List<Category> findAll();

    Category findById(Integer id) throws CustomException;

    Category save(Category category);

    void delete(Integer id);

    boolean existsByName(String name);
}
