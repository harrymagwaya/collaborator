package com.collaborator.collaborator.frontend.views;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.collaborator.collaborator.backend.dto.PostWithAuthorDTO;
import com.collaborator.collaborator.backend.models.Post;
import com.collaborator.collaborator.backend.services.PostService;
import com.collaborator.collaborator.frontend.layout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "/all-posts", layout = MainLayout.class)
public class PostCards  extends VerticalLayout{

    @Autowired
    private PostService postService;


    
    PostCards(PostService postService){
        setSpacing(true);
        setPadding(true);

       this.postService = postService;

       List<PostWithAuthorDTO> posts = postService.getAllPosts();
       posts.forEach(post-> add(createCard(post)));
    }


    private Component createCard(PostWithAuthorDTO post){

        HorizontalLayout cardLayout = new HorizontalLayout();
        cardLayout.setWidthFull();
        cardLayout.setPadding(true);
        cardLayout.getStyle().set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");
        cardLayout.getStyle().set("border-radius", "8px");
        cardLayout.getStyle().set("margin-bottom", "1rem");

        Card postCard = new Card();

        Avatar avatar = new Avatar();
        avatar.addThemeVariants(AvatarVariant.LUMO_XSMALL);


        H3 title  = new H3(post.getPostTitle());
        Paragraph content = new Paragraph(post.getContent());
        Span auth =new Span("By " + post.getProviderName() + " . " + post.getCreatedAt());

        
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);
        layout.setVisible(true);

        layout.add(title, content, auth);

        cardLayout.add(avatar, layout);
        cardLayout.setAlignItems(Alignment.CENTER);
        cardLayout.setVisible(true);



        return postCard;
    }

}
