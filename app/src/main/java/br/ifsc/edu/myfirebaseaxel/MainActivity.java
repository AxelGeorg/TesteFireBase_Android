package br.ifsc.edu.myfirebaseaxel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.security.Principal;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editTextLogin;
    EditText editTextSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextSenha = findViewById(R.id.editTextSenha);


        mAuth = FirebaseAuth.getInstance();

        //create new account
        //mAuth.createUserWithEmailAndPassword("axelgeorg@gmail.com", "123lindu");
//
//
//        mAuth.signInWithEmailAndPassword("axelgeorg@gmail.com", "123lindu").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(), mAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(MainActivity.this, intentPrincipal.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Falha no login", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();// retorna um objeto do usuario cenectado
//
//        if (firebaseUser != null) {
//            Log.d("FirebaseUser", "Usuário Logado");
//        } else {
//            Log.d("FirebaseUser", "Falha na autenticação");
//        }

    }

    public void login(View view) {
        final String login = editTextLogin.getText().toString();
        String senha = editTextSenha.getText().toString();

        if (!login.trim().equals("")){
            mAuth.signInWithEmailAndPassword(login,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Sucesso"+mAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, intentPrincipal.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Falha no login "+login, Toast.LENGTH_LONG).show();
                    }
                }
            });

            FirebaseUser firebaseUser = mAuth.getCurrentUser(); // se o usuario nao estiver conectado retornara null

            if(firebaseUser!=null){
                Intent intent = new Intent(getApplicationContext(), intentPrincipal.class);
                startActivity(intent);
            }
        }
    }

    public void cadastrar(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivityCadastro.class);
        startActivity(i);
    }

    public void RecuperaSenha(View view) {
        if (!editTextLogin.getText().toString().trim().equals("")){
            mAuth.sendPasswordResetEmail(editTextLogin.getText().toString());
        }
    }
}
