package com.vaadin.tutorial.crm.ui.views.main;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.ui.MainLayout;

@PageTitle("MAIN | TTR Studios POS")
@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    public MainView() {
        addClassName("main-view");
        add(
                new H1("Welcome."),
                new H2("Use the sidebar to select your desired program.")
        );
    }
}