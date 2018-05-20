package example.com.mvvmi.earthquakelist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class EarthquakeListViewModelFactory implements ViewModelProvider.Factory {

    private EarthquakeInteractor earthquakeInteractor;

    @Inject
    EarthquakeListViewModelFactory(EarthquakeInteractor earthquakeInteractor) {
        this.earthquakeInteractor = earthquakeInteractor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EarthquakeListViewModel.class)) {
            return (T) new EarthquakeListViewModel(earthquakeInteractor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
