package hago.gosport;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderssAdapter extends ArrayAdapter<Orderss> {
    Context context;
    ArrayList<Orderss> mcontact;

    public OrderssAdapter(Context context, ArrayList<Orderss> mcontact){
        super(context, R.layout.layoutorderss, mcontact);
        this.context=context;
        this.mcontact=mcontact;
    }

    public  class  Holder{

        TextView nameFV;
        TextView priceFV;
        TextView qtyFV;
        TextView totalFV;
        ImageView pic;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Orderss data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());

            convertView = layoutInflater.inflate(R.layout.layoutorderss, parent, false);

            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txtnameorederss);
            viewHolder.priceFV = (TextView) convertView.findViewById(R.id.txtpriceorderss);
            viewHolder.qtyFV = (TextView) convertView.findViewById(R.id.txtqtyorderss);
            viewHolder.totalFV = (TextView) convertView.findViewById(R.id.txttotalorderss);

            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imgorderss);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }



        PicassoClient.downloadImage(context,mcontact.get(position).getoLogo(),viewHolder.pic);

        viewHolder.nameFV.setText(data.getoI_Name());
        viewHolder.priceFV.setText("Price : "+data.getoPrice()+" L.E ");
        viewHolder.qtyFV.setText("Quantity : "+data.getoQuantity());
        viewHolder.totalFV.setText("Total : "+data.getoTotal()+" L.E ");


        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array


}
