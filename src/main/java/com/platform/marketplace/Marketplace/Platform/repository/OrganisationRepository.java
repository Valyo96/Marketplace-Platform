package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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


}
