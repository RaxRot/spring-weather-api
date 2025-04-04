package com.raxrot.weather.repository;

import com.raxrot.weather.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}
