package com.application.Controller;

import com.application.Entity.Patients;
import com.application.Models.PatientsObject;
import com.application.Repository.PatientsRepository;
import com.application.Utils.LevelGame;
import com.google.common.hash.Hashing;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientsController {
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsController(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @PostMapping("/add")
    public PatientsObject addPatient(@Valid @RequestBody PatientsObject patientsObject) {
        patientsObject.setPassword(Hashing.sha256().hashString(patientsObject.getPassword(), StandardCharsets.UTF_8).toString());
        Patients patients = new Patients(patientsObject);
        return new PatientsObject(patientsRepository.save(patients));
    }

    @GetMapping("/login/get")
    public PatientsObject getPatients(@RequestParam(name = "login") String login,
                                      @RequestParam(name = "password") String password) throws Exception {
        try {
            String encodedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            return new PatientsObject(patientsRepository.findByLoginAndPassword(login, encodedPassword));
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient Not Found", e);
        }
    }

    @GetMapping("/get")
    public PatientsObject getPatientsByPersonalId(@RequestParam(name = "patient id") Integer id) throws Exception {
        try {
            return new PatientsObject(patientsRepository.findById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient Not Found", e);
        }
    }

    @GetMapping("/get/all")
    public List<PatientsObject> getPatients() throws Exception {
        try {
            List<Patients> patients = patientsRepository.findAll();
            return patients.stream().map(PatientsObject::new).collect(Collectors.toList());
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patients Not Found", e);
        }
    }

    @PutMapping("/put/update")
    public PatientsObject updatePatients(@Valid @RequestBody PatientsObject patientsObject) throws Exception {
        PatientsObject patients = new PatientsObject(patientsRepository.findById(patientsObject.getId()));
        patients.setInformation(patientsObject.getInformation());
        patients.setLevel(patientsObject.getLevel());
        patients.setLevelOfMMSE(patientsObject.getLevelOfMMSE());
        patients.setName(patientsObject.getName());
        patients.setSurname(patientsObject.getSurname());
        patients.setLogin(patientsObject.getLogin());
        return new PatientsObject(patientsRepository.save(new Patients(patients)));
    }

    @PutMapping("/put/updateLevelFromMMSE")
    public PatientsObject updateLevelFromMMSE(@RequestParam(name = "patient id") Integer id,
                                          @RequestParam(name = "level Of MMSE") Boolean levelOfMMSE,
                                          @RequestParam(name= "level of Game") LevelGame level) throws Exception {
        PatientsObject patients = new PatientsObject(patientsRepository.findById(id));
        patients.setLevelOfMMSE(levelOfMMSE);
        if (levelOfMMSE) {
            patients.setLevel(level);
        }
        return new PatientsObject(patientsRepository.save(new Patients(patients)));
    }

    @PutMapping("/updatePassword")
    public PatientsObject updatePassword(@RequestParam(name = "login") String login,
                                         @RequestParam(name = "old password") String oldPassword,
                                         @RequestParam(name = "new password") String newPassword) throws Exception {
        try {
            String encodedPassword = Hashing.sha256().hashString(oldPassword, StandardCharsets.UTF_8).toString();
            PatientsObject patientsObject = new PatientsObject(patientsRepository.findByLoginAndPassword(login, encodedPassword));
            encodedPassword = Hashing.sha256().hashString(newPassword, StandardCharsets.UTF_8).toString();
            patientsObject.setPassword(encodedPassword);
            return new PatientsObject(patientsRepository.save(new Patients(patientsObject)));
        } catch(Exception e) {
            throw new NotFoundException("Not found", e);
        }
    }
}
