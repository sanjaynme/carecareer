package au.com.carecareers.android.jobSearchModule;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseFragment;
import au.com.carecareers.android.injection.component.BaseComponent;

/**
 * Created by Sanjay on 11/24/2017.
 */

public class SearchFragment extends BaseFragment implements SearchContract.ISearchView {
    @Override
    public void injectComponent(BaseComponent baseComponent) {

    }

    @Override
    public void setupToolbar() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }
}