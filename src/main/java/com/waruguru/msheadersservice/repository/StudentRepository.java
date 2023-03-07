package com.waruguru.msheadersservice.repository;

import com.waruguru.msheadersservice.entity.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long> {

}
