package hago.gosport;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddressActivity extends AppCompatActivity {
    GridView gv;
    GetAddress g=new GetAddress();
    GetOrderssMarket oo=new GetOrderssMarket();
    AddressAdapter addressadapter;
    Address datamodel;
    ArrayList<Address> addressdata;
    String na;
    int nub;
    Button addaddress;
    public static String  addressdetalis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        gv=findViewById(R.id.gvadress);
        addaddress=findViewById(R.id.addaddress);
        SharedPreferences sh=getSharedPreferences("MarketGo",MODE_PRIVATE);
        String u=sh.getString("user",null);
        if (u==null)
        {
            Intent intent=getIntent();
            na=intent.getStringExtra("Usernameres");
        }
        else {na=u;}
        adding();
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                datamodel=addressdata.get(position);
                AlertDialog.Builder n=new AlertDialog.Builder(AddressActivity.this)
                        .setTitle("Do You Want To Delete This Address ")
                        .setMessage("("+datamodel.getaA_Name()+")")
                        .setNegativeButton("no", null)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Database DBAB=new Database();
                                Connection conn=DBAB.ConnectDB();
                                if ( conn==null)
                                {
                                    Toast.makeText(AddressActivity.this,"Sorry, Please check internet access",Toast.LENGTH_LONG).show();
                                }
                                else { String msg=DBAB.RUNDML("Delete  Address Where A_Name='"+datamodel.getaA_Name()+"'and U_UserName='"+datamodel.getaU_UserName()+"' ");
                                    if(msg.equals("Ok"))
                                    {


                                        Toast.makeText(AddressActivity.this, " The Item Has Been Deleted ", Toast.LENGTH_SHORT).show();

                                        addressdata = new ArrayList<>(g.getdata(AddressActivity.this, na));
                                        addressadapter = new AddressAdapter(AddressActivity.this, addressdata);
                                        gv.setAdapter(addressadapter);
                                    }
                                    else
                                    {
                                        Toast.makeText(AddressActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                                    }}
                            }

                        });
                n.create();
                n.show();
                return false;
            }
        });
        addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater1=LayoutInflater.from(AddressActivity.this);
                View vlayout=inflater1.inflate(R.layout.layoutaddress1,null);
                final EditText txtnameaddress=vlayout.findViewById(R.id.txtnameaddress);
                final EditText txtbuildingnumber=vlayout.findViewById(R.id.txtbuildingnumber);
                final EditText txtnumberphone=vlayout.findViewById(R.id.txtnumberphone);
                final EditText txtstrname=vlayout.findViewById(R.id.txtstrname);
                Button btnaddaddress=vlayout.findViewById(R.id.btnaddaddress);
                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(AddressActivity.this);
                bottomSheetDialog.setContentView(vlayout);
                bottomSheetDialog.show();
                btnaddaddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txtnameaddress.getText().toString().isEmpty()) {
                            txtnameaddress.setError("Please Enter Name Address");
                        }
                        else if (txtbuildingnumber.getText().toString().isEmpty()) {
                            txtbuildingnumber.setError("Please Enter Building Number");
                        }
                        else if (txtnumberphone.getText().toString().isEmpty()) {
                            txtnumberphone.setError("Please Enter Number Phone");
                        }
                        else if (txtstrname.getText().toString().isEmpty()) {
                            txtstrname.setError("Please Enter Street Name");
                        }
                        else {
                        Database DBAB = new Database();
                        Connection conn = DBAB.ConnectDB();
                        if (conn == null) {
                            Toast.makeText(AddressActivity.this, "Sorry, Please check internet access", Toast.LENGTH_LONG).show();
                        } else{
                            String msg = DBAB.RUNDML("insert into Address Values('" + txtnameaddress.getText().toString() + "','" + na + "','" + txtbuildingnumber.getText().toString() + "','" + null + "','" + null + "','" + txtnumberphone.getText().toString() + "','" + txtstrname.getText().toString() + "')");
                        if (msg.equals("Ok")) {
                            bottomSheetDialog.dismiss();
                            adding();
                            Toast.makeText(AddressActivity.this, "The Address Has Been Added Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddressActivity.this, "" + msg, Toast.LENGTH_SHORT).show();

                        }
                        }
                        }
                    }
                });
            }
        });



    }
    public void opencurt(View view) {
        startActivity(new Intent(AddressActivity.this,OrderssActivity.class));

    }
    public void adding()
    {

        addressdata=new ArrayList<>(g.getdata(AddressActivity.this,na));
        addressadapter =new AddressAdapter(AddressActivity.this,addressdata);
        gv.setAdapter(addressadapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                datamodel=addressdata.get(position);
                addressdetalis=datamodel.getaBuilding_No()+" "+datamodel.getaStreet_Name();
                AlertDialog.Builder n=new AlertDialog.Builder(AddressActivity.this)
                        .setTitle("New Orders")
                        .setMessage("Do you want to Make an Order To This Address "+datamodel.getaA_Name())
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(AddressActivity.this,MainUserActivity.class));
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Database DBAB = new Database();
                                Connection conn = DBAB.ConnectDB();
                                if (conn == null) {
                                    Toast.makeText(AddressActivity.this, "Sorry, Please check internet access", Toast.LENGTH_LONG).show();
                                } else{
                                    ResultSet num = DBAB.RunSearch("Select max(Orders_No) from Orders");


                                    try {
                                        while (num.next())
                                        {
                                            nub=num.getInt(1);
                                            nub+=1;
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }


                                    String msg = DBAB.RUNDML("insert into Orders Values('" + nub + "','" +  DateFormat.getDateTimeInstance().format(new Date()) + "','" + oo.aDouble + "','" + na + "','TNT','Abdelrahman Abdelrouf Sayed','01156804422','"+datamodel.getaA_Name()+"')");
                                    if(msg.equals("Ok"))
                                    {
                                        DBlite db=new DBlite(AddressActivity.this);
                                        SQLiteDatabase cc=db.getReadableDatabase();
                                        Cursor rs=cc.rawQuery("select * from Orders",null);
                                        try {
                                            while (rs.moveToNext())
                                            {
                                                String setoID=rs.getString(0);
                                                String setoSerialNo=rs.getString(1);
                                                String setoI_Name=rs.getString(2);
                                                String setoPrice=rs.getString(3);
                                                String setoQuantity=rs.getString(4);
                                                String setoTotal=rs.getString(5);
                                                String setoLogo=rs.getString(6);
                                                String msg1 = DBAB.RUNDML("insert into  Basket Values('" + nub + "','" +  setoSerialNo + "','" + setoQuantity + "','"+setoPrice+"','"+setoTotal+"')");
                                                if(msg1.equals("Ok"))
                                                {
                                                    Toast.makeText(AddressActivity.this, "Thanks You For Your Order New Order", Toast.LENGTH_SHORT).show();
                                                }else
                                                    {
                                                        Toast.makeText(AddressActivity.this, ""+msg1, Toast.LENGTH_SHORT).show();
                                                    }
                                            }
                                            DBlite ff=new DBlite(AddressActivity.this);
                                            SQLiteDatabase ccz = ff.getWritableDatabase();
                                            ccz.delete("orders",null,null);
                                            AlertDialog.Builder n=new AlertDialog.Builder(AddressActivity.this)
                                                    .setTitle("Your Order Has Been Ordered Successfully")
                                                    .setMessage("Thank Your For Using Go Sport \n Your Order Serial Number is : "+nub)
                                                    .setIcon(R.drawable.logo)
                                                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                        @Override
                                                        public void onCancel(DialogInterface dialog) {
                                                            startActivity(new Intent(AddressActivity.this,MainUserActivity.class));
                                                            finish();
                                                        }
                                                    })
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            startActivity(new Intent(AddressActivity.this,MainUserActivity.class));
                                                            finish();
                                                        }

                                                    });
                                            n.create();
                                            n.show();

                                        }
                                        catch(SQLiteAbortException eٍٍ)
                                        {
                                            eٍٍ.printStackTrace();

                                        }
                                    }else {
                                        Toast.makeText(AddressActivity.this, "no", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                n.create();
                n.show();
            }

        });

    }
}
