package com.unidev.template.ui;


import com.unidev.platform.utils.RandomUtils;
import com.unidev.template.TestService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class MyVaadinUI extends UI {

    @Autowired
    private RandomUtils randomUtils;

    @Autowired
    private TestService testService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);
        Button button = new Button("Test button " + testService.method());
        Label label = new Label("Hello! I'm the root UI! " + randomUtils.randomValue("A", "B", "C"));

        verticalLayout.addComponent(button);
        verticalLayout.addComponent(label);

        setContent(verticalLayout);
    }
}