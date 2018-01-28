package ru.doktorov.test2.ui.searchbook;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SearchBookFragmentProvider {
    @ContributesAndroidInjector(modules = SearchBookFragmentModule.class)
    abstract SearchBookFragment provideSearchBookFragmentFactory();
}
