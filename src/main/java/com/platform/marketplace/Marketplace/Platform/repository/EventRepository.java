package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event , Long> {
    @Query("SELECT e FROM Event e WHERE e.organisation.id = :orgId AND e.id = :eventId")
    Optional<Event> getEventByEventIdAndOrgId(@Param("orgId")Long orgId , @Param("eventId")Long eventId);
    @Query("SELECT e FROM Event e WHERE e.isExpired = false")
    List<Event> findAllActiveEvents();
    @Query("SELECT e FROM Event e WHERE e.organisation.id = :id")
    List<Event> findEventsByOrganisationId(@Param("id") Long id);
    @Query("SELECT e FROM Event e WHERE e.name = :name")
    Optional<Event> findEventByName(@Param("name") String name);

    @Query("SELECT e FROM Event e WHERE e.endsAt < :now")
    List<Event> findByEndsAtBefore(@Param("now") LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE e.description LIKE' :%search%'")
    List<Event> findEventsByDescriptionSearch(@Param("search") String search);

   @Query("SELECT e FROM Event e WHERE e.keyWords LIKE' :%keyword%'")
    List<Event>findEventsByKeywordEvents(@Param("keyword")String keyword);

    @Query("UPDATE Event e SET e.isExpired = true WHERE e.endsAt< :now")
    void updateEventStatusWhenExpires(@Param("now") LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE e.name LIKE :keyword OR e.description LIKE :keyword OR e.address LIKE :keyword OR e.keyWords LIKE :keyword OR e.entranceType LIKE :keyword OR e.locations LIKE :keyword OR e.organisation.organisationName LIKE :keyword")
    List<Event> findEventsByOneStringKeyword(@Param("keyword") String keyword);


}
