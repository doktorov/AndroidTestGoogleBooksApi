package ru.doktorov.test2.ui;

import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import io.reactivex.disposables.CompositeDisposable;

public class BaseDaggerFragment extends DaggerFragment {
    public Unbinder mUnBinder;
    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();

        mUnBinder.unbind();
        mCompositeDisposable.dispose();
    }
}
