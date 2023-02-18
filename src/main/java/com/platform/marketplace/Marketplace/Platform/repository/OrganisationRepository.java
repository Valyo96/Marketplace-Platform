package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation , Long> {
}
