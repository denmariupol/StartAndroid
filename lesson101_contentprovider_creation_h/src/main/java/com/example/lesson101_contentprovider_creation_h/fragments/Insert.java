package com.example.lesson101_contentprovider_creation_h.fragments;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson101_contentprovider_creation_h.MainActivity;
import com.example.lesson101_contentprovider_creation_h.MyProvider;
import com.example.lesson101_contentprovider_creation_h.R;
import com.example.lesson101_contentprovider_creation_h.databinding.FragmentInsertBinding;
import com.example.lesson101_contentprovider_creation_h.interfaces.IUpdateCursor;


/**
 * A simple {@link Fragment} subclass.
 */
public class Insert extends Fragment{

    Activity activity;
    public Insert() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentInsertBinding insertBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_insert,container,false);
        View v  = insertBinding.getRoot();

        insertBinding.insertRecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                String text = insertBinding.insertTextField.getText().toString();
                if(text.length() > 0) {
                    cv.put(MyProvider.TEXT, text);
                    getContext().getContentResolver().insert(MyProvider.CONTENT_URI, cv);
                    getActivity().getSupportFragmentManager().beginTransaction().remove(Insert.this).commit();
                    insertBinding.insertTextField.clearFocus();

//                    Cursor cursor = null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                        cursor = getContext().getContentResolver().query(MyProvider.CONTENT_URI,null,null,null,null,null);
//                    }
//                    ((IUpdateCursor)activity).updateCursor(cursor);
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

//    public interface IUpdateCursor{
//        void updateCursor(Cursor newCursor);
//    }
}


