package br.ifsc.edu.myfirebaseaxel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class intentPrincipal extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_principal);
        mAuth=FirebaseAuth.getInstance();

    }

    public void logout(View view) {
        mAuth.signOut();
        finish();
    }
}
