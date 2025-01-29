package com.canary.beaches.repository;

import com.canary.beaches.model.Beach;
import com.canary.beaches.model.enums.Island;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BeachRepository extends JpaRepository<Beach, Long>, JpaSpecificationExecutor<Beach> {

    @Query(value = """
    SELECT * FROM beaches 
    WHERE (:island IS NULL OR island = :island) 
    ORDER BY RANDOM() 
    LIMIT 1
    """, nativeQuery = true)
    Optional<Beach> findRandomByIsland(@Param("island") String island);

//    @Query(value = """
//    SELECT * FROM beaches
//    WHERE earth_distance(ll_to_earth(:latitude, :longitude), ll_to_earth(latitude, longitude)) < :distance
//    ORDER BY earth_distance(ll_to_earth(:latitude, :longitude), ll_to_earth(latitude, longitude))
//    LIMIT :size OFFSET :#{#pageable.offset}
//    """,
//            countQuery = """
//    SELECT COUNT(*) FROM beaches
//    WHERE earth_distance(ll_to_earth(:latitude, :longitude), ll_to_earth(latitude, longitude)) < :distance
//    """,
//            nativeQuery = true)
//    Page<Beach> findNearbyBeaches(Double latitude, Double longitude, Integer distance, Pageable pageable);

    @Query(value = """
    SELECT *, 
    (6371 * acos(cos(radians(:latitude)) 
    * cos(radians(latitude)) 
    * cos(radians(longitude) - radians(:longitude)) 
    + sin(radians(:latitude)) 
    * sin(radians(latitude)))) 
    AS distance 
    FROM beaches 
    WHERE (6371 * acos(cos(radians(:latitude)) 
    * cos(radians(latitude)) 
    * cos(radians(longitude) - radians(:longitude)) 
    + sin(radians(:latitude)) 
    * sin(radians(latitude)))) < :radiusKm
    ORDER BY distance ASC
    """,
            countQuery = """
    SELECT COUNT(*) FROM beaches 
    WHERE (6371 * acos(cos(radians(:latitude)) 
    * cos(radians(latitude)) 
    * cos(radians(longitude) - radians(:longitude)) 
    + sin(radians(:latitude)) 
    * sin(radians(latitude)))) < :radiusKm
    """,
            nativeQuery = true)
    Page<Beach> findNearbyBeaches(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radiusKm") double radiusKm,
            Pageable pageable
    );




}
