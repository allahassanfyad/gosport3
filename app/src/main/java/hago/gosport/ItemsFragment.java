package hago.gosport;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
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
public class ItemsFragment extends Fragment {
    GridView lstitmes;
    Items model;
    GetItemsMarket g=new GetItemsMarket();
    ItemsAdapter itemsAdapter;
    ArrayList<Items> itemsdata;
    EditText txtquantity;
    int minteger = 1;

    public ItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vv= inflater.inflate(R.layout.fragment_items, container, false);
        lstitmes= (GridView) vv.findViewById(R.id.gvfragmentitems);
        itemsdata=new ArrayList<>(g.getdata(MainUserActivity.DepartMarketno));
        itemsAdapter=new ItemsAdapter(getActivity(),itemsdata);
        lstitmes.setAdapter(itemsAdapter);
        lstitmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model=itemsdata.get(position);
                LayoutInflater inflater1=LayoutInflater.from(getActivity());
                View vlayout=inflater1.inflate(R.layout.layoutdetail,null);
                TextView txtnamedetail=vlayout.findViewById(R.id.txtnamedetail);
                final TextView txtpricedetail=vlayout.findViewById(R.id.txtoldpricedetail);
                TextView txtdetail=vlayout.findViewById(R.id.txtdetail);
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
                txtpricedetail.setText("Price : "+model.getiPrice()+" L.E");
                txtdetail.setText(model.getiDetails());
                PicassoClient.downloadImage(getActivity(),model.getiLogo(),imgdetail);

                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getActivity());
                bottomSheetDialog.setContentView(vlayout);
                bottomSheetDialog.show();
                imgcarty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBlite dBlite=new DBlite(getActivity());
                        Double total=Double.parseDouble(txtquantity.getText().toString())*Double.parseDouble(model.getiPrice());
                        dBlite.SaveData(model.getiSerialNo(),model.getiI_Name(),model.getiPrice(),txtquantity.getText().toString(),String.valueOf(total),model.getiLogo());
                        Toast.makeText(getActivity(), "The Items Has Been Added", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        return vv;
    }
}
