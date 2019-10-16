package hago.gosport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBlite extends SQLiteOpenHelper {
    public DBlite( Context context) {
        super(context, "GoSport", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Orders (Id INTEGER,ItimeNo TEXT,ItimeName TEXT,Price TEXT,Qty TEXT,Total TEXT,Image TEXT,PRIMARY KEY(Id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void SaveData(String nodb,String namedb,String pricedb,String qtydb,String totaldb,String imagedb) {
        ContentValues data=new ContentValues();
                data.put("ItimeNo",nodb);
                data.put("ItimeName",namedb);
                data.put("Price",pricedb);
                data.put("Qty",qtydb);
                data.put("Total",totaldb);
                data.put("Image",imagedb);
                SQLiteDatabase db=getWritableDatabase();
                db.insert("Orders",null,data);

    }
}
