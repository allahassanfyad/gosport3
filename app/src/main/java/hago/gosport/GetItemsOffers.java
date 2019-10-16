package hago.gosport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetItemsOffers {

    public List<Items> getdata(String secno)
    {
        List<Items> itemsdata=new ArrayList<>();
        Database db=new Database();
        Connection abdo=db.ConnectDB();
        ResultSet rs=db.RunSearch("select * from Items where D_ID='"+secno+"'and Discount > 0");
      try {
            while (rs.next())
          {
              Items it=new Items();
              it.setiSerialNo(rs.getString(1));
              it.setiPrice(rs.getString(2));
              it.setiDetails(rs.getString(3));
              it.setiDiscount(rs.getString(4));
              it.setiI_Name(rs.getString(5));
              it.setiQuantity(rs.getString(6));
              it.setiLogo(rs.getString(7));
              it.setiU_UserName(rs.getString(8));
              it.setiD_Name(rs.getString(9));
              it.setiShops_ID(rs.getString(10));
              it.setiD_ID(rs.getString(11));
              itemsdata.add(it);
          }

      }
      catch(SQLException eٍٍ)
      {
          eٍٍ.printStackTrace();

      }
        return itemsdata;
    }


}

