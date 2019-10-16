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

public class ItemsAdapter extends ArrayAdapter<Items> {
    Context context;
    ArrayList<Items> mcontact;

    public ItemsAdapter(Context context, ArrayList<Items> contact){
        super(context, R.layout.layoutitimes, contact);
        this.context=context;
        this.mcontact=contact;
    }

    public  class  Holder{

//       TextView nameFV;

        ImageView pic;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Items data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());

            convertView = layoutInflater.inflate(R.layout.layoutitimes, parent, false);

//            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.nameitems);
//            viewHolder.nameFV.setVisibility(convertView.INVISIBLE);

//            viewHolder.priceFV = (TextView) convertView.findViewById(R.id.priceitems);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imgitems);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }


//      viewHolder.nameFV.setText(data.getiI_Name());
//        viewHolder.priceFV.setText(data.getiPrice());
        Items items = mcontact.get(position);
        PicassoClient.downloadImage(context,items.getiLogo(),viewHolder.pic);




        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array


}
