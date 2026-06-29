package ra.webmovieapplication.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.dto.request.ActorRequest;
import ra.webmovieapplication.model.entity.Actor;


import java.util.List;

public interface IActorService {
    Page<Actor> findAllAdmin(String search, Pageable pageable);

    List<Actor> findAll();

    Actor findById(Long id) throws CustomException;

    Actor save(ActorRequest actorRequest) throws CustomException;

    void delete(Long id);

}
