package com.example.lesson054_using_own_adapter_customisinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by den on 2017-01-20.
 */

public class BoxAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;

    BoxAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null)
            view = lInflater.inflate(R.layout.item,parent,false);

        Product p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена и картинка
        ((TextView)view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView)view.findViewById(R.id.tvPrice)).setText(p.price);
        ((ImageView)view.findViewById(R.id.ivImage)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox)view.findViewById(R.id.cbBox);
        // присваиваем чекбоксу обработчик
        cbBuy.setOnCheckedChangeListener(this);
    }

    // товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    // содержимое корзины
    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : objects) {
            // если в корзине
            if (p.box)
                box.add(p);
        }
        return box;
    }

    // обработчик для чекбоксов
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // меняем данные товара (в корзине или нет)
        getProduct((Integer)buttonView.getTag().)
    }
}
