package com.vaadin.tutorial.crm.ui.views.admin.order;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.SQLConnectionService;
import com.vaadin.tutorial.crm.ui.MainLayout;

@PageTitle("ORDER MANAGEMENT | TTR Studios POS")
@Route(value = "orderManagement", layout = MainLayout.class)
public class OrderManagment extends VerticalLayout {

    SQLConnectionService conService = new SQLConnectionService();

    public OrderManagment() {
        setSizeFull();

        addClassName("order-Management-view");
        add(
                new H1("Order Management")
        );

    }
}

