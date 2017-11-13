package au.com.carecareers.android.customViews;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import au.com.carecareers.android.R;


/**
 * Helper class to create alert dialogs
 */

public class EbAlertDialog {

    /**
     * Shows cancellable dialog with the provided details
     *
     * @param context                    context to build the dialog
     * @param title                      title for the dialog
     * @param message                    message for the dialog
     * @param negativeButtonText         text for negative button
     * @param positiveButtonText         text for positive button
     * @param confirmationDialogCallback callback for the Ok/Cancel clicked in the dialog
     */
    public static void showCustomDialog(@NonNull Context context,
                                        @Nullable String title,
                                        @NonNull String message,
                                        @NonNull String negativeButtonText,
                                        @NonNull String positiveButtonText,
                                        @NonNull final ConfirmationDialogCallback confirmationDialogCallback) {
        //createDialog(context, title, message, negativeButtonText, positiveButtonText, true, confirmationDialogCallback);
        createDefaultConfirmationDialog(context, title, message, negativeButtonText, positiveButtonText, true, confirmationDialogCallback);
    }

    /**
     * Shows cancellable alert dialog with default title and ok button
     *
     * @param context context to build the dialog
     * @param message message for the dialog
     */
    public static void showAlertDialog(@NonNull Context context, @NonNull String message) {
        createDefaultConfirmationDialog(context, null, message, null, "OK", true, new ConfirmationDialogCallback() {
            @Override
            public void onOkClicked() {

            }

            @Override
            public void onCancelClicked() {

            }
        });
    }

    /**
     * Shows cancellable alert dialog with default title and ok button
     *
     * @param context                    context to build the dialog
     * @param message                    message for the dialog
     * @param confirmationDialogCallback callback to take action
     */
    public static void showAlertDialogWithCallback(@NonNull Context context, @NonNull String message, final ConfirmationDialogCallback confirmationDialogCallback) {
        //createDialog(context, null, message, null, "OK", true, confirmationDialogCallback);
        createDefaultConfirmationDialog(context, null, message, null, "OK", true, confirmationDialogCallback);
    }

    /**
     * Shows not cancellable alert dialog with default title and ok button
     *
     * @param context                    context to build the dialog
     * @param message                    message for the dialog
     * @param confirmationDialogCallback callback to take action
     */
    public static void showNotCancellableAlertDialog(@NonNull Context context, @NonNull String message, final ConfirmationDialogCallback confirmationDialogCallback) {
        createDefaultConfirmationDialog(context, null, message, null, "OK", false, confirmationDialogCallback);
    }

    /**
     * Shows default confirmation dialog with the provided details
     *
     * @param context                    context to build the dialog
     * @param title                      title for the dialog
     * @param message                    message for the dialog
     * @param negativeButtonText         text to show at Cancel's place
     * @param positiveButtonText         text to show at Ok's place
     * @param confirmationDialogCallback callback for the Ok/Cancel clicked in the dialog
     */
    private static void createDefaultConfirmationDialog(@NonNull Context context,
                                                        @Nullable String title,
                                                        @NonNull String message,
                                                        @Nullable String negativeButtonText,
                                                        @NonNull String positiveButtonText,
                                                        boolean isCancellable,
                                                        @Nullable final ConfirmationDialogCallback confirmationDialogCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Set dialog title
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        } else {
            builder.setTitle(context.getString(R.string.app_name));
        }

        //Set message
        builder.setMessage(message);

        //Set negative button text
        if (!TextUtils.isEmpty(negativeButtonText)) {
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (confirmationDialogCallback != null) {
                        confirmationDialogCallback.onCancelClicked();
                    }
                    dialog.dismiss();
                }
            });
        }

        //Set positive button text
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (confirmationDialogCallback != null) {
                    confirmationDialogCallback.onOkClicked();
                }
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(isCancellable);
    }

    public interface ConfirmationDialogCallback {
        void onOkClicked();

        void onCancelClicked();
    }
}
