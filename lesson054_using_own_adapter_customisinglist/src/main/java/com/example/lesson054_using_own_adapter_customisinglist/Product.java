package com.example.lesson054_using_own_adapter_customisinglist;

/**
 * Created by den on 2017-01-20.
 */

public class Product {
    String name;
    int price;
    int image;
    boolean box;


    Product(String _describe, int _price, int _image, boolean _box) {
        name = _describe;
        price = _price;
        image = _image;
        box = _box;
    }
}
