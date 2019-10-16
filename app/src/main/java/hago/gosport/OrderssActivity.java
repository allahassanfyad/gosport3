package hago.gosport;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderssActivity extends AppCompatActivity {
    GridView gv;
    GetOrderssMarket g=new GetOrderssMarket();
    public static String orderssno;
    OrderssAdapter orderssAdapter;
    Orderss orderss;
    ArrayList<Orderss> orderssArrayList;
    TextView thistotal;
    Button cone;
    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event)
    {
        if(KeyCode == KeyEvent.KEYCODE_BACK){
          startActivity(new Intent(this,MainUserActivity.class));
          finish();
            return true;
        }
        return  super.onKeyDown(KeyCode,event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderss);
        gv=(GridView)findViewById(R.id.gvorderss);
        thistotal=(TextView) findViewById(R.id.thistotal);
        cone=findViewById(R.id.continueorderss);
        cone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBlite db=new DBlite(OrderssActivity.this);
                SQLiteDatabase cc=db.getReadableDatabase();
                Cursor rs=cc.rawQuery("select * from Orders",null);
                if (rs.moveToNext()) {
                    startActivity(new Intent(OrderssActivity.this, AddressActivity.class));
                }else
                    {
                        Toast.makeText(OrderssActivity.this, "No Items In The Cart", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        onstartss();


        final SwipeRefreshLayout swpDepartment=(SwipeRefreshLayout)findViewById(R.id.swporderss);
        swpDepartment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onstartss();

                swpDepartment.setRefreshing(false);
            }
        });
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, final long id) {
                orderss=orderssArrayList.get(position);
                orderssno=orderss.getoID();
                AlertDialog.Builder n=new AlertDialog.Builder(OrderssActivity.this)
                        .setTitle("Do You Want To Delete This Item ")
                        .setMessage("("+orderss.getoI_Name()+")")
                        .setNegativeButton("no", null)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBlite ff=new DBlite(OrderssActivity.this);
                                SQLiteDatabase db = ff.getWritableDatabase();
                                db.delete("orders","Id"+"=?",new String[]{orderssno});
                                Toast.makeText(OrderssActivity.this, " The Item Has Been Deleted ", Toast.LENGTH_SHORT).show();

                                onstartss();

                            }

                        });
                n.create();
                n.show();
                return false;
            }
        });


    }

    private void  onstartss()
    {

        orderssArrayList=new ArrayList<>(g.Getdata(OrderssActivity.this));
        orderssAdapter =new OrderssAdapter(OrderssActivity.this,orderssArrayList);
        gv.setAdapter(orderssAdapter);
        thistotal.setText("TOTAL : "+String.valueOf(g.aDouble));
    }
}
