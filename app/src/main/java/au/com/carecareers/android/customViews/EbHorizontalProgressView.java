package au.com.carecareers.android.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import au.com.carecareers.android.R;

/**
 * Custom horizontal progress view with option to show horizontal progressbar, percentage progress
 */

public class EbHorizontalProgressView extends RelativeLayout {
    private ProgressBar pbUpload;
    private TextView tvUploadPercentage;
    private TextView tvUpload;

    public EbHorizontalProgressView(Context context) {
        super(context);
        if (!isInEditMode()) {
            initViews();
        }
    }

    public EbHorizontalProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initViews();
        }
    }

    public EbHorizontalProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            initViews();
        }
    }

    private void initViews() {
        inflate(getContext(), R.layout.layout_horizontal_progress_view, this);
        tvUpload = findViewById(R.id.tv_upload);
        tvUploadPercentage = findViewById(R.id.tv_upload_percentage);
        pbUpload = findViewById(R.id.pb_upload);

        hideProgress();
    }

    public void setProgress(int progress) {
        pbUpload.setProgress(progress);
        tvUploadPercentage.setText(progress + "%");
    }

    /**
     * Set if the progress is finite or indefinite. If param true then set progressbar indeterminate and
     * the upload text and percentage will be gone else progressbar will not be indeterminate and upload
     * text and percentage will be visible
     *
     * @param indeterminate can be true or false
     */
    private void setIndeterminate(boolean indeterminate) {
        pbUpload.setIndeterminate(indeterminate);
        if (indeterminate) {
            tvUploadPercentage.setVisibility(GONE);
            tvUpload.setVisibility(GONE);
        } else {
            tvUploadPercentage.setVisibility(VISIBLE);
            tvUpload.setVisibility(VISIBLE);
        }
    }

    /**
     * Show custom progress
     *
     * @param indeterminate can be true or false
     */
    public void showProgress(boolean indeterminate) {
        pbUpload.setVisibility(VISIBLE);
        tvUpload.setVisibility(VISIBLE);
        tvUploadPercentage.setVisibility(VISIBLE);
        setIndeterminate(indeterminate);
    }

    /**
     * Hide custom progress and reset progressbar
     */
    public void hideProgress() {
        tvUpload.setVisibility(GONE);
        tvUploadPercentage.setVisibility(GONE);
        pbUpload.setProgress(0);
        pbUpload.setVisibility(GONE);
    }
}
