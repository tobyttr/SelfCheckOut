package com.vaadin.tutorial.crm.ui.views.admin.product;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.Product;
import com.vaadin.tutorial.crm.backend.SQLConnectionService;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.views.admin.sales.SalesView;

import java.util.Set;

@PageTitle("Update Product | TTR Studios POS")
@Route(value = "admin/product/updateProd", layout = MainLayout.class)
public class UpdateProductView extends VerticalLayout {

    SQLConnectionService conService = new SQLConnectionService();

    String ogPlu;

    TextField plu = new TextField("PLU:");
    TextField name = new TextField("Name:");
    NumberField price = new NumberField("Price:");
    TextField cid = new TextField("cId:");
    Checkbox ageRestricted = new Checkbox("Age Restricted:");
    Button updateBtn = new Button("UPDATE", event -> {conService.updateProduct(ogPlu,
            plu.getValue(),
            name.getValue(),
            price.getValue(),
            cid.getValue(),
            ageRestricted.getValue());
        runUpdateDialog();});

    public UpdateProductView() {
        ogPlu = conService.getCurrentEditingProduct();

        plu.setValue(ogPlu);
        name.setValue(conService.getProductName(ogPlu));
        price.setValue(conService.getProductPrice(ogPlu));
        cid.setValue(String.valueOf(conService.getProductCid(ogPlu)));
        ageRestricted.setValue(conService.getProductAgeRestricted(ogPlu));

        setSizeFull();
        plu.setSizeFull();
        name.setSizeFull();
        price.setSizeFull();
        cid.setSizeFull();
        ageRestricted.setSizeFull();

        addClassName("update-product-view");
        add(
                new H1("Product Management: Update Products"),
                new H4("Insert details:"),
                plu,
                name,
                price,
                cid,
                ageRestricted,
                updateBtn
        );
    }

    private void runUpdateDialog(){
        Dialog dialog2 = new Dialog();
        dialog2.add(new H2("The product was updated!"),
                new Button("Close", Event -> {
            UI.getCurrent().navigate(ProductManagmentView.class);
        }));

        dialog2.setWidth("425px");
        dialog2.setHeight("250px");

        dialog2.open();
    }
}
