package hago.gosport;

import android.content.Intent;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class OnbordingActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AhoyOnboarderCard ahoyOnboarderCard1=new AhoyOnboarderCard("Introduction", "Welcome To Go Sport The App Provide Every Kind Of Sport Equipment That Belong To Any Sport Including Sport Clothes And Also Provide The Best Transport Serves  \n",R.drawable.image1);
        ahoyOnboarderCard1.setBackgroundColor(R.color.colorbackonbord);
        ahoyOnboarderCard1.setTitleColor(R.color.colorWhith);
        setImageBackground(R.drawable.backdemart);
        ahoyOnboarderCard1.setDescriptionColor(R.color.colorWhith);
        ahoyOnboarderCard1.setTitleTextSize(dpToPixels(14, this));
        ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(6, this));
        ahoyOnboarderCard1.setIconLayoutParams(240, 240, 50, 16, 16, 50);


        AhoyOnboarderCard ahoyOnboarderCard2=new AhoyOnboarderCard("The Rules","to insure a best service you wish for, you have to follow this rules \n1-do not order a product without the information that require \n2-Do Not Order Any Items without The Right Address You Want To Receive Your Order In It  \n3-make sure you choose the right product  before confirming your orders   \n ",R.drawable.image2);
        ahoyOnboarderCard2.setBackgroundColor(R.color.colorbackonbord);
        setImageBackground(R.drawable.backdemart);
        ahoyOnboarderCard2.setTitleColor(R.color.colorWhith);
        ahoyOnboarderCard2.setDescriptionColor(R.color.colorWhith);
        ahoyOnboarderCard2.setTitleTextSize(dpToPixels(15, this));
        ahoyOnboarderCard2.setDescriptionTextSize(dpToPixels(5, this));
        ahoyOnboarderCard2.setIconLayoutParams(240, 240, 50, 16, 16, 50);


        AhoyOnboarderCard ahoyOnboarderCard3=new AhoyOnboarderCard("To Be Safe", "Make Sure To Keep Your Profile Information Secured To Be Safe \n",R.drawable.image3);
        ahoyOnboarderCard3.setBackgroundColor(R.color.colorbackonbord);
        setImageBackground(R.drawable.backdemart);
        ahoyOnboarderCard3.setTitleColor(R.color.colorWhith);
        ahoyOnboarderCard3.setDescriptionColor(R.color.colorWhith);
        ahoyOnboarderCard3.setTitleTextSize(dpToPixels(15, this));
        ahoyOnboarderCard3.setDescriptionTextSize(dpToPixels(7, this));
        ahoyOnboarderCard3.setIconLayoutParams(240, 240, 50, 16, 16, 50);

        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        setOnboardPages(pages);



        setActiveIndicatorColor(R.color.colorPrimary);
        setInactiveIndicatorColor(R.color.white);


        //Set finish button text
        setFinishButtonTitle("Register Now");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rrres));
        }

    }
    @Override
    public void onFinishButtonPressed() {

        startActivity(new Intent(this,RegistrationActivity.class));
    }

}
