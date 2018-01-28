package ru.doktorov.test2.ui.main;

import javax.inject.Inject;

import ru.doktorov.test2.service.ApiService;

public class MainPresenterImpl implements MainPresenter {
    MainView mainView;
    ApiService apiService;

    @Inject
    public MainPresenterImpl(MainView mainView, ApiService apiService) {
        this.mainView = mainView;
        this.apiService = apiService;
    }

    public void loadMain(){
        apiService.loadData();
        mainView.onMainLoaded();
    }
}
