package au.com.carecareers.android.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import au.com.carecareers.android.R;


/**
 * Created by Nischal Manandhar on 15/11/2017.
 */

public class SubmitView extends FrameLayout {
    private TextView tvSubmit, tvNext, tvSkip;
    private Boolean isSubmit;
    private SubmitViewClickListener mSubmitViewClickListener;

    public SubmitView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public SubmitView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SubmitView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        inflate(context, R.layout.layout_submit, this);

        tvSubmit = findViewById(R.id.tv_submit);
        tvNext = findViewById(R.id.tv_next);
        tvSkip = findViewById(R.id.tv_skip);
        String midText;
        if (attrs == null) {
            isSubmit = true;
            midText = context.getString(R.string.tv_submit);
        } else {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SubmitView, 0, 0);
            try {
                isSubmit = ta.getBoolean(R.styleable.SubmitView_is_submit, true);
                midText = ta.getString(R.styleable.SubmitView_text_submit);
            } finally {
                ta.recycle();
            }
        }
        tvSubmit.setText(midText);
        if (isSubmit) {
            showSubmitButton();
        } else {
            showSkipAndNextButton();
        }
    }

    /**
     * Register a callback to be invoked when this view is clicked.
     *
     * @param submitViewClickListener The callback that will run
     */
    public void setOnClickListener(SubmitViewClickListener submitViewClickListener) {
        mSubmitViewClickListener = submitViewClickListener;
    }

    private void showSubmitButton() {
        tvSubmit.setVisibility(VISIBLE);
        tvSkip.setVisibility(GONE);
        tvNext.setVisibility(GONE);

        tvSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSubmitViewClickListener != null) {
                    mSubmitViewClickListener.onSubmitClicked(v);
                }
            }
        });
    }

    private void showSkipAndNextButton() {
        tvSubmit.setVisibility(GONE);
        tvSkip.setVisibility(VISIBLE);
        tvNext.setVisibility(VISIBLE);

        tvSkip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSubmitViewClickListener != null) {
                    mSubmitViewClickListener.onSkipClicked(v);
                }
            }
        });

        tvNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSubmitViewClickListener != null) {
                    mSubmitViewClickListener.onNextClicked(v);
                }
            }
        });
    }

    public interface SubmitViewClickListener {
        void onSubmitClicked(View view);

        void onSkipClicked(View view);

        void onNextClicked(View view);
    }
}