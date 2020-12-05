package com.be.be_app.repositories;

import com.be.be_app.models.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    //Page<Profile> findByProfileId(Long profileId, Pageable pageable);
}
