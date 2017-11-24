package com.example.patidarnikunj.myapplication.permission;

/**
 * Created by Harsh on 2/9/2016.
 */
public class PermissionCode {

    // Permission request code
    public static final int CODE_PERMISSION_LOCATION = 0;
    public static final int CODE_PERMISSION_WRITE_STORAGE = 1;
    public static final int CODE_PERMISSION_READ_STORAGE = 2;
    public static final int CODE_PERMISSION_READ_PHONE_STATE = 3;
    public static final int CODE_PERMISSION_RECORD_AUDIO = 4;
    public static final int CODE_PERMISSIONS_REQUEST_READ_CONTACTS = 5;
    public static final int PERMISSION_REQUEST_CONTACT = 6;

    // Permission request
    public static final String[] PERMISSION_LOCATION = new String[]{"android.permission.ACCESS_FINE_LOCATION"};
    public static final String[] PERMISSION_READ_CONTACTS= new String[]{"android.permission.READ_CONTACTS"};
    public static final String[] PERMISSION_WRITE_USER_DICTIONARY = new String[]{"android.permission.WRITE_USER_DICTIONARY"};
    public static final String[] PERMISSION_WRITE_STORAGE = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"};
    public static final String[] PERMISSION_READ_STORAGE = new String[]{"android.permission.READ_EXTERNAL_STORAGE"};
    public static final String[] PERMISSION_READ_PHONE_STATE = new String[]{"android.permission.READ_PHONE_STATE"};
    public static final String[] PERMISSION_RECORD_AUDIO = new String[]{"android.permission.RECORD_AUDIO"};
}
