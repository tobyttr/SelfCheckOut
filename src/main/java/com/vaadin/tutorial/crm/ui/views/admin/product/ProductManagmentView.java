package com.vaadin.tutorial.crm.ui.views.admin.product;

import ch.qos.logback.core.Layout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.Product;
import com.vaadin.tutorial.crm.backend.SQLConnectionService;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.views.admin.sales.SalesView;
import com.vaadin.tutorial.crm.ui.views.cashier.ItemLayout;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@PageTitle("PRODUCT MANAGEMENT | TTR Studios POS")
@Route(value = "productManagement", layout = MainLayout.class)
public class ProductManagmentView extends VerticalLayout {

    SQLConnectionService conService = new SQLConnectionService();

    List<Product> productList = new ArrayList<>();

    Grid<Product> currentGrid = new Grid<>(Product.class);

    Button addProd = new Button("Add Products");
    Button updateProd = new Button("Update Products");
    Button deleteProd = new Button("Delete Products");

    public ProductManagmentView() {
        setSizeFull();

        currentGrid.setItems(productList);
        currentGrid.setColumns("plu", "name", "price", "cid", "ageRestricted");

        updateProductGrid();

        addClassName("prduct-Management-view");
        add(
                new H1("Product Management"),
                new HorizontalLayout(addProd, updateProd, deleteProd),
                currentGrid
        );

        updateProd.addClickListener(e->{
            Set<Product> productSet = currentGrid.getSelectedItems();
            Product product = productSet.iterator().next();
            String ogPlu = String.valueOf(product.getPlu());

            conService.saveCurrentEditingProduct(ogPlu);

            UI.getCurrent().navigate(UpdateProductView.class);
        });

    }

    private void updateProductGrid (){
        ResultSet rows = conService.getAllProducts();
        int ogProdListSize = productList.size();

        System.out.println(ogProdListSize);

        for (int i = 0; i < ogProdListSize; i++){
            productList.remove(i);
        }

        try {
            if (!rows.next()) {
                return;
            }
            do {
                productList.add(new Product(
                        rows.getLong(1),
                        rows.getString(2),
                        rows.getDouble(3),
                        rows.getInt(4),
                        rows.getBoolean(5)));
            }
            while (rows.next());
        } catch (SQLException ex){
            System.err.println("Error encountered: " + ex.getMessage());
        }

        currentGrid.getDataProvider().refreshAll();
    }
}

