package com.example.patidarnikunj.myapplication.permission;


/**
 * Interface used by { OnShowRationale} methods to allow for continuation
 * or cancellation of a permission request.
 */
public interface PermissionInterface {

    void onGranted(PermissionResponse permissionResponse);
}
