package ra.webmovieapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.dto.request.DirectorRequest;
import ra.webmovieapplication.model.entity.Director;
import ra.webmovieapplication.repository.IDirectorRepo;
import ra.webmovieapplication.service.UploadService;
import ra.webmovieapplication.service.ICountryService;
import ra.webmovieapplication.service.IDirectorService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements IDirectorService {
    private final IDirectorRepo directorRepo;
    private final ICountryService countryService;
    private final UploadService uploadService;
    private final ModelMapper modelMapper;

    @Override
    public List<Director> findAll() {
        return directorRepo.findAll();
    }

    @Override
    public Page<Director> findAllAdmin(String search, Pageable pageable) {
        return directorRepo.findAllByNameContains(search, pageable);
    }

    @Override
    public Director findById(Long id) throws CustomException {
        return directorRepo.findById(id).orElseThrow(()-> new CustomException("Not found this director"));
    }

    @Override
    public Director save(DirectorRequest directorRequest) throws CustomException {
        String imageDefault = "https://i0.wp.com/sbcf.fr/wp-content/uploads/2018/03/sbcf-default-avatar.png?ssl=1";

        Director director= modelMapper.map(directorRequest , Director.class);

        director.setCountry(countryService.findById(directorRequest.getCountryId()));

        if (directorRequest.getId() == null) {
            if (directorRequest.getFile() != null && directorRequest.getFile().getSize() > 0) {
                director.setImage(uploadService.uploadFileToServer(directorRequest.getFile()));
            } else {
                director.setImage(imageDefault);
            }

        } else {
            if (directorRequest.getFile() != null && directorRequest.getFile().getSize() > 0) {
                director.setImage(uploadService.uploadFileToServer(directorRequest.getFile()));
            } else {
                director.setImage(directorRepo.getImgById(directorRequest.getId()));
            }
        }

        return directorRepo.save(director);
    }

    @Override
    public void delete(Long id) {
        directorRepo.deleteById(id);
    }

}
