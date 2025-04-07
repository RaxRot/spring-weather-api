package com.raxrot.weather.repository;

import com.raxrot.weather.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, String> {

    @Query("SELECT l FROM Location l WHERE l.trashed = false")
    List<Location> findUnTrashed();

    @Query("SELECT l FROM Location  l where l.trashed=false and l.code=:code")
    Location findByCode(@Param("code")String code);

    @Modifying
    @Query("UPDATE Location l SET l.trashed = true WHERE l.code = :code")
     void trashedByCode(@Param("code") String code);
}
