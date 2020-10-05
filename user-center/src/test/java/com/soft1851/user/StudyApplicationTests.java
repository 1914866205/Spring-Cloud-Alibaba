package com.soft1851.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudyApplicationTests {



    @Test
    public void test(){
        int c = 3;
        if(c>0)
            if(c>3)
                c = 2;
            else c = 3;
        else c = 4;
        System.out.println(c);
    }
}
