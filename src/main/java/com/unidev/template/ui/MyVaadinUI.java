package com.unidev.template.ui;


import com.unidev.platform.utils.RandomUtils;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class MyVaadinUI extends UI {

    @Autowired
    private RandomUtils randomUtils;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new Label("Hello! I'm the root UI! " + randomUtils.randomValue("A", "B", "C")));
    }
}