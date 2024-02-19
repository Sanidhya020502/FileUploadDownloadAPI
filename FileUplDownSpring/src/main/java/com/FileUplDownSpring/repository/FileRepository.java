package com.FileUplDownSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FileUplDownSpring.model.FileEntity;


@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long>{

}
