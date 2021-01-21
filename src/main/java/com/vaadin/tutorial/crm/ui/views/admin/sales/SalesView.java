package com.vaadin.tutorial.crm.ui.views.admin.sales;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.SQLConnectionService;
import com.vaadin.tutorial.crm.ui.MainLayout;

@PageTitle("SALES | TTR Studios POS")
@Route(value = "sales", layout = MainLayout.class)
public class SalesView extends VerticalLayout {

    SQLConnectionService conService = new SQLConnectionService();

    public SalesView() {
        setSizeFull();

        addClassName("sales-view");
        add(
                new H1("Sales")
        );

    }
}

