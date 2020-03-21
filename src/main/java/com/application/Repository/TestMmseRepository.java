package com.application.Repository;

import com.application.Entity.TestMmse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMmseRepository extends JpaRepository<TestMmse, Long> {
}
