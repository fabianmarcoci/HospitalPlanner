package org.example.HospitalPlanner.manager;

import org.example.HospitalPlanner.model.Account;
import org.example.HospitalPlanner.model.Doctor;
import org.example.HospitalPlanner.model.Patient;
import org.example.HospitalPlanner.service.modelService.AccountService;
import org.example.HospitalPlanner.service.modelService.DoctorService;
import org.example.HospitalPlanner.service.modelService.PatientService;
import org.example.HospitalPlanner.service.network.Server;
import org.example.HospitalPlanner.ui.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDate;

@Component
public class HospitalManager {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private PatientService patientService;
    public void createDoctorAccount(String username, String password, String name, LocalDate birthDate, String gender,
                                    String citizenship, String mail, String specialization, LocalDate debutDate) {
        Doctor newDoctor = new Doctor();
        newDoctor.setName(name);
        newDoctor.setBirthDate(birthDate);
        newDoctor.setAge();
        newDoctor.setGender(gender);
        newDoctor.setCitizenship(citizenship);
        newDoctor.setMail(mail);
        newDoctor.setRole("doctor");
        newDoctor.setSpecialization(specialization);
        newDoctor.setStartedWorkingAt(debutDate);
        newDoctor.setYearsOfExperience();
        doctorService.createDoctor(newDoctor);

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setPerson(newDoctor);
        accountService.createAccount(account);
    }

    public void createPatientAccount(String username, String password, String name, LocalDate birthDate, String gender,
                                    String citizenship, String mail, String disease) {
        Patient newPatient = new Patient();
        newPatient.setName(name);
        newPatient.setBirthDate(birthDate);
        newPatient.setAge();
        newPatient.setGender(gender);
        newPatient.setCitizenship(citizenship);
        newPatient.setMail(mail);
        newPatient.setRole("patient");
        newPatient.setDisease(disease);
        patientService.createPatient(newPatient);

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setPerson(newPatient);
        accountService.createAccount(account);
    }

    public void listDoctors() {
        for (Doctor doctor : doctorService.getAllDoctors()) {
            System.out.println(doctor);
        }
    }

    public void listPatients() {
        for (Patient patients : patientService.getAllPatients()) {
            System.out.println(patients);
        }
    }

    public void listAccounts() {
        for (Account account : accountService.getAllAccounts()) {
            System.out.println(account);
        }
    }
    public void run() {
        LocalDate vladBirthDate = LocalDate.of(1985, 1, 10); // January 10th, 1985
        LocalDate vladDebut = LocalDate.of(2010, 11, 15);
//        createDoctorAccount("vlad10","vlad10", "Vlad", vladBirthDate, "masculine", "romanian",
//                "vladalexandrugheras@yahoo.com", "lung", vladDebut);
        listDoctors();

        LocalDate fabianBirthDate = LocalDate.of(2002, 8, 25); // August 25th, 2002
//        createPatientAccount("fabian25","fabian25", "Fabian",
//                fabianBirthDate, "masculine", "romanian",
//                "marcocifabian16@gmail.com", "lung");
        listPatients();

        new Thread(() -> {
            Server server = new Server();
            server.startServer();
        }).start();

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}

