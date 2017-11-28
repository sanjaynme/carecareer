package au.com.carecareers.android.contracts;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public class UrlContract {
    private UrlContract() {

    }

    public static final String AUTHORIZE = "oauth/application";
    public static final String LOG_IN = "oauth/candidate";
    public static final String GETSTATES = "taxonomies/state";
    public static final String REGISTER = "candidates/register";
    public static final String FORGOT_PASSWORD = "candidates/forgot-password";
    //    public static final String PRIVACY_POLICY = "Contents/get_content";
    public static final String PRIVACY_POLICY = "contents/{type}/{id_or_slug}";
    public static final String GET_AVATARS = "avatars";//GET
    public static final String SET_AVATAR = "candidates/{" + Keys.CANDIDATE_ID + "}/avatar";//PUT
    public static final String UPLOAD_FILE = "upload-file";//POST
    public static final String LOCATION_AREA = "locations-areas";//POST


    public class Keys {
        public static final String GRANT_TYPE = "grant_type";
        public final static String AUTHORIZATION = "authorization";
        public static final String PAGE = "page";
        public static final String CANDIDATE_ID = "candidate_id";
        public static final String COUNTRY_ID = "country_id";
    }

    public class Values {
        public final static String CLIENT_CREDENTIALS = "client_credentials";
        public final static String PASSWORD = "password";
        public final static String AUTHORIZATION_VALUE = "password";
        public final static String CANDIDATE_ID = "me";//Self
        public final static String FILE = "file";//File upload
    }

}
