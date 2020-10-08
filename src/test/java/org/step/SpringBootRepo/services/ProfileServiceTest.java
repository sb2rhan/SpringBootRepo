package org.step.SpringBootRepo.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.step.SpringBootRepo.entities.Profile;
import org.step.SpringBootRepo.repositories.ProfileRepository;
import org.step.SpringBootRepo.services.impl.ProfileServiceImpl;
import org.step.SpringBootRepo.util.DBUtil;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private DBUtil<Profile> dbUtil;

    @InjectMocks
    ProfileServiceImpl profileService;

    @Test
    public void willSaveProfile() {
        final Profile profile = Profile.builder()
                .description("Any description")
                .build();

        Mockito.when(profileRepository.save(profile)).thenReturn(profile);
        Mockito.when(dbUtil.generateFreeId(profileRepository)).thenReturn(1L);

        final Profile savedP = profileService.save(profile);

        Mockito.verify(profileRepository, Mockito.atLeast(1)).save(profile);
        Mockito.verify(dbUtil, Mockito.times(1)).generateFreeId(profileRepository);

        Assertions.assertEquals(savedP, profile);
        Assertions.assertEquals((long) profile.getId(), 1L);
    }

    @Test
    public void willFindAllProfile() {
        List<Profile> profiles = List.of(
                Profile.builder().id(1L).description("1").build(),
                Profile.builder().id(2L).description("2").build());

        Mockito.when(profileRepository.findAll()).thenReturn(profiles);

        final List<Profile> found = profileService.findAll();

        Mockito.verify(profileRepository, Mockito.times(1)).findAll();

        Assertions.assertNotNull(found);
    }

    @Test
    public void willNotFindProfileById() {
        final long idT = 1L;

        Mockito.verify(profileRepository, Mockito.times(0)).findById(idT);

        Assertions.assertThrows(RuntimeException.class, () -> profileService.findById(idT));
    }
}
