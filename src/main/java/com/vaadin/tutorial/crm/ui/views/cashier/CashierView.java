package com.vaadin.tutorial.crm.ui.views.cashier;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.Product;
import com.vaadin.tutorial.crm.backend.SQLConnectionService;
import com.vaadin.tutorial.crm.ui.MainLayout;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@PageTitle("POS | TTR Studios POS")
@Route(value = "pos", layout = MainLayout.class)
public class CashierView extends VerticalLayout {

    SQLConnectionService conService = new SQLConnectionService();

    double totDue = 0.0;
    int numbOfLines = 0;
    int currentOid = 0;

    ArrayList<ItemLayout> itemLayoutArrayList = new ArrayList<>();

    TextField plu = new TextField("ENTER PLU OR EAN:");
    Button pluEnter = new Button("ENTER");
    NumberField amount = new NumberField("QUANTITY:");
    H2 totalDue = new H2("Total Due: " + totDue);
    TextField cosId = new TextField("CostumerID:");

    ItemLayoutHeader headerField = new ItemLayoutHeader();
    HorizontalLayout pluField = new HorizontalLayout(amount, plu, pluEnter);
    HorizontalLayout totCosIdField = new HorizontalLayout(totalDue, cosId);

    public CashierView() {

        addClassName("cashier-view");
        //setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        pluField.setSizeFull();
        pluField.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        totCosIdField.setSizeFull();
        totCosIdField.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        plu.setSizeFull();
        amount.hasControls();
        amount.setPlaceholder("1");

        add(
                new H1("POS"),
                totCosIdField,
                pluField/*,
                headerField*/
        );

        pluEnter.addClickListener(e->{
            addProduct(plu.getValue(), amount.getValue(), cosId.getValue());
            plu.clear();
            plu.focus();
        });
    }

    private void addProduct(String plu, Double amount, String cosId) {
        if (numbOfLines == 0){
            if (!cosId.equals("")){
                conService.makeNewOrder(cosId);
                int orderNumb = conService.getLatestOrderId();
                currentOid = orderNumb;
            }
        }

        numbOfLines++;

        Double quant = 1.0;
        if (amount != null){
            quant = amount;
        }

        double prodPrice = conService.getProductPrice(plu);

        conService.makeNewOrderLine(currentOid, conService.getProductName(plu), prodPrice, quant, prodPrice * quant);

        totDue = totDue + (prodPrice * quant);
        totalDue.setText("Total Due 2: " + totDue);

        for (int i = 0; i < itemLayoutArrayList.size(); i++){
            remove(itemLayoutArrayList.get(i));
        }

        ResultSet rows = conService.getAllLinesFromOid(currentOid);

        try {
            if (!rows.next()) {
                return;
            }
            do {
                ItemLayout itemLayout = new ItemLayout(rows.getString(1), rows.getString(2), rows.getDouble(3));
                itemLayoutArrayList.add(itemLayout);
                add(itemLayout);
            }
            while (rows.next());
        } catch (SQLException ex){
            System.err.println("Error encountered: " + ex.getMessage());
        }

        //delete all lines
        //add inn all lines with currentOid in reverse order so we get the latest line on top

        /*String prodName = conService.getProductName(plu);
        String prodPrice = conService.getProductPrice(plu);
        ItemLayout itemLayout = new ItemLayout(prodName, prodPrice, quant);
        totDue = totDue + (Double.parseDouble(prodPrice) * quant);
        totalDue.setText("Total Due 2: " + totDue);
        add(itemLayout);*/
    }
}

