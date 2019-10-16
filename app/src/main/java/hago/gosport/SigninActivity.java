package hago.gosport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SigninActivity extends AppCompatActivity {

    Button Sbtnlogin;
    Button signup;
    EditText txtus,txtpas;
    CheckBox chk;
public static String namem,emailm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        SharedPreferences sh=getSharedPreferences("MarketGo",MODE_PRIVATE);
        String u=sh.getString("user",null);
        String p=sh.getString("pass",null);
        if ((u!=null)&&(p!=null))
        {
            SigninActivity.namem=sh.getString("first_name",null)+" "+sh.getString("last_name",null);
            SigninActivity.emailm=sh.getString("email",null);
            startActivity(new Intent(this,MainUserActivity.class ));
        }

        txtus=(EditText)findViewById(R.id.stxtUserName);
        txtpas=(EditText)findViewById(R.id.stxtPassword);
        chk=(CheckBox)findViewById(R.id.sChexkBox);
        signup=findViewById(R.id.Signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivity.this,OnbordingActivity.class));
            }
        });
        Sbtnlogin=(Button)findViewById(R.id.sbntsignin);
        Sbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database DBLo=new Database();
                Connection conn=DBLo.ConnectDB();
                if (conn==null)
                {
                    Toast.makeText(SigninActivity.this, "Check internet Access", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        ResultSet rslogin=DBLo.RunSearch("select * from users where U_UserName='"+txtus.getText()+"'and Password='"+txtpas.getText()+"'");

                        try {
                            if ( rslogin.next())
                            {
                                if (chk.isChecked())
                                {
                                    getSharedPreferences("MarketGo",MODE_PRIVATE)
                                            .edit()
                                            .putString("user",txtus.getText().toString())
                                            .putString("pass",txtpas.getText().toString())
                                            .putString("email",rslogin.getString(5))
                                            .putString("first_name",rslogin.getString(3))
                                            .putString("last_name",rslogin.getString(4))
                                            .commit();
                                }
                                namem=rslogin.getString(3)+" "+rslogin.getString(4);
                                emailm=rslogin.getString(5);
                                Intent intent=new Intent(SigninActivity.this,MainUserActivity.class);
                                intent.putExtra("Usernameres",txtus.getText().toString());
                                startActivity(intent);


                            }
                            else
                                {
                                    Toast.makeText(SigninActivity.this, " Invaild username / Password ", Toast.LENGTH_SHORT).show();

                                }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            }
        });
    }
    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event)
    {
        if(KeyCode == KeyEvent.KEYCODE_BACK){
            finish();
            moveTaskToBack(true);//do something on back.
            return true;
        }
        return  super.onKeyDown(KeyCode,event);
    }

}
