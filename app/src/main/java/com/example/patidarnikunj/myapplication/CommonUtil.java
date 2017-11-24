package com.example.patidarnikunj.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Harsh on 2/6/2016.
 */
public class CommonUtil {

    /* Includes :
      getIMEI
      getOSversion
      getDeviceName
      getOSname
      getAppVersion
      getPackagename
      getFacebookSHA
      getFacebookUrl
      getLanguage
      getCurrentTime
      getCurrentDate
      getDateConversation
      getMilliSeconds
      getMilliSecondsFromCurrentDate
      dpTopx
      pxToDp
      getLocation
      getURLFromParams (For volly library get method)
      checkEmail
      checkPassword
      isValidUrl
      isValidNumeric
      isNullString
      stringToInt
      stringToDouble
      getTwoDigitTime
      isInternetAvailable
      convertListToArray
      convertArrayToList
      checkGPS
      showGPSDisabledAlertToUser
      showSoftKeyboard
      hideSoftKeyboard
      get_Picture_bitmap
      getFileSize
      getUTFEncodedString*/

    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$");
    public final static Pattern PASSWORD_VALIDATION = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z*\\d]).{6,20})");
    public final static Pattern WEB_URL_PATTERN = Patterns.WEB_URL;
    public final static Pattern NUMERIC_PATTERN = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");

    public static String getOSversion() {
        return Build.VERSION.RELEASE;
    }

    public static String getAppVersion(Context context) {
        PackageInfo pInfo = null;
        String version = "";
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
            Log.i("version", "" + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static String getPackagename(Context context) {
        return context.getPackageName();
    }

    public static void getFacebookSHA(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "com.daydate", PackageManager.GET_SIGNATURES); //Your package name here
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public static String getLanguage() {
        String lang = Locale.getDefault().getLanguage();
        if (lang.equalsIgnoreCase("fr")) {
            lang = "fr";
        } else {
            lang = "en";
        }
        return lang;
    }
    public static boolean isInternetAvailableWithNoToast(Context context) {
        ConnectionDetector cd = new ConnectionDetector(context);
        Boolean isInternetPresent = cd.isConnectingToInternet();

        if (!isInternetPresent) {
            //ToastUtil.show(context, " "+context.getResources().getString(R.string.toast_no_internet));
            return false;
        } else {
            return isInternetPresent;
        }
    }

    public static String getDateConversation(String date, DateFormat originalFormat, DateFormat targetFormat) {
        String formattedDate = "";
        try {
            Date d = originalFormat.parse(date);
            formattedDate = targetFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static long getMilliSeconds(String dateFormate, String date) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormate, Locale.ENGLISH);
        formatter.setLenient(false);
        Date oldDate = null;
        try {
            oldDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return oldDate.getTime();
    }

    /*public static String getFacebookUrl(String fbId, String fbUrl) {
        String mFbUrl = "";

        mFbUrl = fbUrl.replace(ApiManager.BASE_FB_ID, fbId);
        LogUtil.i("fbUrl", "" + mFbUrl);

        return mFbUrl;
    }*/

    public static void getDatePickerDialog(final TextView textView, Context context) {

        Calendar newCalendar = Calendar.getInstance();
        int mYear = newCalendar.get(Calendar.YEAR);
        int mMonth = newCalendar.get(Calendar.MONTH);
        int mDay = newCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                textView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }

        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public static void getTimePickerDialog(final TextView textView, Context context) {

        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        textView.setText("AT" + hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        tpd.show();
    }

    public static long getMilliSecondsFromCurrentDate(String dateFormate, String date) {
        Date futureDate = null;
        try {
            futureDate = new SimpleDateFormat(dateFormate, Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long milliseconds = futureDate.getTime();
        long millisecondsFromNow = milliseconds - (new Date()).getTime();
        return millisecondsFromNow;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static String getLocation(Context context, String latitude, String longitude) {
        String location = "";
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            location = "" + address + ", " + state + ", " + country + ", " + postalCode;
            location = location.replace(", null", "");
            location = location.replace("null", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }

    public static String getDateFormatedTime(String time, String iHaveFormat, String iWantFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(iHaveFormat, Locale.ENGLISH);
            Date date = (Date) formatter.parse(time);
            SimpleDateFormat newFormat = new SimpleDateFormat(iWantFormat, Locale.ENGLISH);
            String finalString = newFormat.format(date);
            return finalString;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getURLFromParams(String url, Map<String, String> params) {
        Iterator entries = params.entrySet().iterator();
        int i = 0;
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (i == 0) {
                url = url + key + "=" + value;
            } else {
                url = url + "&" + key + "=" + value;
            }
            i++;
        }
        return url;
    }

    public static boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static boolean checkPassword(String password) {
        return PASSWORD_VALIDATION.matcher(password).matches();
    }

    public static boolean isValidUrl(String url) {
        Matcher m = WEB_URL_PATTERN.matcher(url);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static boolean isValidNumeric(String number) {
        boolean isValid = false;
        CharSequence inputStr = number;
        Matcher matcher = NUMERIC_PATTERN.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isNullString(String string) {
        try {
            if (string.trim().equalsIgnoreCase("null") || string.trim() == null || string.trim().length() < 0 || string.trim().equals("")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public static int stringToInt(String str) {
        return Integer.parseInt(str);
    }

    public static double stringToDouble(String str) {
        return Double.parseDouble(str);
    }

    public static String getTwoDigitTime(int string) {
        try {
            if (String.valueOf(string).length() == 1) {
                return "0" + string;
            } else {
                return "" + string;
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isInternetAvailable(Context context) {
        ConnectionDetector cd = new ConnectionDetector(context);
        Boolean isInternetPresent = cd.isConnectingToInternet();

        if (!isInternetPresent) {
            //ToastUtil.show(context, " "+context.getResources().getString(R.string.toast_no_internet));
            return false;
        } else {
            return isInternetPresent;
        }
    }

    public static String[] convertListToArry(ArrayList<String> data) {
        String[] array = new String[data.size()];
        return array = data.toArray(array);
    }

    public static ArrayList<String> convertArryToList(String[] arry) {
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < arry.length; i++) {
            data.add(arry[i]);
        }
        return data;
    }

    public static void showSoftKeyboard(Context context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideSoftKeyboard(Context context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap get_Picture_bitmap(String imagePath) {

        long size_file = getFileSize(new File(imagePath));

        size_file = (size_file) / 1000;// in Kb now
        int ample_size = 1;

        if (size_file <= 250) {

            System.out.println("SSSSS1111= " + size_file);
            ample_size = 2;

        } else if (size_file > 251 && size_file < 1500) {

            System.out.println("SSSSS2222= " + size_file);
            ample_size = 4;

        } else if (size_file >= 1500 && size_file < 3000) {

            System.out.println("SSSSS3333= " + size_file);
            ample_size = 8;

        } else if (size_file >= 3000 && size_file <= 4500) {

            System.out.println("SSSSS4444= " + size_file);
            ample_size = 12;

        } else if (size_file >= 4500) {

            System.out.println("SSSSS4444= " + size_file);
            ample_size = 16;
        }

        Bitmap bitmap = null;

        BitmapFactory.Options bitoption = new BitmapFactory.Options();
        bitoption.inSampleSize = ample_size;

        Bitmap bitmapPhoto = BitmapFactory.decodeFile(imagePath, bitoption);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        int orientation = exif
                .getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        Matrix matrix = new Matrix();

        if ((orientation == 3)) {
            matrix.postRotate(180);
            bitmap = Bitmap.createBitmap(bitmapPhoto, 0, 0,
                    bitmapPhoto.getWidth(), bitmapPhoto.getHeight(), matrix,
                    true);

        } else if (orientation == 6) {
            matrix.postRotate(90);
            bitmap = Bitmap.createBitmap(bitmapPhoto, 0, 0,
                    bitmapPhoto.getWidth(), bitmapPhoto.getHeight(), matrix,
                    true);

        } else if (orientation == 8) {
            matrix.postRotate(270);
            bitmap = Bitmap.createBitmap(bitmapPhoto, 0, 0,
                    bitmapPhoto.getWidth(), bitmapPhoto.getHeight(), matrix,
                    true);

        } else {
            matrix.postRotate(0);
            bitmap = Bitmap.createBitmap(bitmapPhoto, 0, 0,
                    bitmapPhoto.getWidth(), bitmapPhoto.getHeight(), matrix,
                    true);

        }

        return bitmap;
    }

    public static long getFileSize(final File file) {
        if (file == null || !file.exists())
            return 0;
        if (!file.isDirectory())
            return file.length();
        final List<File> dirs = new LinkedList<File>();
        dirs.add(file);
        long result = 0;
        while (!dirs.isEmpty()) {
            final File dir = dirs.remove(0);
            if (!dir.exists())
                continue;
            final File[] listFiles = dir.listFiles();
            if (listFiles == null || listFiles.length == 0)
                continue;
            for (final File child : listFiles) {
                result += child.length();
                if (child.isDirectory())
                    dirs.add(child);
            }
        }

        return result;
    }

    public static String getUTFEncodedString(String data) {
        byte ptext[] = data.getBytes();
        String value = null;
        try {
            value = new String(ptext, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /*public static boolean checkPlayServices(Activity activity) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        GCMIntentService.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.e("checkPlayServices", "This device is not supported.");
                activity.finish();
            }
            return false;
        }
        return true;
    }*/

    /*public static boolean checkGPS(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
            //Toast.makeText(getActivity(), "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
        } else {
            showGPSDisabledAlertToUser(context);
            return false;
        }
    }*/

    /*public static void showGPSDisabledAlertToUser(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_with_2buttons, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setView(dialogView);

        HelveticaRagularTextView txtTitle = (HelveticaRagularTextView) dialogView.findViewById(R.id.txtTitle);
        HelveticaRagularTextView txtMessage = (HelveticaRagularTextView) dialogView.findViewById(R.id.txtMessage);
        HelveticaRagularTextView txtCancel = (HelveticaRagularTextView) dialogView.findViewById(R.id.txtCancel);
        HelveticaRagularTextView txtOk = (HelveticaRagularTextView) dialogView.findViewById(R.id.txtOk);

        // Setting Dialog Title
        txtTitle.setVisibility(View.GONE);
        txtTitle.setText("");

        // Setting Dialog Message
        txtMessage.setText(" "+context.getResources().getString(R.string.gps_message));

        // Setting OK Button
        txtOk.setText(" "+context.getResources().getString(R.string.ok));
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callGPSSettingIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(callGPSSettingIntent);
                alertDialog.dismiss();
            }
        });

        // Setting CANCEL Button
        txtCancel.setText(" "+context.getResources().getString(R.string.cancel));
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }*/

    public String getIMEI(Context context) {
        TelephonyManager mngr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mngr.getDeviceId().toString();
    }

    public String getDeviceName() {
        return Build.MODEL;
    }

    public String getOSname() {
        StringBuilder builder = new StringBuilder();
        builder.append("");

        Field[] fields = Build.VERSION_CODES.class.getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldValue = -1;

            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append(fieldName);
                Log.i("osName", "" + builder.toString());
                //builder.append(" : ").append(fieldName).append(" : ");
                //builder.append("sdk=").append(fieldValue);
            }
        }
        return builder.toString();
    }

    private String getCurrentTime() {
        NumberFormat f = new DecimalFormat("00");

        String curTime = "";
        Calendar cal = Calendar.getInstance();
        int thisHour = cal.get(Calendar.HOUR);
        int thisMinut = cal.get(Calendar.MINUTE);
        int thisAMPM = cal.get(Calendar.AM_PM);
        if (thisAMPM == 0) {
            curTime = f.format(thisHour) + ":" + f.format(thisMinut) + " AM";
        } else {
            curTime = f.format(thisHour) + ":" + f.format(thisMinut) + " PM";
        }
        return curTime;
    }

    private String getCurrentDate() {
        NumberFormat f = new DecimalFormat("00");

        String curDate = "";
        Calendar cal = Calendar.getInstance();

        int thisDate = cal.get(Calendar.DAY_OF_MONTH);
        int thisMonth = cal.get(Calendar.MONTH) + 1;
        int thisYear = cal.get(Calendar.YEAR);

        return curDate = f.format(thisDate) + "/" + f.format(thisMonth) + "/" + f.format(thisYear);
    }
}
