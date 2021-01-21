package com.vaadin.tutorial.crm.ui.views.admin.customer;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.SQLConnectionService;
import com.vaadin.tutorial.crm.ui.MainLayout;

@PageTitle("CUSTOMER MANAGEMENT | TTR Studios POS")
@Route(value = "customerManagement", layout = MainLayout.class)
public class CustomerManagmentView extends VerticalLayout {

    SQLConnectionService conService = new SQLConnectionService();

    public CustomerManagmentView() {
        setSizeFull();

        addClassName("customer-Management-view");
        add(
                new H1("Customer Management")
        );

    }
}

