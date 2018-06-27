package com.watermelon.thanghn.bottomnavigation.screen.menu;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.register.RegisterModel;
import com.watermelon.thanghn.bottomnavigation.screen.login.LoginDialog;
import com.watermelon.thanghn.bottomnavigation.screen.newsoffline.ListNewsOfflineActivity;
import com.watermelon.thanghn.bottomnavigation.screen.register.RegisterDialog;

/**
 * Created by thang on 1/28/2018.
 */

public class MenuFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button btnNewsOffline, buttonLogin, btnRegister;
    private static final String MY_EMAIL = "thanghai.tb123@gmail.com";
    private static final String SUBJECT = "Feedback Tin tức mới 24h";
    public static final String SEND = "Send email";
    public static final String EMAIL = "mailto:";
    private TextView mTextViewFeedBack;
    private TextView mTextViewSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        initView();
        initData();
        initAction();
        return view;
    }

    private void initAction() {
    }

    private void initData() {
    }

    private void initView() {
        btnNewsOffline = (Button) view.findViewById(R.id.btnNewsOffline);
        btnNewsOffline.setOnClickListener(this);
        buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        btnRegister = (Button) view.findViewById(R.id.buttonRegisterMenu);
        btnRegister.setOnClickListener(this);
        mTextViewFeedBack = view.findViewById(R.id.text_feedback);
        mTextViewSetting = view.findViewById(R.id.text_setting);
        mTextViewSetting.setOnClickListener(this);
        mTextViewFeedBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNewsOffline:
                Intent intent = new Intent(getActivity(), ListNewsOfflineActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonLogin:
                LoginDialog loginDialog = new LoginDialog(getContext());
                loginDialog.show();
                break;
            case R.id.buttonRegisterMenu:
                RegisterDialog registerDialog = new RegisterDialog(getContext());
                registerDialog.show();
                break;
            case R.id.text_feedback:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(EMAIL + MY_EMAIL));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
                startActivity(Intent.createChooser(emailIntent, SEND));
                break;
            case R.id.text_setting:
                break;
        }
    }
}
