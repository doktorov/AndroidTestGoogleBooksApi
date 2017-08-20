package ru.doktorov.test2.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ru.doktorov.test2.dagger.AppComponent;
import ru.doktorov.test2.dagger.AppModule;
import ru.doktorov.test2.dagger.DaggerAppComponent;
import ru.doktorov.test2.db.DBHelper;

/*
Реализовать для Android устройства простой клиент сервиса Google Books, с помощью Google Books API https://developers.google.com/books/docs/v1/using

Клиент должен иметь следующие функции:
- Поиск книг по текстовому запросу;
- Просмотр списка найденных книг (первые 10 книг);
- Возможность сохранить книгу в избранное, для последующего просмотра деталей;
- Просмотр списка избранных книг (список избранных книг должен быть доступен пользователю после перезапуска приложения).

Предусмотреть следующие элементы пользовательского интерфейса:
- Форма поиска:
    поле ввода текстового запроса;
    список найденных книг (фото-миниатюра обложки, название, автор, ссылка на загрузку фрагмента книги, кнопка для добавления/удаления книги в избранное);
    кнопка “избранные книги” (открывает список избранных книг).
- Форма со списком избранных книг:
    список книг, добавленных в избранное (фото-миниатюра обложки, название, автор, ссылка на загрузку фрагмента книги, кнопка удаления из избранного).
 */

public class AndroidTestApplication extends Application {
    private static AppComponent appComponent;

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initDagger(this);

        sContext = getApplicationContext();
    }

    protected AppComponent initDagger(AndroidTestApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static SQLiteDatabase getWritableDatabase() {
        return new DBHelper(sContext).getWritableDatabase();
    }

    public static SQLiteDatabase getReadableDatabase() {
        return new DBHelper(sContext).getReadableDatabase();
    }

    public static Context getContext() {
        return sContext;
    }
}
