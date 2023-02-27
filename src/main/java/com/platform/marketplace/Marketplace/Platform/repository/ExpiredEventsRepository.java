package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.ExpiredEvents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpiredEventsRepository extends JpaRepository<ExpiredEvents, Long> {
}
