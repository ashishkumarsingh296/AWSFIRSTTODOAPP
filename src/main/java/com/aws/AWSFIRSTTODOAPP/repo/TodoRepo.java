package com.aws.AWSFIRSTTODOAPP.repo;

import com.aws.AWSFIRSTTODOAPP.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;




public interface TodoRepo extends JpaRepository<Todo, Integer> {

}