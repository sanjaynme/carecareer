package au.com.carecareers.android.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import au.com.carecareers.android.R;

/**
 * Custom progress view with option to show progressbar, message or button to take action
 * </p>
 * Created by Nischal on 17/02/2017.
 */
public class EbProgressView extends RelativeLayout {
    private ProgressBar progressBar;
    private TextView tvMessage;
    private Button btnAction;
    Context context;

    public EbProgressView(Context context) {
        super(context);
        this.context = context;
        if (!isInEditMode()) {
            initViews();
        }
    }

    public EbProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initViews();
        }
    }

    public EbProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            initViews();
        }
    }

    private void initViews() {
        inflate(getContext(), R.layout.layout_custom_progress_view, this);
        progressBar = findViewById(R.id.progress_bar);
        tvMessage = findViewById(R.id.tv_no_data_available);
        btnAction = findViewById(R.id.btn_action);
    }

    public void showProgress() {
        progressBar.setVisibility(VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(INVISIBLE);
    }

    public void setMessage(String message) {
        tvMessage.setVisibility(VISIBLE);
        tvMessage.setText(message);
    }

    public void hideMessage() {
        tvMessage.setVisibility(INVISIBLE);
    }

    public void showActionButton() {
        btnAction.setVisibility(VISIBLE);
    }

    public void hideActionButton() {
        btnAction.setVisibility(INVISIBLE);
    }

    public void setButtonClickListener(OnClickListener clickListener) {
        btnAction.setOnClickListener(clickListener);
    }
}
