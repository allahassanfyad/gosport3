package hago.gosport;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YAT on 26/10/2017.
 */

public class AddressAdapter extends ArrayAdapter<Address> {
    Context context;
    ArrayList<Address> mcontact;

    public AddressAdapter(Context context, ArrayList<Address> mcontact){
        super(context, R.layout.layoutaddress, mcontact);
        this.context=context;
        this.mcontact=mcontact;
    }

    public  class  Holder{

      TextView nameFV;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Address data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());

            convertView = layoutInflater.inflate(R.layout.layoutaddress, parent, false);
            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txtaddress);



            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }




     viewHolder.nameFV.setText(data.getaA_Name());


        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array


}
