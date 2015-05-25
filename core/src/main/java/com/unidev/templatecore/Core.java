package com.unidev.templatecore;

import org.springframework.stereotype.Service;

@Service
public class Core {

    public String invokeMe() {
        return new StringBuilder().append("InvokeMe()").toString();
    }

}
