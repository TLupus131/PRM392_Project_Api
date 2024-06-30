package com.example.booking_api.Controllers;

import com.example.booking_api.Request.CommentRequest;
import com.example.booking_api.Request.UserRequest;
import com.example.booking_api.Response.CommentResponse;
import com.example.booking_api.Response.UserResponse;
import com.example.booking_api.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<CommentResponse> registerUser(@RequestBody CommentRequest request) {
        try {
            commentService.addComment(request.getContent(), request.getUserId(), request.getPropertyId());
            CommentResponse response = new CommentResponse(request.getContent(), request.getUserId(), request.getPropertyId());
            response.setMessage("Comment added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            CommentResponse response = new CommentResponse(request.getContent(), request.getUserId(), request.getPropertyId());
            response.setMessage("Error adding comment: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
