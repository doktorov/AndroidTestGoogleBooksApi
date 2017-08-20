package ru.doktorov.test2.ui;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class BaseItemFragment extends Fragment {
    public Unbinder mUnBinder;
    public CompositeDisposable mCompositeDis = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();

        mUnBinder.unbind();
        mCompositeDis.dispose();
    }
}
