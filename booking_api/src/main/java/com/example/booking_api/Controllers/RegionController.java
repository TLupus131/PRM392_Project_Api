package com.example.booking_api.Controllers;

import com.example.booking_api.Models.Region;
import com.example.booking_api.Request.RegionRequest;
import com.example.booking_api.Response.RegionResponse;
import com.example.booking_api.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegion() {
        return new ResponseEntity<>(regionService.allRegion(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Region>> getRequestProperty(
            @RequestParam String text) {
        List<Region> regions = regionService.getRegionsByRequest(text);
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<RegionResponse> add(@RequestBody RegionRequest request) {
        try {
            regionService.add(request.getName(), request.getDescription());
            RegionResponse response = new RegionResponse(request.getId(), request.getName(), request.getDescription());
            response.setMessage("Added region successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RegionResponse response = new RegionResponse(request.getId(), request.getName(), request.getDescription());
            response.setMessage("Error adding region: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<RegionResponse> update(@RequestBody RegionRequest request) {
        try {
            regionService.update(request.getId(), request.getName(), request.getDescription());
            RegionResponse response = new RegionResponse(request.getId(), request.getName(), request.getDescription());
            response.setMessage("Updated region successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RegionResponse response = new RegionResponse(request.getId(), request.getName(), request.getDescription());
            response.setMessage("Error updating region: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RegionResponse> delete(@PathVariable Integer id) {
        try {
            regionService.delete(id);
            RegionResponse response = new RegionResponse(id, "", "");
            response.setMessage("Deleted region successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RegionResponse response = new RegionResponse(id, "", "");
            response.setMessage("Error deleting region: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
