package org.step.SpringBootRepo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.step.SpringBootRepo.entities.User;

import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface UserRepository extends CommonRepository<User> {

    @Query("update User u set u.username=?1 where u.id = ?2")
    @Modifying(flushAutomatically = true)
    @QueryHints(value = {
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FLUSH_MODE, value = "AUTO")
    })
    void updateUsername(String username, Long id);

    @Query(nativeQuery = true,
            value = "SELECT * FROM users JOIN authorities a on users.id = a.user_id WHERE username=?1")
    Optional<User> findByUsername(String username);
}
