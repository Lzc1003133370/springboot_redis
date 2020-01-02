package com.entor.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entor.entity.Student;

public interface IStudentService {

	public void add(Student student);

	public void deleteById(int id);

	public void update(Student student);

	public Student queryById(int id);

	public List<Student> queryAll();

	public List<Student> queryByPage(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
}
