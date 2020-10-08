package org.step.SpringBootRepo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.step.SpringBootRepo.entities.Profile;

import javax.persistence.QueryHint;

@Repository
public interface ProfileRepository extends CommonRepository<Profile> {

    @Query("update Profile p set p.description=?1 where p.id = ?2")
    @Modifying(flushAutomatically = true)
    @QueryHints(value = {
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FLUSH_MODE, value = "AUTO")
    })
    void updateDescription(String description, Long id);
}
