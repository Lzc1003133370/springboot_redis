package com.entor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entor.entity.Student;
import com.entor.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@RequestMapping("/add")
	public void add(Student student) {
		studentService.add(student);
	}

	@RequestMapping("/deleteById")
	public void deleteById(int id) {
		studentService.deleteById(id);
	}

	@RequestMapping("/update")
	public void update(Student student) {
		studentService.update(student);
	}

	@RequestMapping("/queryById")
	public Student queryById(int id) {
		return studentService.queryById(id);
	}

	@RequestMapping("/queryByPage1")
	public List<Student> queryByPage1(int currentPage, int pageSize) {
		return studentService.queryByPage(currentPage, pageSize);
	}

	@RequestMapping("/queryByPage2")
	public PageInfo<Student> queryByPage2(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Student> list = studentService.queryAll();
		PageInfo<Student> pi = new PageInfo<>(list);
		return pi;
	}
}
