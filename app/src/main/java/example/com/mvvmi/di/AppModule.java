package example.com.mvvmi.di;

/*
* Modules which are going to create dependency here.
* */


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.com.mvvmi.MvvmApplication;
import example.com.mvvmi.earthquakelist.EarthquakeApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    Context provideContext(MvvmApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    GsonConverterFactory providesJacksonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    EarthquakeApi provideEarthquakeService(GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov")
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(EarthquakeApi.class);
    }
}
