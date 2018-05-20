package example.com.mvvmi.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import example.com.mvvmi.earthquakelist.EarthquakeListActivity;
import example.com.mvvmi.earthquakelist.EarthquakeModule;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = EarthquakeModule.class)
    abstract EarthquakeListActivity bindEarthquakeListActivity();

    // Add bindings for other sub-components here
}
