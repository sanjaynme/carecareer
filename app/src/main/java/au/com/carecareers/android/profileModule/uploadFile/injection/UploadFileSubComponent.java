package au.com.carecareers.android.profileModule.uploadFile.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.uploadFile.UploadFileActivity;
import dagger.Subcomponent;


/**
 * Created by Nischal Manandhar on 04/12/2017.
 */
@ActivityScope
@Subcomponent(modules = UploadFileModule.class)
public interface UploadFileSubComponent {
    void inject(UploadFileActivity uploadFileActivity);
}
