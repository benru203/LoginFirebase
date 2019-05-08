package com.example.loginfirebase;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private EditText TxtEmail, TxtPassword;

    private Resources Res ;

    private FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxtEmail = (EditText)findViewById(R.id.TxtEmail);
        TxtPassword = (EditText)findViewById(R.id.TxtPassword);
        Res = this.getResources();

        Auth = FirebaseAuth.getInstance();
    }

    public void Login(View view){
        String email = TxtEmail.getText().toString();
        String password = TxtPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast toast = Toast.makeText(MainActivity.this, Res.getString(R.string.error_empty),Toast.LENGTH_LONG);
            toast.show();
        }else{
            Auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(MainActivity.this,Home.class);
                                startActivity(intent);
                            } else {
                                Toast toast = Toast.makeText(MainActivity.this, Res.getString(R.string.errorauth),Toast.LENGTH_LONG);
                                toast.show();
                                TxtEmail.setError("*");
                                TxtPassword.setError("*");
                            }
                        }
                    });
        }
    }

    public void openRegister(View view){
        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }
}








