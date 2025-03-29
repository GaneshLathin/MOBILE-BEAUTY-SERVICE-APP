package com.example.mobile_beauty_service_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.mobile_beauty_service_app.Entity.Reward;
import com.example.mobile_beauty_service_app.Service.RewardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rewards")
public class RewardController {
    
    @Autowired
    private RewardService rewardService;

    // Create Reward
    @PostMapping
    public Reward createReward(@RequestBody Reward reward) {
        return rewardService.createOrUpdateReward(reward);
    }

    // Get All Rewards
    @GetMapping
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }

    // Update Reward
    @PutMapping("/{id}")
    public Reward updateReward(@PathVariable Long id, @RequestBody Reward reward) {
        return rewardService.updateReward(id, reward);
    }

    // Delete Reward
    @DeleteMapping("/{id}")
    public String deleteReward(@PathVariable Long id) {
        rewardService.deleteReward(id);
        return "Reward with ID " + id + " has been successfully deleted.";
    }
    @PostMapping("/{customerId}")
    public Reward addReward(@PathVariable Long customerId, @RequestBody Reward reward) {
        return rewardService.createReward(customerId, reward);
    }
    @GetMapping("/points/jpql/{points}")
    public List<Reward> getRewardsByPointsJPQL(@PathVariable int points) {
        return rewardService.getRewardsByPointsJPQL(points);
    }
    @GetMapping("/points/jpa/{points}")
    public List<Reward> getRewardsByPointsJPA(@PathVariable int points) {
        return rewardService.getRewardsByPointsJPA(points);
    }
    @GetMapping("/name/jpql/{name}")
    public Optional<Reward> getRewardByNameJPQL(@PathVariable String name) {
        return rewardService.getRewardByNameJPQL(name);
    }
    @GetMapping("/name/jpa/{name}")
    public Optional<Reward> getRewardByNameJPA(@PathVariable String name) {
        return rewardService.getRewardByNameJPA(name);
    }
    @GetMapping("/page/sort")
    public Page<Reward> getAllRewards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return rewardService.getAllRewards(page, size, sortBy, direction);
    }

    @GetMapping("/points")
    public Page<Reward> getRewardsByPoints(
            @RequestParam int points,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return rewardService.getRewardsByPoints(points, page, size, sortBy, direction);
    }
}
