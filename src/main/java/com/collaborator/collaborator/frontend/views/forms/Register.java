package com.collaborator.collaborator.frontend.views.forms;

import com.collaborator.collaborator.backend.models.RegisterUser;
import com.collaborator.collaborator.backend.models.UserCollab;
import com.collaborator.collaborator.backend.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("/register")
public class Register extends VerticalLayout {
    private final UserService userCollabService;
    private final Binder<RegisterUser> binder = new Binder<>(RegisterUser.class);

    public Register(UserService userCollabService) {
        this.userCollabService = userCollabService;

        // UI components
        TextField userName = new TextField("Provider Name");
        EmailField email = new EmailField("Email");
        PasswordField password = new PasswordField("Password");
        TextField areaOfExpertise = new TextField("Area of Expertise");
        
        TextField location = new TextField("Location");
 

        Button saveButton = new Button("Save", event -> {
            RegisterUser user = new RegisterUser();
            if (binder.writeBeanIfValid(user)) {
                userCollabService.registerUser(user);
                Notification.show("Saved successfully");
            } else {
                Notification.show("Validation failed");
            }
        });

        FormLayout formLayout = new FormLayout(
            userName, email, password,
            areaOfExpertise,
            location
        );

        binder.forField(userName).asRequired().bind(RegisterUser::getUserName, RegisterUser::setUserName);
        binder.forField(email).asRequired().bind(RegisterUser::getEmail, RegisterUser::setEmail);
        binder.forField(password).asRequired().bind(RegisterUser::getPassword, RegisterUser::setPassword);
        binder.forField(areaOfExpertise).bind(RegisterUser::getAreaOfExpertise, RegisterUser::setAreaOfExpertise);
        binder.forField(location).bind(RegisterUser::getLocation, RegisterUser::setLocation);
   
        add(formLayout, saveButton);
    }
}
