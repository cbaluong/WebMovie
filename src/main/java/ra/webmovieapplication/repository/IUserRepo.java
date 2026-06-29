package ra.webmovieapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webmovieapplication.model.entity.User;

public interface IUserRepo extends JpaRepository<User,Long> {

}
