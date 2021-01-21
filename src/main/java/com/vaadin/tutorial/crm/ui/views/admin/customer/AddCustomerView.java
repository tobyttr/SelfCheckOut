package com.vaadin.tutorial.crm.ui.views.admin.customer;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
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

@PageTitle("CUSTOMER: ADD | TTR Studios POS")
@Route(value = "admin/customer/add", layout = MainLayout.class)
public class AddCustomerView extends VerticalLayout {

    SQLConnectionService conService = new SQLConnectionService();

    TextField fName = new TextField("First name:");
    TextField lName = new TextField("Last name:");
    TextField email = new TextField("Email:");
    TextField phone = new TextField("Phone:");
    Button addCustomer = new Button("Add customer");

    public AddCustomerView() {
        setSizeFull();
        fName.setSizeFull();
        lName.setSizeFull();
        email.setSizeFull();
        phone.setSizeFull();
        addCustomer.setSizeFull();

        addClassName("admin-view");
        add(
                new H1("Admin: Add Products"),
                new H4("Insert details:"),
                fName,
                lName,
                email,
                phone,
                addCustomer
        );

        addCustomer.addClickListener(e->{
            conService.makeNewCustomer(fName.getValue(), lName.getValue(), email.getValue(), phone.getValue());

            Dialog dialog = new Dialog();
            dialog.add(new Text(fName.getValue() + " is now registered as a customer with the customer ID: " + conService.getLatestCosId()));

            fName.clear();
            lName.clear();
            email.clear();
            phone.clear();

            dialog.setWidth("400px");
            dialog.setHeight("150px");

            dialog.open();

        });

    }
}
