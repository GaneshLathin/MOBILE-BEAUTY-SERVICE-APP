package com.example.mobile_beauty_service_app.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mobile_beauty_service_app.Entity.Reward;
public interface RewardRepository extends JpaRepository<Reward,Long> {
    @Query("SELECT r FROM Reward r WHERE r.points > :points")
    List<Reward> findRewardsByPoints(@Param("points") int points);

    @Query("SELECT r FROM Reward r WHERE r.name = :name")
    Optional<Reward> findRewardByName(@Param("name") String name);

    List<Reward> findByPointsGreaterThan(int points);

    Optional<Reward> findByName(String name);


}
