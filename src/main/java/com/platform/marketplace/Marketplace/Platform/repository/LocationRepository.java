package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location , Long> {
}
