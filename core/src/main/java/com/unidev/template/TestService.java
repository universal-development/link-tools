package com.unidev.template;

import org.springframework.stereotype.Service;

@Service
public class TestService {


    public String method() {
        StringBuilder data = new StringBuilder();
        data.append("Qwe");
        return data.toString();
    }

}
