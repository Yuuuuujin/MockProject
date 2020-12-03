package com.example.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.EmployeeDto;

/**
 * 社員情報 EmployeeMapper
 * @version 1.0
 * @author YUJIN LEE
 */
@Mapper
public interface EmployeeMapper {

    // employee_infoテーブルの件数を取得
    public int count();

	// 登録用メソッド
	public boolean insert (EmployeeDto empDto);

	// 1件検索用メソッド
	public EmployeeDto selectOne(EmployeeDto empDto);

	// 全件検索用メソッド
	public List<EmployeeDto> selectAll();

	// 更新用メソッド
	public boolean update(EmployeeDto empDto);

	// 削除用メソッド
	public boolean delete(String empId);

	// キーワードで検索
	public List<EmployeeDto> search (
			@Param("empId") String empId,
			@Param("dateEmp")Date dateEmp,
			@Param("empName") String empName,
			@Param("empKana") String empKana,
			@Param("affi") String affi,
			@Param("empTitle") String empTitle,
			@Param("contact") String contact,
			@Param("email") String email);
}
