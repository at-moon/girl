package com.atmoon.repository;

import com.atmoon.pojo.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girl,Integer>, JpaSpecificationExecutor<Girl> {

    List<Girl> findByAge(Integer age);

}
