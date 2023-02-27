package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation , Long> {
    List<Organisation> findOrganisationsByUserId(Long id);
    Optional<Organisation> findOrganisationByUserId(Long id);
    @Query("SELECT o FROM Organisation o ORDER BY o.registeredAt DESC")
    List<Organisation> findOrganisationsByRegistrationDateDesc();



    @Query("SELECT o FROM Organisation o ORDER BY o.registeredAt ASC")
    List<Organisation> findOrganisationsByRegistrationDateAsc();

    @Modifying
    @Query("UPDATE Organisation o  SET o.organisationName = :organisationName, o.locations = :locations, o.user.username = :username, o.user.password = :password WHERE o.user.username = :currentEmail")
    void updateOrganisationAndUser(@Param("currentEmail") String currentEmail, @Param("organisationName") String organisationName, @Param("locations") List<Location> locations, @Param("username") String username, @Param("password") String password);






}
