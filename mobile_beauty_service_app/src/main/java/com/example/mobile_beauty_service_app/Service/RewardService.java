package com.example.mobile_beauty_service_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.mobile_beauty_service_app.Entity.Customer;
import com.example.mobile_beauty_service_app.Entity.Reward;
import com.example.mobile_beauty_service_app.Repository.CustomerRepository;
import com.example.mobile_beauty_service_app.Repository.RewardRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RewardService {
    
    @Autowired
    private RewardRepository rewardRepository;

    public Reward createOrUpdateReward(Reward reward) {
        validateReward(reward);
        return rewardRepository.save(reward);
    }

    public Reward updateReward(Long id, Reward reward) {
        if (!rewardRepository.existsById(id)) {
            throw new NoSuchElementException("Reward with ID " + id + " not found.");
        }
        validateReward(reward);
        reward.setId(id);
        return rewardRepository.save(reward);
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public void deleteReward(Long id) {
        if (!rewardRepository.existsById(id)) {
            throw new NoSuchElementException("Reward with ID " + id + " not found.");
        }
        rewardRepository.deleteById(id);
    }

    private void validateReward(Reward reward) {
        if (reward.getName() == null || reward.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Reward name cannot be empty.");
        }
        if (reward.getPoints() < 0) {
            throw new IllegalArgumentException("Points must be a positive value.");
        }
    }
        @Autowired
    private CustomerRepository customerRepository;

    public Reward createReward(Long customerId, Reward reward) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        reward.setCustomer(customer);
        return rewardRepository.save(reward);
    }
    public List<Reward> getRewardsByPointsJPQL(int points) {
        return rewardRepository.findRewardsByPoints(points);
    }
    public List<Reward> getRewardsByPointsJPA(int points) {
        return rewardRepository.findByPointsGreaterThan(points);
    }
    public Optional<Reward> getRewardByNameJPQL(String name) {
        return rewardRepository.findRewardByName(name);
    }
    public Optional<Reward> getRewardByNameJPA(String name) {
        return rewardRepository.findByName(name);
    }
     public Page<Reward> getAllRewards(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Reward> rewards = rewardRepository.findAll(sort);
        int start = Math.min((int) pageable.getOffset(), rewards.size());
        int end = Math.min((start + pageable.getPageSize()), rewards.size());
        return new PageImpl<>(rewards.subList(start, end), pageable, rewards.size());
    }

    public Page<Reward> getRewardsByPoints(int points, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Reward> rewards = rewardRepository.findAll(sort)
                .stream()
                .filter(reward -> reward.getPoints() > points)
                .toList();
        int start = Math.min((int) pageable.getOffset(), rewards.size());
        int end = Math.min((start + pageable.getPageSize()), rewards.size());
        return new PageImpl<>(rewards.subList(start, end), pageable, rewards.size());
    }

}
