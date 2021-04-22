package com.route

import java.lang.ArithmeticException

class Calculator {

    fun add(n1:Int,n2:Int):Int{
        return n1+n2;
    }
    fun divide(n1:Int,n2:Int):Int{
        if(n2==0)throw ArithmeticException("error can't divide by zero")
        return n1/n2;
    }
}