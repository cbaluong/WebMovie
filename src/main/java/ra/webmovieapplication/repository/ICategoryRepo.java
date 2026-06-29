package ra.webmovieapplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapplication.model.entity.Category;

public interface ICategoryRepo extends JpaRepository<Category,Integer> {
    Page<Category> findAllByNameContains(String name, Pageable pageable);

    boolean existsByName(String name);
    
}
