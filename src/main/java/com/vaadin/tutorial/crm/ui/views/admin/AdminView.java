package com.vaadin.tutorial.crm.ui.views.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.tutorial.crm.Application;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.views.admin.customer.CustomerManagmentView;
import com.vaadin.tutorial.crm.ui.views.admin.order.OrderManagment;
import com.vaadin.tutorial.crm.ui.views.admin.product.ProductManagmentView;
import com.vaadin.tutorial.crm.ui.views.admin.sales.SalesView;

@PageTitle("ADMIN | TTR Studios POS")
@Route(value = "admin", layout = MainLayout.class)
public class AdminView extends VerticalLayout {

    Button sales = new Button("Sales");
    Button prodManagment = new Button("Product Managment");
    Button customerManagment = new Button("Customer Managment");
    Button orderManagment = new Button("Order Managment");

    public AdminView() {
        addClassName("admin-view");
        add(
                new H1("Admin"),
                new H4("Select the function you want to use:"),
                new HorizontalLayout(sales, prodManagment, customerManagment, orderManagment)
        );

        sales.addClickListener(e->{
            UI.getCurrent().navigate(SalesView.class);
        });

        prodManagment.addClickListener(e->{
            UI.getCurrent().navigate(ProductManagmentView.class);
        });

        customerManagment.addClickListener(e->{
            UI.getCurrent().navigate(CustomerManagmentView.class);
        });

        orderManagment.addClickListener(e->{
            UI.getCurrent().navigate(OrderManagment.class);
        });
    }
}
