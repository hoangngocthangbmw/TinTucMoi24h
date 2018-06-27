package com.watermelon.thanghn.bottomnavigation.screen.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.MainActivity;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.screen.register.RegisterDialog;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by thang on 2/12/2018.
 */

public class LoginDialog extends Dialog implements View.OnClickListener, ILoginView {
    private String TAG = "LoginDialog";
    private EditText edtEmail, edtPassword;
    private AppCompatCheckBox appCompatCheckBox;
    private Button btnLogin;
    //    private TextView txtSignUp;
    private LoginPresenter loginPresenter;//Presenter
//    private FirebaseAuth mAuth;


    public LoginDialog(@NonNull Context context) {
        super(context);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }


    public LoginDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoginDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);
        initView();
        initData();
        initAction();
    }

    private void initAction() {
    }

    private void initData() {

    }

    private void initView() {
//        mAuth = FirebaseAuth.getInstance();
        loginPresenter = new LoginPresenter(this);
        edtEmail = (EditText) findViewById(R.id.editTextFullName);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        appCompatCheckBox = (AppCompatCheckBox) findViewById(R.id.checkBoxShowPassword);
//        txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(this);
//        txtSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(getContext(), "Bạn chưa điền đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
                    loginPresenter.onHandleLogin(getOwnerActivity(), edtEmail.getText().toString(), edtPassword.getText().toString());
                }
                break;
//            case R.id.txtSignUp:
//                RegisterDialog dialog=new RegisterDialog(getContext());
//                dialog.show();
//                break;
        }
    }

    @Override
    public void onLoginSuccess(String msg) {
        Log.d(TAG, msg);
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        SharedPreferences pre=getContext().getSharedPreferences("reponselogin", MODE_PRIVATE);
        SharedPreferences.Editor edit=pre.edit();
        edit.putString("login", msg);
        edit.commit();
        dismiss();
    }

    @Override
    public void onLoginFailed(String erroMsg) {
        Log.d(TAG, erroMsg);
        Toast.makeText(getContext(), erroMsg, Toast.LENGTH_LONG).show();
    }

}