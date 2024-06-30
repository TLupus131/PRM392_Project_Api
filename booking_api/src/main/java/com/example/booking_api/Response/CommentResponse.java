package com.example.booking_api.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private String content;
    private int userId;
    private int propertyId;
    private String message;

    public CommentResponse(String content, int userId, int propertyId) {
        this.content = content;
        this.userId = userId;
        this.propertyId = propertyId;
    }
}
