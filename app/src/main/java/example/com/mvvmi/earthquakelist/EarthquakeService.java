package example.com.mvvmi.earthquakelist;

import java.util.List;

import javax.inject.Inject;

import example.com.mvvmi.data.EarthQuakeListResponse;
import example.com.mvvmi.domain.Earthquake;
import io.reactivex.Observable;
import io.reactivex.Single;

/*
* Class that is responsible for fetching data from API
* */


public class EarthquakeService {

    private EarthquakeApi earthquakeApi;

    @Inject
    EarthquakeService(EarthquakeApi earthquakeApi) {
        this.earthquakeApi = earthquakeApi;
    }

    Single<List<Earthquake>> getEarthquakes() {

        // Here earthquakeApi.getEarthquakes call would return the raw response. We want to convert the response to the domain models and return that
        // Also, uses hardcoded parameters that should have been passed in for a non demo real app
        Single<EarthQuakeListResponse> apiResult = earthquakeApi.getEarthquakes("geojson", "2014-01-01", "2014-01-02", "4");

        return apiResult.flatMap(
                earthQuakeListResponse -> Observable.fromIterable(earthQuakeListResponse.features)
                        .map(item -> {
                            Earthquake eq = new Earthquake();
                            eq.setDetail(item.properties.detail);
                            eq.setPlace(item.properties.place);
                            eq.setMagnitude(item.properties.mag);
                            eq.setTime(item.properties.time);
                            eq.setUrl(item.properties.url);
                            eq.setDetail(item.properties.detail);
                            eq.setStatus(item.properties.status);
                            eq.setType(item.properties.type);
                            return eq;
                        }).toList());
    }
}
