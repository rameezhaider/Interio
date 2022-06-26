package com.google.ar.core.examples.java.helloar;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import static com.google.ar.core.examples.java.helloar.MainActivity.*;
import static com.google.ar.core.examples.java.helloar.MainActivity.size_width;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KitchenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KitchenFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton desk1;
    private ImageButton desk2;
    private ImageButton desk3;
    private ImageButton desk4;
    private ImageButton desk5;
    private ImageButton desk6;
    private ImageButton back_button_Kitchen;
    public KitchenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KitchenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KitchenFragment newInstance(String param1, String param2) {
        KitchenFragment fragment = new KitchenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_kitchen, container, false);
        back_button_Kitchen = (ImageButton)v.findViewById(R.id.back_button3);
        back_button_Kitchen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new MainFragment());
                fragmentTransaction.commit();
            }
        });

        desk1 = (ImageButton) v.findViewById(R.id.desk1);
        desk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/table3.obj";
                    png_file = "models/table_texture6.png";
                    size_height=2;
                    size_width=0.76;
                    isObjectReplaced = true;
                }
            }
        });
        desk2 = (ImageButton) v.findViewById(R.id.desk2);
        desk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk2.obj";
                    png_file = "models/table_texture5.png";
                    size_height=2;
                    size_width=1.25;
                    isObjectReplaced = true;
                }
            }
        });
        desk3 = (ImageButton) v.findViewById(R.id.desk3);
        desk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk2.obj";
                    png_file = "models/table_texture5.png";
                    size_height=2;
                    size_width=1.25;
                    isObjectReplaced = true;
                }
            }
        });
        desk4 = (ImageButton) v.findViewById(R.id.desk4);
        desk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk2.obj";
                    png_file = "models/table_texture5.png";
                    size_height=2;
                    size_width=1.25;
                    isObjectReplaced = true;
                }
            }
        });
        desk5 = (ImageButton) v.findViewById(R.id.desk5);
        desk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk2.obj";
                    png_file = "models/table_texture5.png";
                    size_height=2;
                    size_width=1.25;
                    isObjectReplaced = true;
                }
            }
        });
        desk6 = (ImageButton) v.findViewById(R.id.desk6);
        desk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk2.obj";
                    png_file = "models/table_texture5.png";
                    size_height=2;
                    size_width=1.25;
                    isObjectReplaced = true;
                }
            }
        });
        return v;
    }
}