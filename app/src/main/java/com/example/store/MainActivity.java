package com.example.store;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //region VARIABLES
    private EditText mPassword, mUserName;
    Button btnLogin, btnSign;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserName = findViewById(R.id.txtUser);
        mPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSign = findViewById(R.id.btnRegistrar);

        btnSign.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        StringRequest request = new StringRequest(Request.Method.POST, "http://localhost/LOGIN_STORE.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("1")) {
                            Toast.makeText(getApplicationContext(),
                                    "¡Usuario correcto, Bienvenido!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Menu.class));
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "¡Usuario y/o contraseña incorrecto!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            //With the StringRequest method, the URL of the login part is extracted, where
            //stored the database for this section
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("username", mUserName.getText().toString());
                params.put("password", mPassword.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}
