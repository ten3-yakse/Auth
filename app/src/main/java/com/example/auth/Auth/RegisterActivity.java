package com.example.auth.Auth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.auth.Api.ApiClient;
import com.example.auth.Api.model.RegisterRequest;
import com.example.auth.Api.model.RegisterResponse;
import com.example.auth.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText et_first_name, et_last_name, et_pass_reg, et_re_pass_reg, et_mail_reg;
    TextView textView;
    Button btn_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textView = findViewById(R.id.btn_have_acc);
        et_first_name = findViewById(R.id.et_first_name);
        et_last_name = findViewById(R.id.et_last_name);
        et_pass_reg = findViewById(R.id.et_pass_reg);
        et_re_pass_reg = findViewById(R.id.et_re_pass_reg);
        et_mail_reg = findViewById(R.id.et_mail_reg);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setBottom(R.drawable.drawable_bottom);


        btn_register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        btn_register.setBackground(getDrawable(R.drawable.background_button_active));
                        btn_register.setTextColor(Color.BLACK);
                        break;
                    case MotionEvent.ACTION_UP:
                        btn_register.setBackground(getDrawable(R.drawable.background_button));
                        btn_register.setTextColor(Color.WHITE);
                        String validemail= "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+"\\@"+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+"("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")+";
                        String emal= et_mail_reg.getText().toString();
                        Matcher matcherObj = Pattern.compile(validemail).matcher(emal);

                        if (matcherObj.matches()) {
                            if (et_pass_reg.getText().toString().equals(et_re_pass_reg.getText().toString())){
                                if (TextUtils.isEmpty(et_first_name.getText().toString())){
                                    if (TextUtils.isEmpty(et_last_name.getText().toString())){
                                        if (TextUtils.isEmpty(et_mail_reg.getText().toString())){
                                            if (TextUtils.isEmpty(et_pass_reg.getText().toString())){
                                                registerUser();
                                            }else{
                                                Toast.makeText(RegisterActivity.this,"Поле 'pass' пустое!",Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Toast.makeText(RegisterActivity.this,"Поле 'email' пустое!",Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(RegisterActivity.this,"Поле 'last name' пустое!",Toast.LENGTH_LONG).show();
                                    }
                                }else {
                                    Toast.makeText(RegisterActivity.this,"Поле 'first name' пустое!",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(RegisterActivity.this,"Пароли не совпадают",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Введите корректный email",Toast.LENGTH_SHORT).show();
                        }


                        break;
                }
                return false;
            }
        });


    }

    public void registerUser(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(et_mail_reg.getText().toString());
        registerRequest.setPassword(et_pass_reg.getText().toString());
        registerRequest.setFirstName(et_first_name.getText().toString());
        registerRequest.setLastName(et_last_name.getText().toString());

        Call<RegisterResponse> call = ApiClient.getService().saveUser(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Successful",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
