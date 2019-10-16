package hago.gosport;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GetOrderssMarket {
    public static Double aDouble;
    public static int totale;
    public List<Orderss> Getdata(Context cn)
    {
        totale=0;
        aDouble=0.0;
        List<Orderss> orderssdata=new ArrayList<>();
        DBlite db=new DBlite(cn);
        SQLiteDatabase cc=db.getReadableDatabase();
        Cursor rs=cc.rawQuery("select * from Orders",null);
      try {
            while (rs.moveToNext())
          {
              Orderss or=new Orderss();
              or.setoID(rs.getString(0));
              or.setoSerialNo(rs.getString(1));
              or.setoI_Name(rs.getString(2));
              or.setoPrice(rs.getString(3));
              or.setoQuantity(rs.getString(4));
              or.setoTotal(rs.getString(5));
              or.setoLogo(rs.getString(6));
              totale+=Integer.parseInt(or.getoQuantity());
              aDouble +=Double.valueOf(or.getoTotal());
              orderssdata.add(or);
          }

      }
      catch(SQLiteAbortException eٍٍ)
      {
          eٍٍ.printStackTrace();

      }
        return orderssdata;
    }


}

