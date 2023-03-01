package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
    @Query("SELECT o FROM Organisation o WHERE o.user.username = :email")
    Optional<Organisation> findOrganisationByEmail(@Param("email") String email);

    @Query("SELECT o FROM Organisation o WHERE o.user.id = :id")
    Optional<Organisation> findOrganisationByUserId(@Param("id") Long id);

    @Query("SELECT o FROM Organisation o ORDER BY o.registeredAt DESC")
    List<Organisation> findOrganisationsByRegistrationDateDesc();


    @Query("SELECT o FROM Organisation o ORDER BY o.registeredAt ASC")
    List<Organisation> findOrganisationsByRegistrationDateAsc();


    @Query("SELECT o FROM Organisation o WHERE o.user.isEnabled = false AND (12 * YEAR(:date) + MONTH(:date)) - (12 * YEAR(o.updatedAt) + MONTH(o.updatedAt)) >= 6")
    List<Organisation> findByIsEnabledFalseAndDisabledPeriodEquals(@Param("date") LocalDateTime date);

}
