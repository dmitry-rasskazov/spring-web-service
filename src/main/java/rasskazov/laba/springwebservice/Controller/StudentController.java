package rasskazov.laba.springwebservice.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rasskazov.laba.springwebservice.Entity.Student;
import rasskazov.laba.springwebservice.Repository.StudentRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@Controller
public class StudentController
{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(@NotNull StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/list")
    public ModelAndView getAllStudents() {
        log.info("/list -> connection");

        ModelAndView model = new ModelAndView("list-students");
        model.addObject("students", studentRepository.findAll());
        return model;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm()
    {
        ModelAndView model = new ModelAndView("add-student-form");
        Student student = new Student();

        model.addObject("student", student);
        return model;
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student)
    {
        studentRepository.save(student);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long studentId) {
        ModelAndView model = new ModelAndView("add-student-form");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = new Student();
        if(optionalStudent.isPresent()) {
            student = optionalStudent.get();
        }

        model.addObject("student", student);
        return model;
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentId) {
        studentRepository.deleteById(studentId);
        return "redirect:/list";
    }
}
