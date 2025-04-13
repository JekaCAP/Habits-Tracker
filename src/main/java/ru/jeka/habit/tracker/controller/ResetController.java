//package ru.jeka.habit.tracker.controller;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.jeka.habit.tracker.Service.DataResetService;
//
//@RestController
//@RequestMapping("/reset-data")
//public class ResetController {
//
//    private final DataResetService dataResetService;
//
//    public ResetController(DataResetService dataResetService) {
//        this.dataResetService = dataResetService;
//    }
//
//    // Только администратор может сбросить данные
//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public void resetDatabase() {
//        dataResetService.resetData();
//    }
//}