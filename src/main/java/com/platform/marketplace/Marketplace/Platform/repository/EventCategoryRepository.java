package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.dto.EventCategoryDTO;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory , Long> {
    @Query("SELECT c FROM EventCategory c WHERE c.type IN :values")
    HashSet<EventCategory> findEventCategoriesByValue(@Param("values")HashSet<EventCategoryDTO>values);

    @Query("SELECT c FROM EventCategory c WHERE c.type = :value")
    EventCategory findCategoryByValue(@Param("value") String value);

}
