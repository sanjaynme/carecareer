package au.com.carecareers.android.customViews;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import au.com.carecareers.android.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickerButtonSheetFragment extends BottomSheetDialogFragment {
    private ItemClickListener listener;

    public static PickerButtonSheetFragment getInstance() {
        return new PickerButtonSheetFragment();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.picker_bottom_sheet, null);
        dialog.setContentView(contentView);
        ButterKnife.bind(this, contentView);
    }

    @OnClick(R.id.tv_take_photo)
    public void takePhoto() {
        dismiss();
        if (listener != null) {
            listener.onTakePhotoClicked();
        }
    }

    @OnClick(R.id.tv_choose_from_gallery)
    public void chooseFromGallery() {
        dismiss();
        if (listener != null) {
            listener.onChooseFromGalleryClicked();
        }
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onTakePhotoClicked();

        void onChooseFromGalleryClicked();
    }
}
