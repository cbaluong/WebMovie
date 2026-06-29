package ra.webmovieapplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.webmovieapplication.model.entity.Director;

public interface IDirectorRepo extends JpaRepository<Director,Long> {
    Page<Director> findAllByNameContains(String name, Pageable pageable);

    @Query("select dir.image from Director dir where dir.id= :id ")
    String getImgById(@Param("id") Long id);

}
