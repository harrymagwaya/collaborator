package com.collaborator.collaborator.frontend.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.router.Route;

@Route("/login")
public class LoginView extends Div {

    LoginView(){
        getStyle().set("background-color", "var(--lumo-contrast-5pct)")
                .set("display", "flex").set("justify-content", "center")
                .set("padding", "var(--lumo-space-l)");

        // LoginI18n i18n = LoginI18n.createDefault();

        // LoginI18n.Form i18nForm = i18n.getForm();
        // i18nForm.setTitle("");
        // i18nForm.setUsername("Käyttäjänimi");
        // i18nForm.setPassword("Salasana");
        // i18nForm.setSubmit("Kirjaudu sisään");
        // i18nForm.setForgotPassword("Unohtuiko salasana?");
        // i18n.setForm(i18nForm);

        // LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        // i18nErrorMessage.setTitle("Väärä käyttäjätunnus tai salasana");
        // i18nErrorMessage.setMessage(
        //         "Tarkista että käyttäjätunnus ja salasana ovat oikein ja yritä uudestaan.");
        // i18n.setErrorMessage(i18nErrorMessage);

        // LoginForm loginForm = new LoginForm();
        // loginForm.setI18n(i18n);
        // add(loginForm);
        // // Prevent the example from stealing focus when browsing the
        // // documentation
        // loginForm.getElement().setAttribute("no-autofocus", "");



        // See login-rich-content.css
        addClassName("login-rich-content");

        LoginForm loginForm = new LoginForm();
        loginForm.getElement().getThemeList().add("dark");
        add(loginForm);
        // Prevent the example from stealing focus when browsing the
        // documentation
        loginForm.getElement().setAttribute("no-autofocus", "");

    } 
    }
    
    

