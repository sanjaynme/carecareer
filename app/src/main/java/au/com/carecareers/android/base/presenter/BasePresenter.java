package au.com.carecareers.android.base.presenter;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BasePresenter<V extends IBaseView, I extends IBaseInteractor> implements IBasePresenter<V, I> {
    private V mView;
    private I mInteractor;


    public BasePresenter(I interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
        mInteractor = null;
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
}
