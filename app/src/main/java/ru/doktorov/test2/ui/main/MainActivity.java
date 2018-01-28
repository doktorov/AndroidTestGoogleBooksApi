package ru.doktorov.test2.ui.main;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import ru.doktorov.test2.R;

public class MainActivity extends DaggerAppCompatActivity implements MainView {
    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mainPresenter.loadMain();
    }

    @Override
    public void onMainLoaded() {
        Log.v("TEST", "Main page is loaded.");
    }
}
