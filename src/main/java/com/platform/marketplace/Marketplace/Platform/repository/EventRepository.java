package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event , Long> {
//    @Modifying
//    @Query("UPDATE Event e SET e.isExpired = 1 WHERE e.endsAt < :now")
//    void markExpiredEvents(@Param("now") LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE e.isExpired = false")
    List<Event> findAllActiveEvents();
    @Query("SELECT e FROM Event e WHERE e.organisation.id = :id")
    List<Event> findEventsByOrganisationId(@Param("id") Long id);
    @Query("SELECT e FROM Event e WHERE e.name = :name")
    Optional<Event> findEventByName(@Param("name") String name);

    @Query("SELECT e FROM Event e WHERE e.endsAt < :now")
    List<Event> findByEndsAtBefore(@Param("now") LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE e.description LIKE' :%keyword%'")
    List<Event>findEventsByDescriptionKeyword(@Param("keyword") String keyword);

    @Query("UPDATE Event e SET e.isExpired = true WHERE e.endsAt< :now")
    void updateEventStatusWhenExpires(@Param("now") LocalDateTime now);
}
