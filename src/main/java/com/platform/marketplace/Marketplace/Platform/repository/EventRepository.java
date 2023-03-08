package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event , Long> , JpaSpecificationExecutor<Event> {

    //заявки за организации
    @Query("SELECT e FROM Event e WHERE e.organisation.id = :orgId AND e.id = :eventId")
    Optional<Event> getEventByEventIdAndOrgId(@Param("orgId")Long orgId , @Param("eventId")Long eventId);

    @Query("SELECT e FROM Event e WHERE e.organisation.id = :id")
    List<Event> findEventsByOrganisationId(@Param("id") Long id);

    //заявки за всички
    @Query("SELECT e FROM Event e WHERE e.isExpired = false AND e.isEnabled = true ORDER BY e.endsAt ASC")
    List<Event> findAllActiveEvents();

    @Query("SELECT e FROM Event e WHERE e.isExpired = true ORDER BY e.endsAt DESC")
    List<Event> findAllInactiveEvents();

    @Query("SELECT e FROM Event e WHERE e.name = :name")
    Optional<Event> findEventByName(@Param("name") String name);

    @Query("SELECT e FROM Event e WHERE e.name LIKE %:keyword% AND e.isEnabled = true AND e.isExpired = false")
    List<Event> findEventsByName(@Param("keyword")String keyword);

    @Query("SELECT e FROM Event e WHERE e.address LIKE %:keyword% AND e.isEnabled = true AND e.isExpired = false")
    List<Event> findEventsByAddress(@Param("keyword")String keyword);

    @Query("SELECT e FROM Event e JOIN e.locations loc WHERE loc.city =:city AND e.isEnabled = true AND e.isExpired = false")
    List<Event> findEventsByLocation(@Param("city") String city);

    @Query("SELECT e FROM Event e WHERE e.entranceType = :entrance AND e.isEnabled = true  AND e.isExpired = false")
    List<Event>findEventsByEntranceType(@Param("entrance")EntranceType entrance);



    @Query("SELECT e FROM Event e WHERE e.endsAt < :now")
    List<Event> findByEndsAtBefore(@Param("now") LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE e.description LIKE' :%search%' AND e.isEnabled = true  AND e.isExpired = false")
    List<Event> findEventsByDescriptionSearch(@Param("search") String search);

   @Query("SELECT e FROM Event e WHERE e.keyWords LIKE' :%keyword%' AND e.isEnabled = true  AND e.isExpired = false")
    List<Event>findEventsByKeyWords(@Param("keyword")String keyword);

    @Query("SELECT e FROM Event e WHERE e.name LIKE :keyword OR e.description LIKE :keyword OR e.address LIKE :keyword OR e.keyWords LIKE :keyword OR e.locations LIKE :keyword OR e.organisation.organisationName LIKE :keyword AND e.isEnabled = true  AND e.isExpired = false")
    List<Event> findEventsByOneStringKeyword(@Param("keyword") String keyword);

    @Query("SELECT e FROM Event e WHERE e.isEnabled = true  AND e.isExpired = false ORDER BY e.startsAt DESC ")
    List<Event> findEventsByStartDateDesc();

    @Query("SELECT e FROM Event e WHERE e.isEnabled = true AND e.isExpired = false ORDER BY e.startsAt ASC")
    List<Event>findEventsByStartDateAsc();

    @Query("SELECT e FROM Event e WHERE e.isEnabled = true AND e.isExpired = false ORDER BY e.endsAt DESC")
    List<Event>findEventsByEndDateDesc();

    @Query("SELECT e FROM Event e WHERE e.isEnabled = true  AND e.isExpired = false ORDER BY e.endsAt ASC")
    List<Event>findEventsByEndDateAsc();

    @Query("SELECT e FROM Event e WHERE e.isEnabled = true  AND e.isExpired = false ORDER BY e.createdAt ASC")
    List<Event>findNewestCreatedEvents();

    @Query("SELECT e FROM Event e WHERE e.isEnabled = true ORDER BY e.createdAt DESC")
    List<Event>findOldestCreatedEvents();

    @Query("SELECT e FROM Event e  JOIN e.eventCategories ec WHERE ec.type = :type AND e.isEnabled = true  AND e.isExpired = false")
    List<Event> findEventsByCategories(@Param("type")String type);


    //заявки за админ

}
