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


    public class Keys {
        public static final String GRANT_TYPE = "grant_type";
        public final static String AUTHORIZATION = "authorization";
    }

    public class Values {
        public final static String CLIENT_CREDENTIALS = "client_credentials";
        public final static String PASSWORD = "password";
        public final static String AUTHORIZATION_VALUE = "password";
    }
}
