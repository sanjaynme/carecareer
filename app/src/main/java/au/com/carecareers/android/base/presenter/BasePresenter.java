package au.com.carecareers.android.base.presenter;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.view.IBaseView;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BasePresenter<V extends IBaseView, I extends IBaseInteractor> implements IBasePresenter<V, I> {
    protected final String TAG = "BasePresenter";
    private V mView;
    private I mInteractor;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(I interactor, CompositeDisposable compositeDisposable) {
        mInteractor = interactor;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
        mInteractor = null;
        mCompositeDisposable.dispose();
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public I getInteractor() {
        return mInteractor;
    }

    @Override
    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }
}
