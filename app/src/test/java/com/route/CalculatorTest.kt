package com.route

import org.junit.Assert.*
import org.junit.Test
import java.lang.ArithmeticException

class CalculatorTest{

    @Test
    fun `add when negative input then neg output`(){
        // arrange : defines inputs
        val calculator = Calculator();
        val n1=-10; val n2=-20;
        //act:call function and store result
        val res = calculator.add(n1,n2);
        //assert: assert if result not expected
        assertEquals(-30,res)
    }
    @Test
    fun `add when zero input then zero output`(){
        // arrange : defines inputs
        val calculator = Calculator();
        val n1=0; val n2=0;
        //act:call function and store result
        val res = calculator.add(n1,n2);
        //assert: assert if result not expected
        assertEquals(0,res)
    }
    @Test(expected = ArithmeticException::class)
    fun `divide when zero divisor then throw exception`(){
        // arrange : defines inputs
        val calculator = Calculator();
        val n1=0; val n2=0;
        //act:call function and store result
        val res = calculator.divide(n1,n2);
        //assert: assert if result not expected

    }

}