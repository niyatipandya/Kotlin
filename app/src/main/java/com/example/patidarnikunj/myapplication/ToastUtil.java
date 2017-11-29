package com.example.patidarnikunj.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class ToastUtil {

    public static final boolean isToast = true;
    public static final String ROBOTO_REGULAR = "Roboto-Regular.otf";
    private static Typeface mTypeFace;

   /* public static void show(Context context, String message) {

        if (isToast) {
            try {
                mTypeFace = Typeface.createFromAsset(context.getAssets(), ROBOTO_REGULAR);

                Snackbar snackbar = Snackbar
                        .make(((HomeActivity)context).getCordinate(), message, Snackbar.LENGTH_LONG);

                snackbar.show();

                //Toast.makeText(context,message,Toast.LENGTH_SHORT).show();


                /*final SuperToast superToast = new SuperToast(context);



                if (!superToast.isShowing()) {
                    superToast.setText(message);
                    //superToast.setIcon(R.drawable.back, SuperToast.IconPosition.LEFT);
                    superToast.setTextSize(SuperToast.TextSize.MEDIUM);
                    superToast.getTextView().setTypeface(mTypeFace);
                    superToast.setTextColor(" "+context.getResources().getColor(R.color.black));
                    superToast.setBackground(SuperToast.Background.WHITE);
                    superToast.setDuration(SuperToast.Duration.MEDIUM);
                    superToast.setAnimations(SuperToast.Animations.FLYIN);
                    superToast.show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }*/

    public static void show(Context context, String message, View view) {
        if (isToast) {
            try {

                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);

                mTypeFace = Typeface.createFromAsset(context.getAssets(), ROBOTO_REGULAR);

                Snackbar snackbar = Snackbar
                        .make(view, message, Snackbar.LENGTH_LONG);

                snackbar.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    /*public static void show(Context context, String message ,View view) {
        if (isToast) {
            try {
                mTypeFace = Typeface.createFromAsset(context.getAssets(), ROBOTO_REGULAR);
                Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.RED);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.DKGRAY);
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
}
