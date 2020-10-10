package org.step.SpringBootRepo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.step.SpringBootRepo.entities.Profile;
import org.step.SpringBootRepo.exceptions.ProfileNotFoundException;
import org.step.SpringBootRepo.repositories.ProfileRepository;
import org.step.SpringBootRepo.services.CrudService;
import org.step.SpringBootRepo.util.DBUtil;

import java.util.List;

@Service
public class ProfileServiceImpl implements CrudService<Profile, Long> {

    private final ProfileRepository profileRepository;
    private final DBUtil<Profile> profileDBUtil;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, DBUtil<Profile> profileDBUtil) {
        this.profileRepository = profileRepository;
        this.profileDBUtil = profileDBUtil;
    }

    @Override
    @Transactional
    public Profile save(Profile profile) {
        profile.setId(profileDBUtil.generateFreeId(profileRepository));
        return profileRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Profile findById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException
                        (String.format("Profile with id %d was not found", id), id));
    }

    @Override
    @Transactional(readOnly = true)
    public Profile find(Profile profile) {
        return profileRepository.findById(profile.getId()).orElseThrow(() -> new ProfileNotFoundException
                (String.format("Profile with id %d was not found", profile.getId()), profile.getId()));
    }

    @Override
    @Transactional
    public void delete(Profile profile) {
        profileRepository.delete(profile);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, Profile profile) {
        profileRepository.updateDescription(profile.getDescription(), id);
    }
}