package example.com.mvvmi.earthquakelist;

/*
 * The view model class is not related to the lifecycle.
 * It withstands configuration change.
 * It gets data from interactor and sends it to view.
 * View is automatically updated when data is changed.
 * */
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import example.com.mvvmi.domain.Earthquake;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class EarthquakeListViewModel extends ViewModel {

    private final EarthquakeInteractor earthquakeInteractor;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<List<Earthquake>> earthquakeList = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();

    @Inject
    public EarthquakeListViewModel(EarthquakeInteractor earthquakeInteractor) {
        this.earthquakeInteractor = earthquakeInteractor;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    LiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    LiveData<List<Earthquake>> getEarthquakeList() {
        return earthquakeList;
    }

    LiveData<Throwable> getError() {
        return error;
    }

    void loadEarthQuakeList() {
        disposables.add(
                earthquakeInteractor.loadEarthquakeList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(s -> isLoading.setValue(true))
                        .doAfterTerminate(() -> isLoading.setValue(false))
                        .subscribe(
                                earthquakeList::setValue,
                                error::setValue
                        )
        );
    }
}
