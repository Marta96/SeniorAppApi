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
        try {
            patientsObject.setPassword(Hashing.sha256().hashString(patientsObject.getPassword(), StandardCharsets.UTF_8).toString());
            patientsObject.setActive(true);
            Patients patients = new Patients(patientsObject);
            return new PatientsObject(patientsRepository.save(patients));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Patient with login or personal id exists", e);
        }
    }

    @GetMapping("/login/get")
    public PatientsObject getPatients(@RequestParam(name = "login") String login,
                                      @RequestParam(name = "password") String password) throws Exception {
        try {
            String encodedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            return new PatientsObject(patientsRepository.findByLoginAndPasswordAndActive(login, encodedPassword, true));
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient Not Found", e);
        }
    }

    @GetMapping("/get")
    public PatientsObject getPatientsByPersonalId(@RequestParam(name = "patient id") Integer id) throws Exception {
        try {
            return new PatientsObject(patientsRepository.findByIdAndActive(id, true));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient Not Found", e);
        }
    }

    @GetMapping("/get/all")
    public List<PatientsObject> getPatients() throws Exception {
        try {
            List<Patients> patients = patientsRepository.findAllByActive(true);
            return patients.stream().map(PatientsObject::new).collect(Collectors.toList());
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patients Not Found", e);
        }
    }

    @PutMapping("/put/update")
    public PatientsObject updatePatients(@Valid @RequestBody PatientsObject patientsObject) throws Exception {
        try {
        Patients patient = patientsRepository.findByIdAndActive(patientsObject.getId(), true);
        PatientsObject patients = new PatientsObject(patient);
        patients.setInformation(patientsObject.getInformation());
        patients.setLevel(patientsObject.getLevel());
        patients.setLevelOfMMSE(patientsObject.getLevelOfMMSE());
        patients.setName(patientsObject.getName());
        patients.setPassword(patient.getPassword());
        patients.setSurname(patientsObject.getSurname());
        patients.setLogin(patientsObject.getLogin());
        return new PatientsObject(patientsRepository.save(new Patients(patients)));
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patients Not Found", e);
        }
    }

    @PutMapping("/put/updateLevelFromMMSE")
    public PatientsObject updateLevelFromMMSE(@RequestParam(name = "patient id") Integer id,
                                          @RequestParam(name = "level Of MMSE") Boolean levelOfMMSE,
                                          @RequestParam(name= "level of Game") LevelGame level) throws Exception {
        try {
        Patients patient = patientsRepository.findByIdAndActive(id, true);
        PatientsObject patients = new PatientsObject(patient);
        patients.setLevelOfMMSE(levelOfMMSE);
        if (levelOfMMSE) {
            patients.setLevel(level);
        }
        patients.setPassword(patient.getPassword());
        return new PatientsObject(patientsRepository.save(new Patients(patients)));
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patients Not Found", e);
        }
    }

    @PutMapping("/updatePassword")
    public PatientsObject updatePassword(@RequestParam(name = "login") String login,
                                         @RequestParam(name = "new password") String newPassword) throws Exception {
        try {
            String encodedPassword = Hashing.sha256().hashString(newPassword, StandardCharsets.UTF_8).toString();
            PatientsObject patientsObject = new PatientsObject(patientsRepository.findByLoginAndActive(login, true));
            encodedPassword = Hashing.sha256().hashString(newPassword, StandardCharsets.UTF_8).toString();
            patientsObject.setPassword(encodedPassword);
            return new PatientsObject(patientsRepository.save(new Patients(patientsObject)));
        } catch(Exception e) {
            throw new NotFoundException("Not found", e);
        }
    }

    @PutMapping("/delete")
    public PatientsObject deletePatient(@RequestParam(name = "patient id") Integer id) {
        try {
        Patients patient = patientsRepository.findByIdAndActive(id, true);
        PatientsObject patients = new PatientsObject(patient);
        patients.setActive(false);
        patients.setPassword(patient.getPassword());
        return new PatientsObject(patientsRepository.save(new Patients(patients)));
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patients Not Found", e);
        }
    }
}
