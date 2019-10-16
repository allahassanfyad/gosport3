package hago.gosport;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDepartmentMarket {

    public List<DepartmentMarket> Getdata(Context cn)
    {
        List<DepartmentMarket> data=new ArrayList<>();
        Database db=new Database();
        Connection abdo=db.ConnectDB();
    if (abdo==null)
    {
        Toast.makeText(cn, "Check internet Access", Toast.LENGTH_SHORT).show();

    }else {
        ResultSet rs=db.RunSearch("select * from Department");
      try {
            while (rs.next())
          {
              DepartmentMarket it=new DepartmentMarket();
              it.setDepartmentno(rs.getString(1));
              it.setDepartmenttxt(rs.getString(2));
              it.setDepartmentimg(rs.getString(4));
              data.add(it);
          }

      }
      catch(SQLException eٍٍ)
      {
          eٍٍ.printStackTrace();

      }

    }

        return data;
}
}
