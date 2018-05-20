package example.com.mvvmi.earthquakelist;

//This class is where Business Logic sits

import java.util.List;

import javax.inject.Inject;

import example.com.mvvmi.domain.Earthquake;
import io.reactivex.Single;

public class EarthquakeInteractor {
    //This class is where Business Logic sits

    private final EarthquakeService earthquakeService;

    @Inject
    EarthquakeInteractor(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    Single<List<Earthquake>> loadEarthquakeList() {
        //show examples of business logic manipulations here
        return earthquakeService.getEarthquakes();
    }
}
