package com.watermelon.thanghn.bottomnavigation.screen.register;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.register.Account;
import com.watermelon.thanghn.bottomnavigation.screen.login.LoginDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thang on 2/12/2018.
 */

public class RegisterDialog extends Dialog implements View.OnClickListener, IRegisterView {
    private EditText edtEmail, edtUserName, edtPassword, edtPassword2;
    private Button btnLogin, btnRegister;
    private RegisterPresenter registerPresenter;//Presenter
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String email;
    private String userName;
    private String password;
    private String password2;

    public RegisterDialog(@NonNull Context context) {
        super(context);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    public RegisterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RegisterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_register);
        initView();
        initData();
        initAction();
    }

    private void initAction() {
    }

    private void initData() {
    }

    private void initView() {
        mAuth = FirebaseAuth.getInstance();
        registerPresenter = new RegisterPresenter(this);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtUserName = (EditText) findViewById(R.id.editTextFullName);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        edtPassword2 = (EditText) findViewById(R.id.editTextConfirmPassword);
//        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnRegister = (Button) findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(this);
//        btnLogin.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRegister:
                email = edtEmail.getText().toString();
                userName = edtUserName.getText().toString();
                password = edtPassword.getText().toString();
                password2 = edtPassword2.getText().toString();
                if (email.equals("") || userName.equals("") || password.equals("") || password2.equals("")) {
                    Toast.makeText(getContext(), "Bạn chưa điền đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
//                    registerPresenter.onHandleRegister(getOwnerActivity(), userName, email, password, password2);
                    registerPresenter.onHandleCheckUserName(getOwnerActivity(), userName, email, password, password2);
                }
                break;
//            case R.id.buttonLogin:
//                LoginDialog loginDialog = new LoginDialog(getContext());
//                loginDialog.show();
//                break;
        }
    }

    @Override
    public void onHandelRegisterSucces(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        dismiss();
    }

    @Override
    public void onHandleRegisterFailed(String erroMsg) {
        Toast.makeText(getContext(), erroMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHaveUserName(String msg) {
        if (msg.equals("yes")){
            Log.d("ccxx","khongtao");
            Toast.makeText(getContext(), "...", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNotHaveUserName(String erroMsg) {
        if (erroMsg.equals("no")){
            Log.d("ccxx","tao");
            registerPresenter.onHandleRegister(getOwnerActivity(), userName, email, password, password2);
        }
    }


}
