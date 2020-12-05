package com.be.be_app.repositories;

import com.be.be_app.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleIdAndProfileProfileId(Long roleId, Long profileId);
    List<Role> findByProfileProfileId(long profileId);
    //@Query("from Role where roleId =: roleId and profileId =: profileId")
    //Optional<Role> findByRoleIdAndProfileId(@Param("roleId") Long roleId, @Param("profileId") Long profileId );
}
