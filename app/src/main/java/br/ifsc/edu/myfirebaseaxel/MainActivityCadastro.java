package br.ifsc.edu.myfirebaseaxel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityCadastro extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText login;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);

        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.editTextLoginAdd);
        senha = findViewById(R.id.editTextsenhaAdd);

    }

    public void CadastrarUser(View view) {

        if ((!login.toString().trim().equals("")) && (!senha.toString().trim().equals(""))) {

            String loginCad = login.getText().toString();
            String senhaCad = senha.getText().toString();

            mAuth.createUserWithEmailAndPassword(loginCad, senhaCad).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Sucesso!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Falha no Cadastro!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}