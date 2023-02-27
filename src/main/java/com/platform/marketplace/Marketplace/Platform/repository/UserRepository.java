package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    @Query("SELECT e FROM User e WHERE e.username = :email")
    Optional<User> getOptionalUserByEmail(@Param("email") String email);

    @Query("SELECT e FROM User e WHERE e.username = :email")
    User getUserByEmail(@Param("email") String email);

}
