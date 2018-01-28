package ru.doktorov.test2.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.doktorov.test2.ui.main.MainActivity;
import ru.doktorov.test2.ui.main.MainActivityModule;
import ru.doktorov.test2.ui.searchbook.SearchBookFragmentProvider;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = { MainActivityModule.class, SearchBookFragmentProvider.class })
    abstract MainActivity bindMainActivity();
}
