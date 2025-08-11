package com.mountreach.bloodhub.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.mountreach.bloodhub.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ImageSlider imageSlider;
    LinearLayout btnRequestBlood,btnDonate;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.ishomeimageslider);
        btnRequestBlood = view.findViewById(R.id.requestforblood);
        btnDonate = view.findViewById(R.id.donate);


        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner8, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner6, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner7, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner8, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        imageSlider.setSlideAnimation(AnimationTypes.FIDGET_SPINNER);

        btnRequestBlood.setOnClickListener(v -> {
            Fragment requestFragment = new RequestForBloodFragment();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, requestFragment)
                    .addToBackStack(null)
                    .commit();
        });

        btnDonate.setOnClickListener(v -> {
            Fragment donateFragment = new DonateFragment();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,donateFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
