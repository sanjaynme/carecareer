package au.com.carecareers.android.contracts;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public class AppContract {
    private AppContract() {

    }

    public class ErrorTypes {
        public final static int LOGIN = 1;
        public final static int REGISTER = 2;
        public final static int TERMS_AND_CONDITIONS = 3;
        public final static int FORGOT_PASSWORD = 4;
        public final static int CHANGE_PASSWORD = 5;
        public final static int SELECT_AVATAR = 6;

    }

    public class Preferences {
        public final static String IS_LOGGED_IN = "is_logged_in";
        public final static String IS_PROFILE_COMPLETE = "is_profile_complete";
        public final static String AUTHORIZATION_KEY = "authorization_key";
        public final static String CANDIDATE_ID = "candidate_id";
        public final static String ACCESS_TOKEN = "access_token";
        public final static String EXPIRES_IN = "expires_in";
        public final static String REFRESH_TOKEN = "refresh_token";
        public final static String TOKEN_TYPE = "token_type";
        public final static String SCOPE = "scope";
        public final static String FIRST_NAME = "first_name";
        public final static String LAST_NAME = "last_name";
        public final static String LAST_LOGIN_DATE = "last_name";

        public final static String AVATAR_URL = "avatar_url";

    }

    public class Extras {
        public final static String DATA = "data";
        public static final String EXTRA_SHOW_CROP_VIEW = "show_crop_view";
        public static final String EXTRA_COMPRESS_IMAGE = "compress_image";
        public static final int STATELIST = 1;
    }

    public class Page {
        public final static String PAGE_FLAG = "page_flag";
        public final static int TERMS_AND_CONDITIONS = 1;
        public final static int PRIVACY_POLICY = 2;
    }

    public class ClientCredentials {
        public final static String CLIENT_ID = "86e84f17-3fe0-4d3a-a2fd-a93af9ff3355";
        public final static String CLIENT_SECRET = "nxKQWgERJy9ALyDjGEp4bRw4";
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

        public static final int PREFERRED_LOCATION = 201;
        public static final int SELECT_AVATAR = 202;
        public static final int LOCATION_AREA = 203;
    }

}
