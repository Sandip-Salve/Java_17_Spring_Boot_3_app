package com.app.Recap;

import com.app.Recap.Entities.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTest {

    @Test
    public void sumTesting(){
        assertThrows(ArithmeticException.class,()->{
            int result = 10/0;
            System.out.println(result);
        });
    }

    @Test
    public void shouldBeNull(){
        assertNull(new Employee());
    }
}
