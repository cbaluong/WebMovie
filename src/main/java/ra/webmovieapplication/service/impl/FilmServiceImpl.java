package ra.webmovieapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.dto.request.FilmRequest;
import ra.webmovieapplication.model.entity.Actor;
import ra.webmovieapplication.model.entity.Category;
import ra.webmovieapplication.model.entity.Director;
import ra.webmovieapplication.model.entity.Film;
import ra.webmovieapplication.repository.IFilmRepo;
import ra.webmovieapplication.service.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements IFilmService {
    private final IFilmRepo filmRepo;
    private final IDirectorService directorService;
    private final IActorService actorService;
    private final ICountryService countryService;
    private final ICategoryService categoryService;
    private final UploadService uploadService;
    private final ModelMapper modelMapper;

    @Override
    public Page<Film> findAllAdmin(String search, Pageable pageable) {
        return filmRepo.findAllByNameContains(search, pageable);
    }

    @Override
    public List<Film> findAll() {
        return filmRepo.findAll();
    }

    @Override
    public Film findById(Integer id) throws CustomException {
        return filmRepo.findById(id).orElseThrow(()-> new CustomException("Not found this film"));
    }


    @Override
    public Film save(FilmRequest filmRequest) throws CustomException {


        String imageDefault = "https://i0.wp.com/sbcf.fr/wp-content/uploads/2018/03/sbcf-default-avatar.png?ssl=1";

        Set<Category> categorySet=new HashSet<>();
        Set<Actor> actorSet=new HashSet<>();
        Set<Director> directorSet=new HashSet<>();

        categorySet= filmRequest.getCategoryId().stream().map(id -> {
            try {
                return categoryService.findById(id);
            } catch (CustomException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());

        actorSet= filmRequest.getActorsId().stream().map(id -> {
            try {
                return actorService.findById(id);
            } catch (CustomException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());



        directorSet= filmRequest.getDirectorsId().stream().map(id-> {
            try {
                return directorService.findById(id);
            } catch (CustomException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());


        Film film= modelMapper.map(filmRequest , Film.class);

        film.setStatus(true);
        film.setCountry(countryService.findById(filmRequest.getCountryId()));
        film.setCategorySet(categorySet);
        film.setActorSet(actorSet);
        film.setDirectorSet(directorSet);

        if (filmRequest.getId() == null) {
            if (filmRequest.getFile() != null && filmRequest.getFile().getSize() > 0) {
                film.setImage(uploadService.uploadFileToServer(filmRequest.getFile()));
            } else {
                film.setImage(imageDefault);
            }

        } else {
            if (filmRequest.getFile() != null && filmRequest.getFile().getSize() > 0) {
                film.setImage(uploadService.uploadFileToServer(filmRequest.getFile()));
            } else {
                film.setImage(filmRepo.getImgById(filmRequest.getId()));
            }
        }

        return filmRepo.save(film);
    }

    @Override
    public void delete(Integer id) {
        filmRepo.deleteById(id);
    }

    @Override
    public List<Film> findTop5ById() {
        return filmRepo.findTop5ByOrderByIdDesc();
    }

}
