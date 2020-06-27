package com.application.Controller;

import com.application.Entity.Caregivers;
import com.application.Models.CaregiversObject;
import com.application.Repository.CaregiversRepository;
import com.google.common.hash.Hashing;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/caregivers")
public class CaregiversController {
    private final CaregiversRepository caregiversRepository;

    @Autowired
    public CaregiversController(CaregiversRepository caregiversRepository) {
        this.caregiversRepository = caregiversRepository;
    }

    @PostMapping("/add")
    public CaregiversObject createCaregivers(@Valid @RequestBody CaregiversObject caregiversObject) {
        caregiversObject.setPassword(Hashing.sha256().hashString(caregiversObject.getPassword(), StandardCharsets.UTF_8).toString());
        Caregivers caregivers = new Caregivers(caregiversObject);
        return new CaregiversObject(caregiversRepository.save(caregivers));
    }

    @GetMapping("/get")
    public CaregiversObject getCaregivers(@RequestParam(name = "login") String login,
                                          @RequestParam(name = "password") String password) throws Exception {
        try {
            String encodedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            return new CaregiversObject(caregiversRepository.findByLoginAndPassword(login, encodedPassword));
        } catch(Exception e) {
            throw new NotFoundException("Not found", e);
        }
    }

    @PutMapping("/updatePassword")
    public CaregiversObject updatePassword(@RequestParam(name = "login") String login,
                                           @RequestParam(name = "old password") String oldPassword,
                                           @RequestParam(name = "new password") String newPassword) throws Exception {
        try {
            String encodedPassword = Hashing.sha256().hashString(oldPassword, StandardCharsets.UTF_8).toString();
            CaregiversObject caregiversObject = new CaregiversObject(caregiversRepository.findByLoginAndPassword(login, encodedPassword));
            encodedPassword = Hashing.sha256().hashString(newPassword, StandardCharsets.UTF_8).toString();
            caregiversObject.setPassword(encodedPassword);
            return new CaregiversObject(caregiversRepository.save(new Caregivers(caregiversObject)));
        } catch(Exception e) {
            throw new NotFoundException("Not found", e);
        }
    }
}
