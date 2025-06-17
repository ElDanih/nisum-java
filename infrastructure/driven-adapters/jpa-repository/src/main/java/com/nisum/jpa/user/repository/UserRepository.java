package com.nisum.jpa.user.repository;

import com.nisum.jpa.user.data.UserData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserData, UUID> {
    boolean existsByUsername(String email);
    Optional<UserData> findByUsername(String email);
    List<UserData> findAll();

    @Modifying
    @Query(value = "UPDATE users SET last_login = CURRENT_TIMESTAMP WHERE username = :username", nativeQuery = true)
    void updateLastLoginByUsername(@Param("username") String username);

    @Modifying
    @Query(value = "UPDATE users SET is_active = false WHERE username = :username", nativeQuery = true)
    int inactivateUserByUsername(@Param("username") String username);
}
