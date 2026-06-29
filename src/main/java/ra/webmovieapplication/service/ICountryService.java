package ra.webmovieapplication.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.entity.Country;

import java.util.List;


public interface ICountryService {
//    Page<Country> findAllPagination(Pageable pageable);

    Page<Country> findAllAdmin(String search, Pageable pageable);

    List<Country> findAll();

    Country findById(Integer id) throws CustomException;

    Country save(Country country);

    void delete(Integer id);

    boolean existsByName(String name);
}
