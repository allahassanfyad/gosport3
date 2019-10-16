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

public class DepartmentAdapter extends ArrayAdapter<DepartmentMarket> {
    Context context;
    ArrayList<DepartmentMarket> mcontact;

    public DepartmentAdapter(Context context, ArrayList<DepartmentMarket> mcontact){
        super(context, R.layout.layoutdepartment, mcontact);
        this.context=context;
        this.mcontact=mcontact;
    }

    public  class  Holder{

//        TextView nameFV;

        ImageView pic;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        DepartmentMarket data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());

            convertView = layoutInflater.inflate(R.layout.layoutdepartment, parent, false);

//            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txtitmes);

            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imgsuper);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }



        PicassoClient.downloadImage(context,mcontact.get(position).getDepartmentimg(),viewHolder.pic);

//        viewHolder.nameFV.setText(data.getDepartmenttxt());


        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array


}
