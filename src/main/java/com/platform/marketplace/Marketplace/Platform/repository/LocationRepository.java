package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT c FROM Location c WHERE c.city IN :values")
    List<Location> findLocationsByValue(@Param("values") List<String>values);

    @Query("SELECT l FROM Location l WHERE l.city IN :cities AND l.id IN (SELECT a.location.id FROM Address a WHERE a.address IN :addresses)")
    List<Location> findLocationsByCityAndAddressIn(@Param("cities") List<String> cities, @Param("addresses") List<String> addresses);



}