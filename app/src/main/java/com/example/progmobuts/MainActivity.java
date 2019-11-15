package com.example.progmobuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        Button btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(myBtnLoginClick);
    }

    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);

            String statusLogin = prefs.getString("isLogin",null);
            SharedPreferences.Editor edit = prefs.edit();

            TextView emailText = findViewById(R.id.editText3);
            if (emailText.getText().toString().contains("@si.ukdw.ac.id")){
                edit.putString("isLogin" , "Mahasiswa");
                edit.commit();
                Intent intent = new Intent(MainActivity.this, DosenHome.class);
                startActivity(intent);
            }else if(emailText.getText().toString().contains("@staff.ukdw.ac.id")){
                edit.putString("isLogin","Admin");
                edit.commit();
                Intent intent = new Intent(MainActivity.this, AdminHome.class);
                startActivity(intent);
            }else {
                Toast toast = Toast.makeText(getApplicationContext(),"Bukan Email UKDW",Toast.LENGTH_SHORT);
                toast.setMargin(100,100);
                toast.show();
            }

        }
    };
}