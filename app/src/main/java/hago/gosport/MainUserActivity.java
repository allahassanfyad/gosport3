package hago.gosport;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainUserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridView gv;
    GetDepartmentMarket g=new GetDepartmentMarket();
    public static String DepartMarketno;
    DepartmentAdapter departmentadapter;
    DepartmentMarket datamodel;
    ArrayList<DepartmentMarket> departmentdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gv=(GridView)findViewById(R.id.gsv);
        final SwipeRefreshLayout swpDepartment=(SwipeRefreshLayout)findViewById(R.id.swpDepartment);
        swpDepartment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                departmentdata=new ArrayList<>(g.Getdata(MainUserActivity.this));
                departmentadapter =new DepartmentAdapter(MainUserActivity.this,departmentdata);
                gv.setAdapter(departmentadapter);

                swpDepartment.setRefreshing(false);
            }
        });

        departmentdata=new ArrayList<>(g.Getdata(MainUserActivity.this));
        departmentadapter =new DepartmentAdapter(MainUserActivity.this,departmentdata);
        gv.setAdapter(departmentadapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                datamodel=departmentdata.get(i);
                DepartMarketno=datamodel.getDepartmentno();
                startActivity(new Intent(MainUserActivity.this,ItemsActivity.class));
            }
        });


//        FloatingActionButton adddepartment = (FloatingActionButton) findViewById(R.id.adddepartment);
//        adddepartment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View gationView=navigationView.getHeaderView(0);
        TextView txtuserm=gationView.findViewById(R.id.txtuserm);
        TextView txtuseremailm=gationView.findViewById(R.id.txtuseremailm);

        txtuserm.setText(SigninActivity.namem);
        txtuseremailm.setText(SigninActivity.emailm);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event)
    {
        if(KeyCode == KeyEvent.KEYCODE_BACK){
            finishAffinity();
            //do something on back.
            return true;
        }
        return  super.onKeyDown(KeyCode,event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            getSharedPreferences("MarketGo",MODE_PRIVATE)
                    .edit()
                    .clear()
                    .commit();
            startActivity(new Intent(MainUserActivity.this, SigninActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_page) {
            startActivity(new Intent(MainUserActivity.this,MainUserActivity.class));
            // Handle the camera action
        } else if (id == R.id.my_order) {

        } else if (id == R.id.notification) {

        } else if (id == R.id.settings) {

        } else if (id == R.id.about_app) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void opencurt(View view) {
                        startActivity(new Intent(MainUserActivity.this,OrderssActivity.class));

    }
}
