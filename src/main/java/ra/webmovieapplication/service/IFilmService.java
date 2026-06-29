package ra.webmovieapplication.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.dto.request.FilmRequest;
import ra.webmovieapplication.model.entity.Film;

import java.util.List;

public interface IFilmService {
    Page<Film> findAllAdmin(String search, Pageable pageable);

    List<Film> findAll();

    Film findById(Integer id) throws CustomException;

    Film save(FilmRequest filmRequest) throws CustomException;

    void delete(Integer id);

    List<Film> findTop5ById();

}
