package com.example.cashcard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyClass {

    @GetMapping("/abc")
    public static String getName()
    {
        return "Fight suvro Fight";
    }


}
