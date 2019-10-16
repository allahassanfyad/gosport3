package hago.gosport;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends Fragment {

    GridView lstitmes;
    Items model;
    GetItemsOffers g=new GetItemsOffers();
    ItemsAdapter itemsAdapter;
    ArrayList<Items> itemsdata;
    EditText txtquantity;
    int minteger = 1;
    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_offers, container, false);
        lstitmes= (GridView) vv.findViewById(R.id.gvfragmentoffers);
        itemsdata=new ArrayList<>(g.getdata(MainUserActivity.DepartMarketno));
        itemsAdapter=new ItemsAdapter(getActivity(),itemsdata);
        lstitmes.setAdapter(itemsAdapter);
        lstitmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model=itemsdata.get(position);
                LayoutInflater inflater1=LayoutInflater.from(getActivity());
                View vlayout=inflater1.inflate(R.layout.layoutdetailo,null);
                TextView txtnamedetail=vlayout.findViewById(R.id.txtnamedetail);
                final TextView txtpricedetail2=vlayout.findViewById(R.id.txtpricedetail2);
                TextView txtdetail=vlayout.findViewById(R.id.txtdetail);
                TextView txtdecawodetail=vlayout.findViewById(R.id.txtdecawodetail);
                TextView txtoldpricedetail=vlayout.findViewById(R.id.txtoldpricedetail);
                ImageView imgdetail=vlayout.findViewById(R.id.imgdetail);
                txtquantity=vlayout.findViewById(R.id.txtquantity);
                Button increaseinteger=vlayout.findViewById(R.id.increaseinteger);
                Button decreaseinteger=vlayout.findViewById(R.id.decreaseinteger);
                increaseinteger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        minteger += 1;
                        txtquantity.setText(String.valueOf(minteger));
                    }
                });
                decreaseinteger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        minteger -= 1;
                        if(minteger<1)
                        {
                            minteger = 1;
                        }
                        txtquantity.setText(String.valueOf(minteger));
                    }
                });
                ImageView imgcarty=vlayout.findViewById(R.id.imgcarty);
                txtnamedetail.setText(model.getiI_Name());
                 final Double pricenew=Double.valueOf(model.getiPrice())*(1-Double.valueOf(model.getiDiscount()));
                txtpricedetail2.setText("New Price : "+pricenew+" L.E");
                txtdetail.setText(model.getiDetails());
                txtdecawodetail.setText("Discount : "+Double.valueOf(model.getiPrice())*Double.valueOf(model.getiDiscount())+" L.E");
                txtoldpricedetail.setText("Old Price : "+model.getiPrice()+" L.E");
                PicassoClient.downloadImage(getActivity(),model.getiLogo(),imgdetail);

                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getActivity());
                bottomSheetDialog.setContentView(vlayout);
                bottomSheetDialog.show();
                imgcarty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBlite dBlite=new DBlite(getActivity());
                        Double total=Double.parseDouble(txtquantity.getText().toString())*pricenew;
                        dBlite.SaveData(model.getiSerialNo(),model.getiI_Name(),pricenew.toString(),txtquantity.getText().toString(),String.valueOf(total),model.getiLogo());
                        Toast.makeText(getActivity(), "The Items Has Been Added", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        return vv;
    }

}
