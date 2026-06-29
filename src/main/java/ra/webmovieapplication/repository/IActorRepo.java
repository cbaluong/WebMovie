package ra.webmovieapplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.webmovieapplication.model.entity.Actor;

public interface IActorRepo extends JpaRepository<Actor, Long> {
    Page<Actor> findAllByNameContains(String name, Pageable pageable);

    @Query("select act.image from Actor act where act.id= :id ")
    String getImgById(@Param("id") Long id);


}
