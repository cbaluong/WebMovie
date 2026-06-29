package ra.webmovieapplication.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.dto.request.DirectorRequest;
import ra.webmovieapplication.model.entity.Director;

import java.util.List;

public interface IDirectorService {
    List<Director> findAll();

    Page<Director> findAllAdmin(String search, Pageable pageable);

    Director findById(Long id) throws CustomException;

    Director save(DirectorRequest directorRequest) throws CustomException;

    void delete(Long id);

}
