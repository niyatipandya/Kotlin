package com.example.patidarnikunj.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.view.Window;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Sketch Project Studio
 * Created by Angga on 12/04/2016 14.27.
 */
public class AppHelper {    
    
    /**
     * Turn drawable resource into byte array.
     *
     * @param context parent context
     * @param id      drawable resource id
     * @return byte array
     */
    public static byte[] getFileDataFromDrawable(Context context, int id) {
        Drawable drawable = ContextCompat.getDrawable(context, id);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Turn drawable into byte array.
     *
     * @param drawable data
     * @return byte array
     */
    public static byte[] getFileDataFromDrawable(Context context, Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    /*public static String getTimeAgo(long time, Context ctx) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.minuteago);
        } else if (diff < 2 * MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.minuteago);
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " "+ctx.getResources().getString(R.string.minago);
        } else if (diff < 90 * MINUTE_MILLIS) {
            return " "+ctx.getResources().getString(R.string.anhourago);
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS +" "+ ctx.getResources().getString(R.string.anhourago);
        } else if (diff < 48 * HOUR_MILLIS) {
            return ctx.getResources().getString(R.string.yesterday);
        }
        else {
            return diff / DAY_MILLIS + " "+ ctx.getResources().getString(R.string.daysago);
        }
   } */
   /* public static String getTimeAgo(String date,Context ctx) {

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date myDate = null;

        //2017-08-11


        try {
            myDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            simpleDateFormat = new SimpleDateFormat("EEE dd MMM - HH:mm");
            try {
                myDate = simpleDateFormat.parse(date);
                getTimeAgo(myDate.toString(), ctx);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        long time = myDate.getTime();


        System.out.println("localTime " + myDate.getTime());


        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }


        // TODO: localize
        final long diff = now - time;


        Log.e("diff", "" + diff);


        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else if (diff / DAY_MILLIS == 7) {
            return "week ago";
        } else if (diff / DAY_MILLIS > 7 && diff / DAY_MILLIS <= 8) {
            return "Last week";
        } else if (diff / DAY_MILLIS > 8 && diff / DAY_MILLIS <= 14) {
            return "Last week";
        } else if (diff / DAY_MILLIS > 14 && diff / DAY_MILLIS < 21) {
            return "2 weeks ago";
        } else if (diff / DAY_MILLIS > 21 && diff / DAY_MILLIS < 31) {
            return "3 weeks ago";
        } else if (diff / DAY_MILLIS >= 31 && diff / DAY_MILLIS <= 89) {
            return "Last month";
        } else if (diff / DAY_MILLIS >= 90 && diff / DAY_MILLIS < 365) {
            return "3 month ago";
        } else if (diff / DAY_MILLIS >= 365) {
            return "Year ago";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }*/

        /*final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.minuteago);
        } else if (diff < 2 * MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.minuteago);
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " "+ctx.getResources().getString(R.string.minago);
        } else if (diff < 90 * MINUTE_MILLIS) {
            return " "+ctx.getResources().getString(R.string.anhourago);
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS +" "+ ctx.getResources().getString(R.string.anhourago);
        } else if (diff < 48 * HOUR_MILLIS) {
            return ctx.getResources().getString(R.string.yesterday);
        }
        else {
            return diff / DAY_MILLIS + " "+ ctx.getResources().getString(R.string.daysago);
        }}*/

   /* public static String getTimeAgoE(String date, Context ctx) {

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("EEE dd MMM - HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date myDate = null;

        //2017-08-11


        try {
            myDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long time = myDate.getTime();


        System.out.println("localTime " +myDate.getTime());


        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize


        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.minuteago);
        } else if (diff < 2 * MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.minuteago);
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " "+ctx.getResources().getString(R.string.minago);
        } else if (diff < 90 * MINUTE_MILLIS) {
            return " "+ctx.getResources().getString(R.string.anhourago);
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS +" "+ ctx.getResources().getString(R.string.anhourago);
        } else if (diff < 48 * HOUR_MILLIS) {
            return ctx.getResources().getString(R.string.yesterday);
        }
        else {
            return diff / DAY_MILLIS + " "+ ctx.getResources().getString(R.string.daysago);
        }
    }
*/

    public static boolean isInputCorrect(Editable s, int size, int dividerPosition, char divider) {
        boolean isCorrect = s.length() <= size;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && (i + 1) % dividerPosition == 0) {
                isCorrect &= divider == s.charAt(i);
            } else {
                isCorrect &= Character.isDigit(s.charAt(i));
            }
        }
        return isCorrect;
    }

    public static char[] getDigitArray(final Editable s, final int size) {
        char[] digits = new char[size];
        int index = 0;
        for (int i = 0; i < s.length() && index < size; i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                digits[index] = current;
                index++;
            }
        }
        return digits;
    }

    public static String concatString(char[] digits, int dividerPosition, char divider) {
        final StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 0) {
                formatted.append(digits[i]);
                if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                    formatted.append(divider);
                }
            }
        }

        return formatted.toString();
    }
    public static String parseDate(String timeAtMiliseconds) {
        if (timeAtMiliseconds.equalsIgnoreCase("")) {
            return "";
        }
        //API.log("Day Ago "+dayago);
        String result = "now";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataSot = formatter.format(new Date());
        Calendar calendar = Calendar.getInstance();

        long dayagolong = Long.valueOf(timeAtMiliseconds) * 1000;
        calendar.setTimeInMillis(dayagolong);
        String agoformater = formatter.format(calendar.getTime());

        Date CurrentDate = null;
        Date CreateDate = null;

        try {
            CurrentDate = formatter.parse(dataSot);
            CreateDate = formatter.parse(agoformater);

            long different = Math.abs(CurrentDate.getTime() - CreateDate.getTime());

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            different = different % secondsInMilli;
            if (elapsedDays == 0) {
                if (elapsedHours == 0) {
                    if (elapsedMinutes == 0) {
                        if (elapsedSeconds < 0) {
                            return "0" + " s";
                        } else {
                            if (elapsedDays > 0 && elapsedSeconds < 59) {
                                return "now";
                            }
                        }
                    } else {
                        return String.valueOf(elapsedMinutes) + "m ago";
                    }
                } else {
                    return String.valueOf(elapsedHours) + "h ago";
                }

            } else {
                if (elapsedDays <= 29) {
                    return String.valueOf(elapsedDays) + "d ago";
                }
                if (elapsedDays > 29 && elapsedDays <= 58) {
                    return "1Mth ago";
                }
                if (elapsedDays > 58 && elapsedDays <= 87) {
                    return "2Mth ago";
                }
                if (elapsedDays > 87 && elapsedDays <= 116) {
                    return "3Mth ago";
                }
                if (elapsedDays > 116 && elapsedDays <= 145) {
                    return "4Mth ago";
                }
                if (elapsedDays > 145 && elapsedDays <= 174) {
                    return "5Mth ago";
                }
                if (elapsedDays > 174 && elapsedDays <= 203) {
                    return "6Mth ago";
                }
                if (elapsedDays > 203 && elapsedDays <= 232) {
                    return "7Mth ago";
                }
                if (elapsedDays > 232 && elapsedDays <= 261) {
                    return "8Mth ago";
                }
                if (elapsedDays > 261 && elapsedDays <= 290) {
                    return "9Mth ago";
                }
                if (elapsedDays > 290 && elapsedDays <= 319) {
                    return "10Mth ago";
                }
                if (elapsedDays > 319 && elapsedDays <= 348) {
                    return "11Mth ago";
                }
                if (elapsedDays > 348 && elapsedDays <= 360) {
                    return "12Mth ago";
                }

                if (elapsedDays > 360 && elapsedDays <= 720) {
                    return "1 year ago";
                }

                if (elapsedDays > 720) {
                    SimpleDateFormat formatterYear = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar calendarYear = Calendar.getInstance();
                    calendarYear.setTimeInMillis(dayagolong);
                    return formatterYear.format(calendarYear.getTime()) + "";
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static  void chageStatusBar(int i , Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (i == 0) {
                window.setStatusBarColor(activity.getResources().getColor(R.color.app_blue));
            } else if (i == 1) {
                window.setStatusBarColor(activity.getResources().getColor(R.color.app_profile));
            } else if (i == 2) {
                window.setStatusBarColor(activity.getResources().getColor(R.color.pip_green));
            }
            else if (i == 3) {
                window.setStatusBarColor(activity.getResources().getColor(R.color.colorAccent));
            }
        }
    }
}