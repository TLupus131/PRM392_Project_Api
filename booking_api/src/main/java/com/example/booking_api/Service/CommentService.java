package com.example.booking_api.Service;

import com.example.booking_api.Models.Comment;
import com.example.booking_api.Models.Property;
import com.example.booking_api.Models.User;
import com.example.booking_api.Repository.CommentRepository;
import com.example.booking_api.Repository.PropertyRepository;
import com.example.booking_api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public void addComment(String content, int userId, int propertyId) {
        User user = userRepository.findById(userId).orElse(null);
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (user != null && property != null) {
            LocalDate localDate = LocalDate.now();
            Date date = Date.valueOf(localDate);
            Comment comment = new Comment(content, date, user, property);
            commentRepository.save(comment);
        }
    }
}
