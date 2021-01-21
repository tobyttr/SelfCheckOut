package com.vaadin.tutorial.crm.ui.views.admin.product;

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

@PageTitle("ADMIN: Add Product | TTR Studios POS")
@Route(value = "admin/addProd", layout = MainLayout.class)
public class AddProductsView extends VerticalLayout {

    SQLConnectionService conService = new SQLConnectionService();

    TextField plu = new TextField("PLU:");
    TextField name = new TextField("Name:");
    NumberField price = new NumberField("Price");
    TextField cid = new TextField("CID:");
    Checkbox ageRestricted = new Checkbox("Age Restricted:");
    Button addProduct = new Button("Add product");

    public AddProductsView() {
        setSizeFull();
        plu.setSizeFull();
        name.setSizeFull();
        price.setSizeFull();
        cid.setSizeFull();
        ageRestricted.setSizeFull();
        addProduct.setSizeFull();

        addClassName("add-products-view");
        add(
                new H1("Admin: Add Products"),
                new H4("Insert details:"),
                plu,
                name,
                price,
                cid,
                ageRestricted,
                addProduct
        );

        addProduct.addClickListener(e->{
            conService.sqlAddProduct(plu.getValue(), name.getValue(), price.getValue(), cid.getValue(), ageRestricted.getValue());

            Dialog dialog = new Dialog();
            dialog.add(new Text(name.getValue() + " is now registered as a product with the plu: " + plu.getValue()));

            plu.clear();
            name.clear();
            price.clear();
            cid.clear();
            ageRestricted.clear();

            dialog.setWidth("400px");
            dialog.setHeight("150px");

            dialog.open();
        });

    }
}
