package com.javacorner.admin.web;

import com.javacorner.admin.entity.Student;
import com.javacorner.admin.entity.User;
import com.javacorner.admin.service.StudentService;
import com.javacorner.admin.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    private final UserService userService;
    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('Admin')")
    public String Students(Model model, @RequestParam(name = "keyword" , defaultValue = "") String keyword){
        List<Student> students = studentService.findStudentsByName(keyword);
        model.addAttribute("listStudents", students);
        model.addAttribute("keyword", keyword);
        return "student-views/students";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('Admin')")
    public String deleteStudent(Long studentId, String keyword){
        studentService.removeStudent(studentId);
        return "redirect:/students/index?keyword="+keyword;
    }

    @GetMapping("/formUpdate")
    @PreAuthorize("hasAuthority('Student')")
    public String updateStudent(Model model, Principal principal){
        Student student = studentService.loadStudentByEmail(principal.getName());
        model.addAttribute("student", student);
        return "student-views/formUpdate";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('Student')")
    public String update(Student student){
        studentService.updateStudent(student);
        return "redirect:/courses/index/student";
    }

    @GetMapping("/formCreate")
    @PreAuthorize("hasAuthority('Admin')")
    public String formCreate(Model model){
        model.addAttribute("student", new Student());
        return "student-views/formCreate";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('Admin')")
    public String save(Student student, BindingResult bindingResult){
        User user = userService.loadUserByEmail(student.getUser().getEmail());
        if(user != null){
            bindingResult.rejectValue("user.email", null, "There is already an account registered with this email");
        }
        if(bindingResult.hasErrors()) return "student-views/formCreate";
        studentService.createStudent(student.getFirstName(), student.getLastName(), student.getLevel(), student.getUser().getEmail(), student.getUser().getPassword());
        return "redirect:/students/index";
    }
}
