package com.application.Repository;

import com.application.Entity.Caregivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaregiversRepository extends JpaRepository<Caregivers, Long> {

    Caregivers findByLoginAndPassword(String login, String password);
}

