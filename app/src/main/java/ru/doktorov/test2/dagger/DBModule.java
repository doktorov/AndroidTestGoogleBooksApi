package ru.doktorov.test2.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.doktorov.test2.db.DBService;

@Module
public class DBModule {
    @Provides
    @Singleton
    public DBService provideDBService() {
        return new DBService();
    }
}
