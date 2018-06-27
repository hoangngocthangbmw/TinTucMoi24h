package com.watermelon.thanghn.bottomnavigation.model.register;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
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
import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.screen.register.IRegisterModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by thang on 3/23/2018.
 */

public class RegisterModel {
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private IRegisterModel callback;

    public RegisterModel(IRegisterModel callback) {
        this.callback = callback;
    }

    public void HandleRegister(Activity activity, final String username, final String email, String password, String password2) {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final Account account = new Account(username, email, password);

        if (password.equals(password2)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull final Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("account").child("acc_" + username).setValue(account);
                                callback.onHandelRegisterSucces("Đăng kí thành công");
                            } else {
                               callback.onHandleRegisterFailed("Đăng kí thất bại");
                            }
                        }
                    });
        } else {
            callback.onHandleRegisterFailed("Đăng kí thất bại");
        }
    }

    public void HandleCheckUserName(Activity activity, final String username, final String email, String password, String password2) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final List<Account> list = new ArrayList<Account>();
        databaseReference.child("account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String a = null;
                for (DataSnapshot ac : dataSnapshot.getChildren()) {
                    Account account = new Account(ac);
                    list.add(account);
                }
                if (list.size()>0) {
                    String fab = null;
                    for (int i = 0; i < list.size(); i++) {
                        Log.d("taikhoan", list.get(i).getUserName());
                        if (list.get(i).getUserName().equals(username)) {
//                        Toast.makeText(App.getAppContext(), "1", Toast.LENGTH_SHORT).show();
//                            callback.onHaveUserName("1");
                            fab="yes";
                            break;
                        } else {
//                        Toast.makeText(App.getAppContext(), "0", Toast.LENGTH_SHORT).show();
//                            callback.onNotHaveUserName("0");
                            fab="no";
                        }
                    }
                    if (fab.equals("yes")){
                        callback.onHaveUserName("yes");
                    }
                    if(fab.equals("no")){
                        callback.onNotHaveUserName("no");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
