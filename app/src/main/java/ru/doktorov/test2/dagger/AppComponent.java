package ru.doktorov.test2.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.doktorov.test2.ui.favorites.FavoritesItemFragment;
import ru.doktorov.test2.ui.favorites.FavoritesPresenterImpl;
import ru.doktorov.test2.ui.search.SearchItemFragment;
import ru.doktorov.test2.ui.search.SearchPresenterImpl;

@Singleton
@Component(modules = {AppModule.class,
        PresenterModule.class,
        RetrofitModule.class,
        DBModule.class})
public interface AppComponent {
    void inject(SearchItemFragment searchItemFragment);

    void inject(FavoritesItemFragment favoritesItemFragment);

    void inject(SearchPresenterImpl searchPresenter);

    void inject(FavoritesPresenterImpl favoritesPresenter);
}
