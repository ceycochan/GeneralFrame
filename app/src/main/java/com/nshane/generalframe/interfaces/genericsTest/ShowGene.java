package com.nshane.generalframe.interfaces.genericsTest;

/**
 * Created by bryan on 2017-12-20.
 */

public class ShowGene {

    public static void getGene(){
        GenericUtils<Student> g = new GenericUtils<>();
        g.setObject(new Student());
        Teacher teacher = g.getObject();
    }


}
