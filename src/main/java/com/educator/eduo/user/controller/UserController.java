package com.educator.eduo.user.controller;

import com.educator.eduo.user.model.entity.Assistant;
import com.educator.eduo.user.model.entity.Hire;
import com.educator.eduo.user.model.entity.Student;
import com.educator.eduo.user.model.entity.Teacher;
import com.educator.eduo.user.model.service.AuthService;
import com.educator.eduo.user.model.service.UserService;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/api/teacher")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> modifyTeacher(@RequestBody Teacher teacher) throws SQLException {
        if (!userService.updateTeacher(teacher)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/assistant")
    @PreAuthorize("hasRole('ROLE_ASSISTANT')")
    public ResponseEntity<?> modifyAssistant(@RequestBody Assistant assistant) throws SQLException {
        if (!userService.updateAssistant(assistant)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/student")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> modifyStudent(@RequestBody Student student) throws SQLException {
        if (!userService.updateStudent(student)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/hire")
    @PreAuthorize("hasRole('ROLE_ASSISTANT')")
    public ResponseEntity<?> applyAssistant(@RequestBody Hire hire) throws SQLException {
        userService.insertHire(hire);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
