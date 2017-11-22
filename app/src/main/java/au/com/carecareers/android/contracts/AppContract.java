package au.com.carecareers.android.contracts;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public class AppContract {
    private AppContract() {

    }

    public class Extras {
        public final static String DATA = "data";
        public static final String EXTRA_SHOW_CROP_VIEW = "show_crop_view";
        public static final String EXTRA_COMPRESS_IMAGE = "compress_image";
    }

    public class Errors {
        public static final String IMAGE_ERROR = "Image not found";
    }

    public class Permission {
        public static final int CAMERA = 0;
        public static final int GALLERY = 1;
        public static final int LOCATION = 2;
    }

    public class RequestCode {
        public static final int CAMERA = 100;
        public static final int GALLERY = 101;

        public static final int EDIT_PROFILE = 201;
        public static final int PREFERRED_LOCATION = 202;
    }

}
