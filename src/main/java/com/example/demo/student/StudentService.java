package com.example.demo.student;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    final private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentRepository.findAll();

    }

    @PostMapping
    public void addStudent(Student student){
        Optional<Student> studentEmail = studentRepository.findStudentByEmail((student.getEmail()));
        if(studentEmail.isPresent()) {
            throw new IllegalStateException("Email is already taken.");
        }else {
            studentRepository.save(student);
        }
    }

    @DeleteMapping(path="{studentId}")
    public void removeStudent(Long studentId){
        boolean isExisting = studentRepository.existsById(studentId);
        if(!isExisting){
            throw new IllegalStateException("Student with id "+studentId+" does not exist.");
        }else{
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId,String name, String email){
        Student student = studentRepository.findById((studentId))
                .orElseThrow(()->new IllegalStateException("Student with "+studentId+" does not exist."));

        if(name!=null&&name.length()>0&&!Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email!=null&&email.length()>0&&!Objects.equals(student.getEmail(),email)){
           Optional<Student> studentEmail = studentRepository.findStudentByEmail(email);
            if(studentEmail.isPresent()){
                throw new IllegalStateException("Email is already taken.");
            }else{
                student.setEmail(email);
            }
        }


    }

}
