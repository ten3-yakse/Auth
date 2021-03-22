package com.example.auth.Auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.auth.Api.ApiClient;
import com.example.auth.Api.model.LoginRequest;
import com.example.auth.Api.model.LoginResponses;
import com.example.auth.MainActivity;
import com.example.auth.MovieActivity;
import com.example.auth.R;
import com.example.auth.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView textView;
    Button btn_login;
    EditText et_mail, et_pass;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.btn_create);
        btn_login = findViewById(R.id.btn_login);
        et_mail = findViewById(R.id.et_mail);
        et_pass = findViewById(R.id.et_pass);

        btn_login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        btn_login.setBackground(getDrawable(R.drawable.background_button_active));
                        btn_login.setTextColor(Color.BLACK);
                        break;
                    case MotionEvent.ACTION_UP:
                        btn_login.setBackground(getDrawable(R.drawable.background_button));
                        btn_login.setTextColor(Color.WHITE);

                        auth();

                        break;
                }
                return false;
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
            }
        });


    }
    private void auth() {

        LoginRequest loginBody = new LoginRequest();
        loginBody.setEmail(et_mail.getText().toString());
        loginBody.setPassword(et_pass.getText().toString());
        Call<LoginResponses> call = ApiClient.getLogin().authUser(loginBody);
        call.enqueue(new Callback<LoginResponses>() {
            @Override
            public void onResponse(Call<LoginResponses> call, Response<LoginResponses> response) {
                if (response.isSuccessful()){
                    LoginResponses loginResponses = response.body();

                    sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    String message = Integer.toString(loginResponses.getToken());
                    ed.putString(SAVED_TEXT, message);
                    ed.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Error" ,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponses> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage() ,Toast.LENGTH_LONG).show();
            }
        });
    }
}
