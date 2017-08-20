package ru.doktorov.test2.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.doktorov.test2.ui.favorites.FavoritesPresenter;
import ru.doktorov.test2.ui.favorites.FavoritesPresenterImpl;
import ru.doktorov.test2.ui.search.SearchPresenter;
import ru.doktorov.test2.ui.search.SearchPresenterImpl;

@Module
public class PresenterModule {
    @Provides
    @Singleton
    SearchPresenter provideSearchPresenter() {
        return new SearchPresenterImpl();
    }

    @Provides
    @Singleton
    FavoritesPresenter provideFavoritesPresenter() {
        return new FavoritesPresenterImpl();
    }
}
