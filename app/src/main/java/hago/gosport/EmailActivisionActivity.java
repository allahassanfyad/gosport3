package hago.gosport;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaCasException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailActivisionActivity extends AppCompatActivity {
   int confirmcodenumber;
    EditText txtemailac,txtcodeac;
    Button btnsend,btnconfirm;
     String usernamec,emailaddres,fnameres,lnameres,passwordres,phoneres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_activision);
        txtcodeac=findViewById(R.id.txtcodeatc);
        txtemailac=findViewById(R.id.txtEmailAtc);
        btnconfirm=findViewById(R.id.btnconfirmcode);
        btnsend=findViewById(R.id.btnsentcode);
        Intent intent=getIntent();
        emailaddres=intent.getStringExtra("Emailres");
        usernamec=intent.getStringExtra("Usernameres");
        fnameres=intent.getStringExtra("FNameres");
        lnameres=intent.getStringExtra("LNameres");
        passwordres=intent.getStringExtra("Passwordres");
        phoneres=intent.getStringExtra("Phoneres");
        txtemailac.setText(emailaddres);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmailCode();
                btnsend.setVisibility(v.INVISIBLE);
                btnconfirm.setVisibility(v.VISIBLE);
                txtemailac.setVisibility(v.INVISIBLE);
                txtcodeac.setVisibility(v.VISIBLE);
            }
        });
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtcodeac.getText().toString().isEmpty())
                {
                    Toast.makeText(EmailActivisionActivity.this, "Enter Code", Toast.LENGTH_SHORT).show();
                }
                else if (confirmcodenumber==Integer.parseInt(txtcodeac.getText().toString()))
                {


                Database DBAB=new Database();
                Connection conn=DBAB.ConnectDB();
                if ( conn==null)
                {
                    Toast.makeText(EmailActivisionActivity.this,"Sorry, Please check internet access",Toast.LENGTH_LONG).show();
                }
                else
                    {
                        String msg=DBAB.RUNDML("update Users set IsActivation ='1' where U_UserName='"+usernamec+"'");
//                        String msg=DBAB.RUNDML("update Users set Password='"+passwordres+"',FirstName='"+fnameres+"',LastName='"+lnameres+"',Email='"+emailaddres+"',Phone='"+phoneres+"',IsActivation ='1' where U_UserName='"+usernamec+"'");

                        if(msg.equals("Ok"))
                        {
                            AlertDialog.Builder n=new AlertDialog.Builder(EmailActivisionActivity.this)
                                    .setTitle("GO Sport Market")
                                    .setMessage("Your account has been created Succeed")
                                    .setIcon(R.drawable.logo)
                                    .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(EmailActivisionActivity.this, SigninActivity.class));
                                            finish();

                                        }
                                    });
                            n.create();
                            n.show();

                        }else
                            {
                                AlertDialog.Builder n=new AlertDialog.Builder(EmailActivisionActivity.this)
                                        .setTitle("GO Sport Market")
                                        .setMessage(msg)
                                        .setIcon(R.drawable.logo)
                                        .setPositiveButton("Thanks",null);
                                n.create();
                                n.show();
                            }
                    }
            }else {
                    Toast.makeText(EmailActivisionActivity.this, "THE CODE YOU ENTERED IS NOT RIGHT PLEASE TRY AGAIN ...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setEmailCode()
    {
        Random random=new Random();
        confirmcodenumber=random.nextInt(999999-100001+1)+100001;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String username = "yourmobileapp2017@gmail.com";
                    final String password = "okok2017";
                    Properties properties = new Properties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(properties,
                            new javax.mail.Authenticator() {

                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                }
                            });
                    try
                    {
                        Message message=new MimeMessage(session);
                        message.setFrom(new InternetAddress("ab3l22019@gmail.com"));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(txtemailac.getText().toString()));
                        message.setSubject("Go Sport Application - Activation code");
                        message.setText("Dear : " +usernamec+"\n Activation Code is : "+confirmcodenumber+"\n Thank you For Registration");
                        Transport.send(message);
                    }catch (MessagingException e)
                    {
                        throw new RuntimeException(e);
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }).start();
        Toast.makeText(this, "Thank You For ADD To Go Sport Application \n Please  Check Your Email", Toast.LENGTH_SHORT).show();

    }
}

