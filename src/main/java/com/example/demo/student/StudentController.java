package com.example.demo.student;


import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }


    @PostMapping
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }
    @DeleteMapping(path="{studentId}")
    public void removeStudent(@PathVariable("studentId") Long studentId){
        studentService.removeStudent(studentId);
    }
    
    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required=false) String name,
                              @RequestParam(required=false) String email){
        studentService.updateStudent(studentId, name, email);
    }



}
