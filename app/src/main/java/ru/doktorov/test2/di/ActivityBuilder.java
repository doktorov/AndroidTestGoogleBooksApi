package ru.doktorov.test2.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.doktorov.test2.ui.main.MainActivity;
import ru.doktorov.test2.ui.main.MainActivityModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();
//
//    @ContributesAndroidInjector(modules = {DetailActivityModule.class, DetailFragmentProvider.class})
//    abstract DetailActivity bindDetailActivity();

}
