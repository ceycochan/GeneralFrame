package com.nshane.generalframe.interfaces.generics;

/**
 * Created by bryan on 2017-12-20.
 */

public class GenericUtils<Man> {
    private Man m;
    public void setObject(Man man){
        this.m= man;
    }

    public Man getObject(){
        return m;
    }
}
