package com.example.booking_api.Service;

import com.example.booking_api.Models.Region;
import com.example.booking_api.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> allRegion() {
        return regionRepository.findAll();
    }

    public List<Region> getRegionsByRequest(String text) {
        return regionRepository.getRegionsByRequest(text);
    }

    public void add(String name, String description) {
        regionRepository.save(new Region(name, description));
    }

    @Transactional
    public void update(int id, String name, String description) {
        try {
            Region region = regionRepository.findById(id).orElse(null);
            region.setName(name);
            region.setDescription(description);
            regionRepository.save(region);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void delete(int id) {
        try {
            Region region = regionRepository.findById(id).orElse(null);
            regionRepository.delete(region);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
