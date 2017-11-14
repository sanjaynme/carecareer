package au.com.carecareers.android.base.presenter;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.view.IBaseView;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public interface IBasePresenter<V extends IBaseView, I extends IBaseInteractor> {
    void onAttach(V view);

    void onDetach();

    boolean isViewAttached();

    V getView();

    I getInteractor();

    CompositeDisposable getCompositeDisposable();
}
