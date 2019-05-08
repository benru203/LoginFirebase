package com.example.loginfirebase;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Register extends AppCompatActivity {

    private EditText TxtEmail, TxtPassword;
    private Resources Res ;

    private FirebaseAuth Auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        TxtEmail = (EditText)findViewById(R.id.TxtEmail);
        TxtPassword = (EditText)findViewById(R.id.TxtPassword);
        Res = this.getResources();

        Auth = FirebaseAuth.getInstance();
    }

    public void RegisterUser(View view){
        String email   = TxtEmail.getText().toString();
        String password = TxtPassword.getText().toString();
        if(email.isEmpty() || password.isEmpty()){
            Toast toast = Toast.makeText(Register.this, Res.getString(R.string.error_empty),Toast.LENGTH_LONG);
            toast.show();
        }else{
            Auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = Auth.getCurrentUser();
                                Toast toast = Toast.makeText(Register.this, Res.getString(R.string.register_success),Toast.LENGTH_LONG);
                                toast.show();

                            } else {
                                Toast.makeText(Register.this, Res.getString(R.string.errorauth),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

}