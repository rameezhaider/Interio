package com.google.ar.core.examples.java.helloar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {


    private static final String TAG = "RegisterActivity";
    EditText mEmailText, mPasswordText, mPasswordcheckText, mName;
    private FirebaseAuth firebaseAuth;


    private Button SignUP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();

        mEmailText = findViewById(R.id.idText);
        mPasswordText = findViewById(R.id.pwText);
        mPasswordcheckText = findViewById(R.id.signUpPwCheck);
        mName=findViewById(R.id.signUpName);

        SignUP = findViewById(R.id.register);

        SignUP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Get subscription information
                final String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                String pwdcheck = mPasswordcheckText.getText().toString().trim();
                String name=mName.getText().toString().trim();


                if (pwd.equals(pwdcheck)) {
                    Log.d(TAG, "Register button" + email + " , " + pwd);
                    final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                    mDialog.setMessage("Signing up...");
                    mDialog.show();

                    //Register a new account on Firebase
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {
                                mDialog.dismiss();

                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String uid = user.getUid();
                                String name = mName.getText().toString().trim();

                                HashMap<Object, String> hashMap = new HashMap<>();

                                hashMap.put("uid", uid);
                                hashMap.put("email", email);
                                hashMap.put("name",name);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("Users");
                                reference.child(uid).setValue(hashMap);


                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(SignUpActivity.this, "You have successfully registered as a member.", Toast.LENGTH_SHORT).show();

                            }
                            else if(!task.isSuccessful()) {
                                mDialog.dismiss();
                                try {
                                    throw task.getException();
                                } catch(FirebaseAuthWeakPasswordException e) {
                                    Toast.makeText(SignUpActivity.this,"Your password is too simple." ,Toast.LENGTH_SHORT).show();
                                } catch(FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(SignUpActivity.this,"It doesn't fit the email format." ,Toast.LENGTH_SHORT).show();
                                } catch(FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(SignUpActivity.this,"This email already exists." ,Toast.LENGTH_SHORT).show();
                                } catch(Exception e) {
                                    Toast.makeText(SignUpActivity.this,"Please check again." ,Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                } else {

                    Toast.makeText(SignUpActivity.this, "The password is wrong. Please re-enter.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }
}