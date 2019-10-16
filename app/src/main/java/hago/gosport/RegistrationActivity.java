package hago.gosport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;

public class RegistrationActivity extends AppCompatActivity {
    Button bsend,btnCreateAccount;
    EditText txtFName,txtLName,txtUserName,txtPassword,txtEmail,txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtUserName=(EditText)findViewById(R.id.txtnumberphone);
        txtFName=(EditText)findViewById(R.id.txtnameaddress);
        txtLName=(EditText)findViewById(R.id.txtbuildingnumber);
        txtPassword=(EditText)findViewById(R.id.txtstrname);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtPhone=(EditText)findViewById(R.id.txtPhone);
        btnCreateAccount=(Button) findViewById(R.id.btnaddaddress);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String pp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
                String np="^01[0-2]{1}[0-9]{8}";
                String us="^[a-z0-9_-]{3,15}$";

                Database DBAB=new Database();
                Connection conn=DBAB.ConnectDB();
                if ( conn==null)
                {
                    Toast.makeText(RegistrationActivity.this,"Sorry, Please check internet access",Toast.LENGTH_LONG).show();
                }
                else
                    {
                                if (txtFName.getText().toString().isEmpty()) {
                                    txtFName.setError("Please Enter FirstName");
                                }
                                else if (txtLName.getText().toString().isEmpty()) {
                                    txtLName.setError("Please Enter LastName");
                                }
                                else if (txtUserName.getText().toString().isEmpty()) {
                                    txtUserName.setError("Please Enter UserName");
                                }
                                else if (!txtUserName.getText().toString().matches(us)) {
                                    txtUserName.setError("Enter UserName Correct");
                                    Toast.makeText(RegistrationActivity.this, "(minimum 3 characters  maximum 15)&&(a-z)&&(0-9)", Toast.LENGTH_SHORT).show();
                                }
                                else if (txtPassword.getText().toString().isEmpty()) {
                                    txtPassword.setError("Please Enter Password");
                                }
                                else if (!txtPassword.getText().toString().matches(pp)) {
                                    txtPassword.setError("Password A-Z And a-z And number ");
                                }
                                else if (txtEmail.getText().toString().isEmpty()) {
                                    txtEmail.setError("Please Enter Email");
                                }
                                else if (!txtEmail.getText().toString().matches(aa)) {
                                    txtEmail.setError("Invaild Email Address (user@domin)");
                                }
                                else if (txtPhone.getText().toString().isEmpty()) {
                                txtPhone.setError("Please Enter Phone");
                                }
                                else if (!txtPhone.getText().toString().matches(np)) {
                                    txtPhone.setError("Enter the correct phone number");
                                }
                                else
                                    {
                                        String msg=DBAB.RUNDML("insert into Users Values('"+txtUserName.getText()+"','"+txtPassword.getText()+"','"+txtFName.getText()+"','"+txtLName.getText()+"','"+txtEmail.getText()+"','"+txtPhone.getText()+"','0')");
                                        if(msg.equals("Ok"))
                                            {
                                                AlertDialog.Builder n=new AlertDialog.Builder(RegistrationActivity.this)
                                                        .setTitle("GO Sport Market")
                                                        .setMessage("Your account has been created Succeed")
                                                        .setIcon(R.drawable.logo)
                                                        .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                Intent intent=new Intent(RegistrationActivity.this, SigninActivity.class);
                                                                intent.putExtra("Usernameres",txtUserName.getText().toString());
                                                                intent.putExtra("Emailres",txtEmail.getText().toString());
                                                                intent.putExtra("FNameres",txtFName.getText().toString());
                                                                intent.putExtra("LNameres",txtLName.getText().toString());
                                                                intent.putExtra("Passwordres",txtPassword.getText().toString());
                                                                intent.putExtra("Phoneres",txtPhone.getText().toString());
                                                                startActivity(intent);
                                                                finish();

                                                            }
                                                        });
                                                n.create();
                                                n.show();
                                            }
                                            else if (msg.contains("PRIMARY KEY") )
                                            {
                                                    AlertDialog.Builder n=new AlertDialog.Builder(RegistrationActivity.this)
                                                            .setTitle("GO Sport Market")
                                                            .setMessage("This UserName already used , try again")
                                                            .setIcon(R.drawable.logo)
                                                            .setPositiveButton("Thanks",null);
                                                    n.create();
                                                    n.show();
                                            }
                                            else
                                                {
                                                    Toast.makeText(RegistrationActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                                                }
                                        }


                                }
                        }

                    });


                }
            }



