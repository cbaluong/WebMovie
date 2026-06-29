package ra.webmovieapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.dto.request.ActorRequest;
import ra.webmovieapplication.model.entity.Actor;
import ra.webmovieapplication.repository.IActorRepo;
import ra.webmovieapplication.service.UploadService;
import ra.webmovieapplication.service.IActorService;
import ra.webmovieapplication.service.ICountryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements IActorService {
    private final IActorRepo actorRepo;
    private final ICountryService countryService;
    private final UploadService uploadService;

    @Override
    public Page<Actor> findAllAdmin(String search, Pageable pageable) {
        return actorRepo.findAllByNameContains(search, pageable);
    }

    @Override
    public List<Actor> findAll() {
        return actorRepo.findAll();
    }

    @Override
    public Actor findById(Long id) throws CustomException {
        return actorRepo.findById(id).orElseThrow(()-> new CustomException("Not found this actor"));
    }

    @Override
    public Actor save(ActorRequest actorRequest) throws CustomException {

        String imageDefault = "https://i0.wp.com/sbcf.fr/wp-content/uploads/2018/03/sbcf-default-avatar.png?ssl=1";

        Actor actor = Actor.builder()
                .id(actorRequest.getId())
                .name(actorRequest.getName())
                .description(actorRequest.getDescription())
                .country(countryService.findById(actorRequest.getCountryId()))
                .dob(actorRequest.getDob())
                .gender(actorRequest.getGender())
                .build();

        if (actorRequest.getId() == null) {
            if (actorRequest.getFile() != null && actorRequest.getFile().getSize() > 0) {
                actor.setImage(uploadService.uploadFileToServer(actorRequest.getFile()));
            } else {
                actor.setImage(imageDefault);
            }

        } else {
            if (actorRequest.getFile() != null && actorRequest.getFile().getSize() > 0) {
                actor.setImage(uploadService.uploadFileToServer(actorRequest.getFile()));
            } else {
                actor.setImage(actorRepo.getImgById(actorRequest.getId()));
            }
        }

        return actorRepo.save(actor);
    }

    @Override
    public void delete(Long id) {
        actorRepo.deleteById(id);
    }

}
