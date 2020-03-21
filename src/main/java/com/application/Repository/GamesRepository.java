package com.application.Repository;

import com.application.Entity.Games;
import com.application.Utils.NameGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {

    List<Games> findAllByPatientId(Integer id);

    List<Games> findAllByPatientIdAndName(int patientId, NameGame name);

    List<Games> findAllByDateAfterAndPatientIdAndNameOrderByDate(Date date, int patientId, NameGame name);
}
