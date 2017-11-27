package au.com.carecareers.android.loginModule.changePassword.model;

/**
 * Created by Sanjay on 11/21/2017.
 */

public class ChangePasswordModel {
    public static class ChangePasswordRequest {
        String password;
        String current_password;

        public String getCurrentPassword() {
            return current_password;
        }

        public void setCurrentPassword(String current_password) {
            this.current_password = current_password;
        }

        public String getNewPassword() {
            return password;
        }

        public void setNewPassword(String password) {
            this.password = password;
        }


    }
}
