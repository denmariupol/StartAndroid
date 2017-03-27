package com.example.lesson101_contentprovider_creation;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by den on 2017-03-26.
 */

public class MyCursorLoader extends CursorLoader {

    public MyCursorLoader(Context context) {
        super(context);
    }


    @Override
    public Cursor loadInBackground() {
        Uri myUri = Uri.parse(MyContactsProvider.CONTACT_TABLE);
        MyContactsProvider provider = new MyContactsProvider();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return provider.query(myUri,null,null,null,null,null);
        }
        return null;
    }
}
