package au.com.carecareers.android.loginModule.forgotPassword.model;

/**
 * Created by Sanjay on 11/21/2017.
 */

public class ForgotPasswordModel {

    public static class ForgotPasswordRequest {

        String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
