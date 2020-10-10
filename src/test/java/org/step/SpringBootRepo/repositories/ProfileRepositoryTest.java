package org.step.SpringBootRepo.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.step.SpringBootRepo.entities.Profile;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = {"classpath:db/test_files/setupTestProfile.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = {"classpath:db/test_files/cleanTestProfile.sql"})
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    private final Long testId = 1L;

    @Test
    public void willFindAll() {
        final List<Profile> all = profileRepository.findAll();

        Assertions.assertNotNull(all);
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    public void willFindById() {
        final Profile profile = profileRepository.findById(testId).orElseThrow(RuntimeException::new);

        Assertions.assertNotNull(profile);
        System.out.println(profile.getDescription());
    }

    @Test
    public void willUpdateDescription() {
        final String newDesc = "New description";
        profileRepository.updateDescription(newDesc, testId);
        final Profile dbProfile = profileRepository.findById(testId).orElseThrow(RuntimeException::new);

        Assertions.assertNotNull(dbProfile);
        System.out.println(dbProfile.getDescription());
        Assertions.assertEquals(newDesc, dbProfile.getDescription());
    }

    @Test
    public void willDelete() {
        profileRepository.deleteById(testId);

        Optional<Profile> byId = profileRepository.findById(testId);

        Assertions.assertTrue(byId.isEmpty());
    }

    @Test
    public void willSave() {
        Profile profile = Profile.builder()
                .id(4L)
                .description("Save test profile")
                .build();

        final Profile save = profileRepository.save(profile);
    }

    @Test
    public void willSortByDescription() {
        Sort sort = Sort.by("description");
        final List<Profile> sortedProfiles = profileRepository.findAll(sort);

        Assertions.assertNotNull(sortedProfiles);
        Assertions.assertFalse(sortedProfiles.isEmpty());

        sortedProfiles.forEach(System.out::println);
    }
}
