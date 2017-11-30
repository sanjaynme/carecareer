package au.com.carecareers.android.customViews;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;

import java.io.File;

import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.utilities.FileUtils;
import au.com.carecareers.android.utilities.ViewUtils;

import static android.app.Activity.RESULT_OK;

/**
 * View less Helper fragment to :
 * 1) show chooser dialog {@link EbImageHelperFragment#showChooserDialog()}
 * 2) Crop selected image using crop view {@link EbCropImageFragment}
 * 3) compress selected image {@link au.com.carecareers.android.utilities.FileUtils#resizeAndCompressImageBeforeSend(Context, String)} )}
 * </p>
 * Any Activity/fragment using {@link EbImageHelperFragment}should implement {@link EbImageHelperListener}
 * to receive the image path.
 */
public class EbImageHelperFragment extends Fragment {
    public static final String TAG = "EbImageHelperFragment";
    private EbImageHelperListener listener;
    private boolean showCropView, compressImage;
    private String imagePath;

    /**
     * Always initialise the fragment using this method
     *
     * @param showCropView  provide option to show/hide Crop view: 0 = don't show crop view, 1= show crop view
     * @param compressImage provide option to compress selected image: 0 = don't compress, 1= compress
     * @return {@link EbImageHelperFragment}
     */
    public static EbImageHelperFragment newInstance(boolean showCropView, boolean compressImage) {
        EbImageHelperFragment fragment = new EbImageHelperFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(AppContract.Extras.EXTRA_SHOW_CROP_VIEW, showCropView);
        bundle.putBoolean(AppContract.Extras.EXTRA_COMPRESS_IMAGE, compressImage);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Set callback
     *
     * @param listener {@link  EbImageHelperListener}
     */
    public void setListener(@NonNull EbImageHelperListener listener) {
        this.listener = listener;
    }

    /**
     * Show the chooser dialog with option to "Take photo", "Choose from gallery" and "Cancel"
     */
    public void showChooserDialog() {
        final CharSequence[] items = {
                "Take Photo",
                "Choose from Gallery",
                "Cancel"};
        getActivity().getWindow().setGravity(Gravity.BOTTOM);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //Request permission to read write external storage and use camera.
                        requestPermissions(new String[]{
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA},
                                AppContract.Permission.CAMERA);
                        return;
                    }
                    openCamera();
                } else if (items[item].equals("Choose from Gallery")) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        //Request permission to read write external storage.
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                AppContract.Permission.GALLERY);
                        return;
                    }
                    openGallery();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    /**
     * Show bottomSheet dialog with option "Take Photo" or "Choose from gallery"
     */
    public void showBottomSheetFragment() {
        PickerButtonSheetFragment pickerButtonSheetFragment = PickerButtonSheetFragment.getInstance();
        pickerButtonSheetFragment.show(getActivity().getSupportFragmentManager(), pickerButtonSheetFragment.getTag());
        pickerButtonSheetFragment.setListener(new PickerButtonSheetFragment.ItemClickListener() {
            @Override
            public void onTakePhotoClicked() {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission to read write external storage and use camera.
                    requestPermissions(new String[]{
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA},
                            AppContract.Permission.CAMERA);
                    return;
                }
                openCamera();
            }

            @Override
            public void onChooseFromGalleryClicked() {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission to read write external storage.
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            AppContract.Permission.GALLERY);
                    return;
                }
                openGallery();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showCropView = getArguments().getBoolean(AppContract.Extras.EXTRA_SHOW_CROP_VIEW);
        compressImage = getArguments().getBoolean(AppContract.Extras.EXTRA_COMPRESS_IMAGE);
        setRetainInstance(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AppContract.RequestCode.GALLERY:
                    if (data.getData() != null) {
                        imagePath = FileUtils.getPath(getContext(), data.getData());
                        sendBackImagePath();
                    }
                    break;
                case AppContract.RequestCode.CAMERA:
                    sendBackImagePath();
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case AppContract.Permission.CAMERA: {
                // If request is cancelled, the results arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    ViewUtils.showToastMessage(getContext(), "You need to grant access to open camera");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case AppContract.Permission.GALLERY: {
                // If request is cancelled, the results arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    ViewUtils.showToastMessage(getContext(), "You need to grant access to read storage");
                }
                return;
            }
        }
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri outputImageUri;
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            final File photoFile = new File(FileUtils.getImageDirectory(), FileUtils.getUniqueImageName());
            imagePath = photoFile.getPath();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputImageUri = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".provider", photoFile);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                outputImageUri = Uri.fromFile(photoFile);
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputImageUri);
            // Create the File where the photo should go
            startActivityForResult(takePictureIntent, AppContract.RequestCode.CAMERA);
        } else {
            ViewUtils.showToastMessage(getContext(), "No camera app available");
        }
    }

    private void openGallery() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select File"), AppContract.RequestCode.GALLERY);
    }

    private void sendBackImagePath() {
        if (listener == null) {
            throw new IllegalArgumentException("EbImageHelperListener is not set");
        } else {
            if (imagePath != null) {
                if (showCropView) {
                    EbCropImageFragment ebCropImageFragment = EbCropImageFragment.newInstance(imagePath);
                    ebCropImageFragment.show(getActivity().getSupportFragmentManager(), EbCropImageFragment.TAG);
                    ebCropImageFragment.setCropImageListener(new EbCropImageFragment.CropImageListener() {
                        @Override
                        public void imageCropCompleted(@NonNull String croppedImagePath) {
                            if (compressImage) {
                                imagePath = FileUtils.resizeAndCompressImageBeforeSend(getContext(), croppedImagePath);
                            }
                            listener.onImageSuccess(imagePath);
                        }
                    });
                } else {
                    if (compressImage) {
                        imagePath = FileUtils.resizeAndCompressImageBeforeSend(getContext(), imagePath);
                    }
                    listener.onImageSuccess(imagePath);
                }
            } else {
                EbAlertDialog.showAlertDialog(getContext(), AppContract.Errors.IMAGE_ERROR);
            }
        }
    }

    public interface EbImageHelperListener {
        void onImageSuccess(String imagePath);
    }
}
