package com.example.loginapp

import android.content.Intent
import android.net.Network
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


private lateinit var btnReg:Button
private lateinit var etUser:EditText
private lateinit var etPassword:EditText
private lateinit var txtLogin:TextView

private lateinit var user:String
private lateinit var password:String

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnReg = findViewById(R.id.btnRegister)
        etUser = findViewById(R.id.etRUserName)
        etPassword = findViewById(R.id.etRPassword)

        txtLogin = findViewById(R.id.tvLoginLink)
        txtLogin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnReg.setOnClickListener{
            //registerUser()
            user = etUser.getText().toString()
            password = etPassword.getText().toString()
            Toast.makeText(this, "sussessfull \n " + user + " \n" + password , Toast.LENGTH_SHORT).show()

            val call: Call<ResponseBody> = RetrofitClient
                .getInstance()
                .api
                .createUser(User(user, password))
            call.enqueue(object : Callback<ResponseBody?>
            {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    var s = ""
                    try {
                        s = response.body()!!.string()
                        if (s == "SUCCESS") {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Successfully registered. Please login",
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        } else {
                            Toast.makeText(this@RegisterActivity, "User already exists!", Toast.LENGTH_LONG)
                                .show()
                        }

                } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_LONG).show()
                }
            })

        }
    }

    private fun registerUser()
    {
        val userName: String = etUser.getText().toString().trim()
        val password: String = etPassword.getText().toString().trim()

        if (userName.isEmpty()) {
            etUser.setError("Username is required")
            etUser.requestFocus()
            return
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required")
            etPassword.requestFocus()
            return
        }

        val call: Call<ResponseBody> = RetrofitClient
            .getInstance()
            .api
            .createUser(User(user, password))
        call.enqueue(object : Callback<ResponseBody?>
        {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                var s = ""
                try {

                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                //Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            }
        })
    }
}