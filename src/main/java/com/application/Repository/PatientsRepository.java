package com.application.Repository;

import com.application.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {

    Patients findByLoginAndPassword(String login, String password);

    @Override
    List<Patients> findAll();

    Patients findByPersonalIdentity(String personalIdentity);
}
