package com.vaadin.tutorial.crm.ui.views.cashier;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ItemLayoutHeader extends HorizontalLayout {

    public ItemLayoutHeader() {
        setSizeFull();

        Paragraph nameHeader = new Paragraph("Product Name:");
        Paragraph priceHeader = new Paragraph("Price:");
        //Button deleteHeader = new Button("Delete:");

        nameHeader.setSizeFull();
        priceHeader.setSizeFull();

        add(nameHeader, priceHeader);
    }
}
