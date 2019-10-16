package hago.gosport;

import android.content.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAddress {

    public List<Address> getdata(Context context,String usrename)
    {
        List<Address> addresses=new ArrayList<>();
        Database db=new Database();
        Connection abdo=db.ConnectDB();
        ResultSet rs=db.RunSearch("select * from Address where U_UserName ='"+usrename+"'");
      try {
            while (rs.next())
          {
              Address at=new Address();
              at.setaA_Name(rs.getString(1));
              at.setaU_UserName(rs.getString(2));
              at.setaBuilding_No(rs.getString(3));
              at.setaLongitude(rs.getString(4));
              at.setaLatitude(rs.getString(5));
              at.setaPhone(rs.getString(6));
              at.setaStreet_Name(rs.getString(7));
              addresses.add(at);
          }

      }
      catch(SQLException eٍٍ)
      {
          eٍٍ.printStackTrace();

      }
        return addresses;
    }


}

