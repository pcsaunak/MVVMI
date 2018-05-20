package example.com.mvvmi.di;

/*
* Component class responsible for generating code for injecting dependency.
* */

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import example.com.mvvmi.MvvmApplication;

@Singleton
@Component(modules = {
        /* Use AndroidInjectionModule.class if you're not using support library */
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MvvmApplication application);
        AppComponent build();
    }

    void inject(MvvmApplication app);
}
