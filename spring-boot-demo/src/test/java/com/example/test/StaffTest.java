package com.example.test;

import com.example.model.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Javen
 * @date 2022/4/4
 */
@SpringBootTest
public class StaffTest {

    @Autowired
    private Staff staff;

    @Test
    public void test() {
        System.out.println(staff);
        System.out.println(staff.getId());
        System.out.println(staff.getDepartment());
        System.out.println(staff.getMap());
    }
}
