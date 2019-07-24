package com.example.store;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//Create the LoginRequest class to store the
//variables and the URL of the Login where the data is stored
public class LoginRequest extends StringRequest {
    //region VARIABLES
    private static final String LOGIN_REQUEST_URL = "http://localhost/LOGIN_RECIPE.php";
    private Map<String, String> params;
    //endregion

    public LoginRequest (String username, String password, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        //Variables are created to be able to pass them as parameters
        //in this case the user and password will be used
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

    }
    public Map<String, String> getParams(){
        return params;
    }
}

