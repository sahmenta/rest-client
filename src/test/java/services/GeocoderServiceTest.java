package services;

import com.trrestclient2.entities.Site;
import com.trrestclient2.services.GeocoderService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GeocoderServiceTest {
    private Logger logger = LoggerFactory.getLogger(GeocoderServiceTest.class);

    @Autowired
    private GeocoderService service;

    @Test
    void getLatLngForGoogleHeadQuarters(){
        Site google = (Site) service.getLatLng("1600, Ampitheatre Parkway", "Mountain View", "CA");
        logger.info(google.toString());
        assertEquals(37.42, google.getLatitude(), 0.01);
        assertEquals(-122.08, ((Site) google).getLongitude(), 0.01);
    }


    @Test
    void getLatLngWithoutStreet(){
        Site site = (Site) service.getLatLng("Boston", "MA");
        assertEquals(42.36, site.getLatitude(), 0.01);
        assertEquals(-71.06, site.getLongitude(), 0.01);
    }

}