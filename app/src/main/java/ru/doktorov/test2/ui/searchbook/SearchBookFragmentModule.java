package ru.doktorov.test2.ui.searchbook;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchBookFragmentModule {
    @Provides
    SearchBookFragmentView provideSearchBookFragmentView(SearchBookFragment searchBookFragment){
        return searchBookFragment;
    }
}
