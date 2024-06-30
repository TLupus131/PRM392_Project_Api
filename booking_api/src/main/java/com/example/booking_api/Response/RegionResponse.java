package com.example.booking_api.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionResponse {
    private int id;
    private String name;
    private String description;
    private String message;

    public RegionResponse(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
