package com.nisum.jpa.repository;

import com.nisum.jpa.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, UUID> {
    // Define any additional query methods if needed
    boolean existsByEmail(String email);
}
