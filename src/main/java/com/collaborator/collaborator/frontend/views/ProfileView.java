package com.collaborator.collaborator.frontend.views;


import com.collaborator.collaborator.frontend.layout.MainLayout;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;

import com.vaadin.flow.component.html.Image;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "/profile", layout = MainLayout.class)
public class ProfileView extends VerticalLayout {

    ProfileView(){
        Image profileImage = new Image();
        profileImage.setSrc("null");
        profileImage.setWidth("100px");
        profileImage.setHeight("100px");
        profileImage.getStyle().set("border-radius", "50%");


       FormLayout names = new FormLayout();
       TextField naField = new TextField("Name");
       TextField emailField = new TextField("Email");
       names.add(naField, 2);
       names.add(emailField, 2);
       
       FormLayout location = new FormLayout();
       TextField locationField = new TextField("Location");
       location.add(locationField, 2);


       FormLayout areaInterest = new FormLayout();
       TextField areaIntrest = new TextField("Area of interest");
       TextArea desField = new TextArea("Description");
       areaInterest.add(areaIntrest);
       areaInterest.add(desField, 2);


        Details detailsName = new Details("Names and Contact information", names);
        Details details = new Details("Location", location);
        Details areas = new Details("Area of interest", areaInterest);

        detailsName.setOpened(true);
        details.setOpened(true);
        areas.setOpened(true);
        
       add(profileImage, detailsName, details, areas);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
    }
    
   
    
}
