package com.raxrot.weather.repository;

import com.raxrot.weather.model.Location;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class LocationRepositoryTest {

    @Autowired
    private LocationRepository repository;

    @DisplayName("Test add data in Repo")
    @Test
    public void testAddSuccess(){
        Location location = Location.builder()
                .code("NYC_USA")
                .cityName("New York City")
                .regionName("New York")
                .countryCode("US")
                .countryName("United States of America")
                .enabled(true)
                .build();

        Location savedLocation = repository.save(location);

        assertThat(savedLocation).isNotNull();
        assertThat(savedLocation.getCode()).isEqualTo("NYC_USA");
    }

    @DisplayName("Test get unTrashed data from Repo")
    @Test
    public void testUnTrashSuccess(){
        List<Location> locations = repository.findUnTrashed();
        assertThat(locations).isNotNull();
    }

    @DisplayName("Test get unTrashed data by code")
    @Test
    public void testFindByCodeSuccess(){
        String code="ABCD";
        Location location = repository.findByCode(code);
        assertThat(location).isNull();

        String code2="DELHI_IN";
        location = repository.findByCode(code2);
        assertThat(location).isNotNull();
        assertThat(location.getCode()).isEqualTo("DELHI_IN");
    }
    //write update test!!!!

    @Transactional
    @DisplayName("Test delete(send to trashed")
    @Test
    public void testDeleteSuccess(){
        String code="LACA_USA";
        repository.trashedByCode(code);
        Location location = repository.findByCode(code);
        assertThat(location).isNull();
    }
}