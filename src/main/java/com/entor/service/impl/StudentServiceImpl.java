package com.entor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.entor.entity.Student;
import com.entor.mapper.StudentMapper;
import com.entor.service.IStudentService;

@Service
@CacheConfig(cacheNames = "students")
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public void add(Student student) {
		studentMapper.add(student);
	}

	@Override
	@CacheEvict(allEntries=true)//清除分组下所有的缓存
	public void deleteById(int id) {
		studentMapper.deleteById(id);
	}

	@Override
	public void update(Student student) {
		studentMapper.update(student);
	}

	@Override
	@Cacheable(key = "'student_'+#p0") // @Cacheable作用：先从缓存中查询是否有数据，如果有直接取出返回，否者查询数据库返回值保存在缓存。
										// 如果传递id是23，则保存到redis中key的名称是student_23
	public Student queryById(int id) {
		return studentMapper.queryById(id);
	}

	@Override
	@Cacheable(key = "'student_'+#p0+'_'+#p1")
	public List<Student> queryByPage(int currentPage, int pageSize) {
		return studentMapper.queryByPage((currentPage - 1) * pageSize, pageSize);
	}

	@Override
	@Cacheable(key = "'student_'+#p0+'_'+#p1")
	public List<Student> queryAll() {
		return studentMapper.queryAll();
	}

}
