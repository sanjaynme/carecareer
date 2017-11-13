package au.com.carecareers.android.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */
@Module
public class AppModule {
    private Context mContext;

    AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

}
