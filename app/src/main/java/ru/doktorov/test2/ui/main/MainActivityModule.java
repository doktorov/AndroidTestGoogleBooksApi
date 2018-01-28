package ru.doktorov.test2.ui.main;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ru.doktorov.test2.service.ApiService;

@Module
public abstract class MainActivityModule {
    @Provides
    static MainPresenter provideMainPresenter(MainView mainView, ApiService apiService) {
        return new MainPresenterImpl(mainView, apiService);
    }

    @Binds
    abstract MainView provideMainView(MainActivity mainActivity);
}
