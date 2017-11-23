package au.com.carecareers.android.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import au.com.carecareers.android.R;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyConfig(Context context) {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath(context.getString(R.string.font_sourcesanpro_regular))
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
