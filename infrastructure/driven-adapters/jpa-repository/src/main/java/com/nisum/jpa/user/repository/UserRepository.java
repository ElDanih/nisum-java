package com.nisum.jpa.user.repository;

import com.nisum.jpa.user.data.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserData, UUID> {
    boolean existsByUsername(String email);
    Optional<UserData> findByUsername(String email);
}
