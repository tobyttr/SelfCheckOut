package com.vaadin.tutorial.crm.ui.views.cashier;

import com.fasterxml.jackson.core.io.NumberInput;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public class ItemLayout extends HorizontalLayout {

    public ItemLayout(String nameTxt, String priceTxt, Double amount) {
        setSizeFull();

        Double totalPrice = Double.parseDouble(priceTxt) * amount;

        Paragraph name = new Paragraph(nameTxt);
        Paragraph price = new Paragraph(priceTxt);
        Paragraph quantity = new Paragraph("x" + String.valueOf(amount));
        Paragraph totPrice = new Paragraph(String.valueOf(totalPrice));

        Button deleteBtn = new Button("X");

        name.setSizeFull();
        price.setSizeFull();
        quantity.setSizeFull();
        totPrice.setSizeFull();

        add(name, price, quantity, totPrice, deleteBtn);

        deleteBtn.addClickListener(e->{
            remove(name);
        });
    }
}
