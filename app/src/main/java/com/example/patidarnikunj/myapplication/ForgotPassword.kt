package com.example.patidarnikunj.myapplication

import android.app.Activity
import android.os.Bundle

class ForgotPassword : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }
}
/*

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap
import java.util.regex.Matcher
import java.util.regex.Pattern

*
 * Created by Niyati on 29-11-2017.


class ForgotPassword : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)










    }

}

//@BindView(R.id.imgBackBlue)
internal var imgBackBlue: ImageView
//@BindView(R.id.txtSkip)
internal var txtSkip: TextView
@BindView(R.id.txtForgotPasswordText)
internal var txtForgotPasswordText: TextView? = null
@BindView(R.id.txtForgotPasswordText2)
internal var txtForgotPasswordText2: TextView? = null
@BindView(R.id.txtPasswordText)
internal var txtPasswordText: TextView? = null
@BindView(R.id.edEmailId)
internal var edEmailId: EditText? = null
@BindView(R.id.relativeResetPassword)
internal var relativeResetPassword: RelativeLayout? = null
@BindView(R.id.txtSendForgotPW)
internal var txtSendForgotPW: TextView? = null
internal var includeLayout: View
internal var progressDialog: ProgressDialog
private var sharedPreferences: SharedPreferences? = null
private var editor: SharedPreferences.Editor? = null
private var arrayList: ArrayList<ForgotPasswordModel>? = null
private val TAG = "ForgotPassword"
internal var mContext: Context
@BindView(R.id.coordinatorLayout)
internal var coordinatorLayout: CoordinatorLayout? = null

protected override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_forgot_password)
    ButterKnife.bind(this)

    CommonUtil.hideSoftKeyboard(this)
    mContext = this

    AppHelper.chageStatusBar(0, this)

    arrayList = ArrayList()

    sharedPreferences = getSharedPreferences(VariableBag.SHAREDPREFRENCE, Context.MODE_PRIVATE)
    editor = sharedPreferences!!.edit()

    includeLayout = findViewById<View>(R.id.forgotPw_toolbar) as View
    imgBackBlue = includeLayout.findViewById(R.id.imgBackBlue)
    txtSkip = includeLayout.findViewById(R.id.txtSkip)

    txtSendForgotPW!!.setOnClickListener(this)
    imgBackBlue.setOnClickListener(this)
    txtSkip.setOnClickListener(this)
    txtSkip.visibility = View.GONE
}

fun forgotPassword() {

    val params = RequestParams()
    params.put("email", edEmailId!!.text.toString())

    val client = HashMap<String, String>()

    client.put("os", "a")
    client.put("osv", sharedPreferences!!.getString(VariableBag.OSVERSION, ""))
    client.put("appv", sharedPreferences!!.getString(VariableBag.APPVERSION, ""))
    client.put("devicetoken", "123456789")
    client.put("devicename", sharedPreferences!!.getString(VariableBag.DEVICENAME, ""))
    val responseHandler = object : AsyncHttpResponseHandler() {
        override fun onStart() {
            progressDialog = ProgressDialog(this@ForgotPassword)
            progressDialog.setMessage(getResources().getString(R.string.please_wait))
            progressDialog.setCancelable(false)
            progressDialog.show()
            super.onStart()
        }

        override fun onSuccess(s: String?) {
            super.onSuccess(s)
            Log.i(TAG, "Response" + s!!)

            try {
                progressDialog.dismiss()

                val root = JSONObject("" + s)
                Log.i(TAG, "S" + root)

                //S{"message":"Email is invalid"}

                if (root.has("data")) {
                    val data = root.getJSONObject("data")
                    val user_id = data.getString("user_id")
                    val otp = data.getString("otp")
                    ToastUtil.show(this@ForgotPassword, getResources().getString(R.string.email_sent_successfully), coordinatorLayout)
                    val i = Intent(this@ForgotPassword, ResetPasswordActivity::class.java)
                    i.putExtra("user_id", user_id)
                    startActivity(i)
                } else {
                    ToastUtil.show(this@ForgotPassword, root.getString("message"), coordinatorLayout)
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        override fun onFailure(throwable: Throwable, s: String?) {
            super.onFailure(throwable, s)
            Log.i(TAG, "throwable" + throwable)
            Log.i(TAG, "s" + s!!)
            ToastUtil.show(this@ForgotPassword, getResources().getString(R.string.something_wrong_happened), coordinatorLayout)
        }

        override fun onFinish() {
            super.onFinish()
        }
    }
    HttpRestClientForLogin.post("forgotpass", params, responseHandler, client)
}


override fun onClick(v: View) {
    when (v.id) {
        R.id.txtSendForgotPW -> if (validation()) {
            if (checkValidation()) {
                forgotPassword()
            }
        }
        R.id.imgBackBlue -> finish()
        R.id.txtSkip -> finish()
    }
}

 @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ForgotPassword.this, LoginActivity.class);
        startActivity(i);
        finish();
    }


fun validateEmail(email: String): Boolean {

    val EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"
    val pattern = Pattern.compile(EMAIL_PATTERN)
    val matcher: Matcher

    matcher = pattern.matcher(email)
    return matcher.matches()
}

private fun hideKeyboard() {
    val view = getCurrentFocus()
    if (view != null) {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view!!.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

private fun checkValidation(): Boolean {
    hideKeyboard()
    val email = edEmailId!!.text.toString()
    if (!validateEmail(email)) {
        ToastUtil.show(this@ForgotPassword, getResources().getString(R.string.not_valid_email_address), coordinatorLayout)
        return false
    } else {
        return true
    }
}

private fun validation(): Boolean {
    if (edEmailId!!.text.toString().length == 0) {
        ToastUtil.show(getApplicationContext(), getResources().getString(R.string.please_enter_email), coordinatorLayout)
        return false
    } else {
        return true
    }

}
}
*/
