package com.example.stubackend.service;

import com.example.stubackend.entity.Student;
import com.example.stubackend.exception.StudentAlreadyExistsException;
import com.example.stubackend.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.stubackend.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())) {
            throw  new StudentAlreadyExistsException(student.getEmail() + "already exists! ");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st->{
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(()-> new StudentNotFoundException("sorry, could not found this student! "));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("sorry, could not found student with this ID: " + id));
    }

    @Override
    public void deleteStudent(Long id) {
        if(! studentRepository.existsById(id)) {
            throw new StudentNotFoundException("sorry, student not found");
        }
        studentRepository.deleteById(id);
    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

}
