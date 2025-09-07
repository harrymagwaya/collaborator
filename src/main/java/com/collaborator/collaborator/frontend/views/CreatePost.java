package com.collaborator.collaborator.frontend.views;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;

import com.collaborator.collaborator.backend.models.Post;
import com.collaborator.collaborator.backend.models.UserCollab;
import com.collaborator.collaborator.backend.repositories.PostRepository;
import com.collaborator.collaborator.backend.repositories.UserCollabRepository;
import com.collaborator.collaborator.backend.services.PostService;
import com.collaborator.collaborator.frontend.layout.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route(value = "/create-post", layout = MainLayout.class)
public class CreatePost extends VerticalLayout {

    private final Binder<Post> binder = new Binder<>(Post.class);

    
    public CreatePost(PostService postService, PostRepository postRepo,UserCollabRepository userRepo ) {
        // Fields
        TextField postTitle = new TextField("Post Title");
        TextArea content = new TextArea("Content");
        ComboBox<String> postType = new ComboBox<>("Post Type");
        postType.setItems("announcements", "update", "event");

        Button submit = new Button("Submit Post");

        // Layout
        FormLayout formLayout = new FormLayout(postTitle, content, postType);
        add(new H2("Create a New Post"), formLayout, submit);

        // Bind fields
        binder.forField(postTitle).asRequired("Title is required").bind(Post::getTitle,
                Post::setTitle);
        binder.forField(content).asRequired("Content is required").bind(Post::getPostContent,
                Post::setPostContent);
        // binder.forField(postType).asRequired("Type is required").bind(Post::getPostType,
        //         Post::setPostType);

        // Submit logic
        submit.addClickListener(event -> {
            Post post = new Post();
            if (binder.writeBeanIfValid(post)) {
                // 🔐 Get authenticated user's email
                String email = SecurityContextHolder.getContext().getAuthentication().getName();

                // 🔍 Lookup provider by email
                Optional<UserCollab> providerOpt = userRepo.findByEmail(email);

                if (providerOpt.isPresent()) {
                    post.setAuthor(providerOpt.get());
                    postRepo.save(post);
                    Notification.show("Post created successfully");
                } else {
                    Notification.show("Authenticated user is not a registered provider", 3000,
                            Notification.Position.MIDDLE);
                }
            } else {
                Notification.show("Please fill all required fields", 3000, Notification.Position.MIDDLE);
            }
        });
    }
}
