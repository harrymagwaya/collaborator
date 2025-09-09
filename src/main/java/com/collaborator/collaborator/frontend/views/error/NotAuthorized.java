package com.collaborator.collaborator.frontend.views.error;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/not-authorized")
public class NotAuthorized extends HorizontalLayout {
    NotAuthorized(){

        Dialog checkDialog = new Dialog();
        checkDialog.setHeaderTitle("Status of Application");

        Button ok = new Button("OK");
        ok.addClickListener(event -> checkDialog.close());

        VerticalLayout dialogLayout = new VerticalLayout();
        checkDialog.add(dialogLayout, ok);

        
        Button checkButton = new Button("Tap to check status");
        checkButton.addClickListener(event -> checkDialog.open()
        );

        getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                .set("bottom", "0").set("left", "0").set("display", "flex")
                .set("align-items", "center").set("justify-content", "center");

        
                add(checkDialog, checkButton);

    }
}
