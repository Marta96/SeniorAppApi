package com.application.Repository;

import com.application.Entity.TestMmse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMmseRepository extends JpaRepository<TestMmse, Long> {

    List<TestMmse> findAllByIdPatientsOrderByDateDesc(Integer id);

    TestMmse findFirstByIdPatientsOrderByDateDesc(Integer id);

}


