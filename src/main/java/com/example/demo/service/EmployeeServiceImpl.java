package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.SearchForm;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.mapper.EmployeeMapper;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper empMapper;

	@Override
	public int count(){

		return empMapper.count();
	}

	@Override
	public boolean insert(EmployeeDto empDto){

		return empMapper.insert(empDto);
	}

	@Override
	public EmployeeDto selectOne(String empId){

		return empMapper.selectOne(empId);
	}

	@Override
	public List<EmployeeDto> selectAll(){

		return empMapper.selectAll();
	}

	@Override
	public boolean update(EmployeeDto empDto){

		return empMapper.update(empDto);
	}

	@Override
	public boolean delete(String empId){

		return empMapper.delete(empId);
	}

	@Override
	public List<EmployeeDto> search(SearchForm form) {
		// TODO 自動生成されたメソッド・スタブ
		return empMapper.search(form);
	}

}
