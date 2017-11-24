package com.example.patidarnikunj.myapplication.permission;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.patidarnikunj.myapplication.R;

/**
 * Created by Harsh on 2/19/2016.
 */
public class PermissionRequest {
    private static String TAG = "PermissionRequest";

    private static String[] PERMISSION_REQUEST;
    private static int PERMISSION_REQUEST_CODE;
    int tryCount = 0;
    private Activity mTarget;
    private PermissionInterface mPermissionInterface;
    private int mRetionalMessageId, mErrorMessageId;

    public PermissionRequest() {
        tryCount = 0;
    }

    public PermissionRequest(Activity activity, String[] requestPermission, int permissionRequestCode, int retionalMessageId, int errorMessageId, PermissionInterface permissionInterface) {
        mTarget = activity;
        PERMISSION_REQUEST = requestPermission;
        PERMISSION_REQUEST_CODE = permissionRequestCode;
        mRetionalMessageId = retionalMessageId;
        mErrorMessageId = errorMessageId;
        mPermissionInterface = permissionInterface;
    }

    public void checkPermission() {
        if (PermissionUtils.hasSelfPermissions(mTarget, PERMISSION_REQUEST)) {
            Log.i(TAG, "...true");
            mPermissionInterface.onGranted(new PermissionResponse(PERMISSION_REQUEST_CODE));
        } else {
            Log.i(TAG, "...false");
            neverAskAgain();
            /*if (PermissionUtils.shouldShowRequestPermissionRationale(mTarget, PERMISSION_REQUEST)) {
                showRationaleDialog(mRetionalMessageId);
            } else {
                ActivityCompat.requestPermissions(mTarget, PERMISSION_REQUEST, PERMISSION_REQUEST_CODE);
            }*/
        }
    }

    public void onRequestPermissionsResult(Activity target, int requestCode, int[] grantResults) {
        Log.i(TAG, "onRequestPermissionsResult:" + requestCode);
        if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_REQUEST)) {
            Log.i(TAG, "onRequestPermissionsResult:" + PermissionUtils.getTargetSdkVersion(target));
            showDeniedError();
            return;
        }
        if (PermissionUtils.verifyPermissions(grantResults)) {
            Log.i(TAG, "onRequestPermissionsResult:verifyPermissions: True");
            mPermissionInterface.onGranted(new PermissionResponse(PERMISSION_REQUEST_CODE));
        } else {
            tryCount++;
            Log.i(TAG, "onRequestPermissionsResult:verifyPermissions: False");
            neverAskAgain();
        }
    }

    private void showRationaleDialog(@StringRes int messageResId) {
        new AlertDialog.Builder(mTarget)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(mTarget, PERMISSION_REQUEST, PERMISSION_REQUEST_CODE);
                    }
                })
//                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(@NonNull DialogInterface dialog, int which) {
//                        showDeniedError();
//                    }
//                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    public void showDeniedError() {
        Toast.makeText(mTarget, mTarget.getResources().getString(mErrorMessageId), Toast.LENGTH_SHORT);
    }

    public void showNeverAskAgainError() {
        Toast.makeText(mTarget, mTarget.getResources().getString(R.string.permission_enable_path), Toast.LENGTH_SHORT);
    }

    private void neverAskAgain() {

        //Single Permission
//        int hasPemission = ContextCompat.checkSelfPermission(mTarget, PERMISSION_REQUEST[0]);
//        if (hasPemission != PackageManager.PERMISSION_GRANTED) {
        if (tryCount == 0) {
            Log.i(TAG, "neverAskAgain: count" + tryCount);
            ActivityCompat.requestPermissions(mTarget, PERMISSION_REQUEST, PERMISSION_REQUEST_CODE);
        } else {
            Log.i(TAG, "neverAskAgain: count" + tryCount);
            if (!ActivityCompat.shouldShowRequestPermissionRationale(mTarget, PERMISSION_REQUEST[0])) {
                Log.i(TAG, "NEVER_ASK");

                if (PERMISSION_REQUEST_CODE == PermissionCode.CODE_PERMISSION_LOCATION) {
                    showReapetedOptionDialog("" + mTarget.getResources().getString(mRetionalMessageId));
                } else if (PERMISSION_REQUEST_CODE == PermissionCode.CODE_PERMISSION_WRITE_STORAGE) {
                    showOptionDialog("" + mTarget.getResources().getString(mRetionalMessageId));
                }
                return;
            } else {
                Log.i(TAG, "DENIED");
                ActivityCompat.requestPermissions(mTarget, PERMISSION_REQUEST, PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(mTarget).setCancelable(false)
                .setTitle("Alert!")
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }

    private void showOptionDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mTarget);
        //builder.setTitle("");
        builder.setMessage("" + message);
        builder.setPositiveButton(mTarget.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int flag) {
                dialogInterface.dismiss();
                if (mTarget == null) {
                    return;
                }
                final Intent i = new Intent();
                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + mTarget.getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                mTarget.startActivity(i);
            }
        });
        builder.setNegativeButton(mTarget.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int flag) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void showReapetedOptionDialog(final String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mTarget);
        //builder.setTitle("");
        builder.setMessage("" + message);
        builder.setPositiveButton(mTarget.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int flag) {
                dialogInterface.dismiss();
                if (mTarget == null) {
                    return;
                }
                final Intent i = new Intent();
                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + mTarget.getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                mTarget.startActivity(i);
            }
        });
        builder.setNegativeButton(mTarget.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int flag) {
                showReapetedOptionDialog(message);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}