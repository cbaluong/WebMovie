package ra.webmovieapplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapplication.model.entity.Country;

public interface ICountryRepo extends JpaRepository<Country, Integer> {

    Page<Country> findAllByNameContains(String name, Pageable pageable);

    boolean existsByName(String name);

}
