package hago.gosport;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by YAT on 25/11/2017.
 */

public class PicassoClient {

    public static void downloadImage(Context context, String url, ImageView imageView)
    {

        if(url != null && url.length()>0)
        {
            Picasso.with(context).load(url).placeholder(R.drawable.logo).into(imageView);
        }
        else
        {
            Picasso.with(context).load(R.drawable.logo).into(imageView);
        }
    }
}