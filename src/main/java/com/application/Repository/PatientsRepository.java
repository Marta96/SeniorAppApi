package com.application.Repository;

import com.application.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {

    Patients findByLoginAndPasswordAndActive(String login, String password, Boolean active);

    Patients findByLoginAndActive(String login, Boolean active);

    List<Patients> findAllByActive(Boolean active);

    Patients findByIdAndActive(Integer id, Boolean active);
}
