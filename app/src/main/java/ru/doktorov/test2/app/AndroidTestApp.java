package ru.doktorov.test2.app;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ru.doktorov.test2.di.DaggerAppComponent;
import ru.doktorov.test2.di.AppComponent;

public class AndroidTestApp  extends DaggerApplication {
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
