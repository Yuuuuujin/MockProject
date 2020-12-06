package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 社員情報DTOクラス
 * @version 1.0
 * @author YUJIN LEE
 */
@Entity
@Data
@Table(name="employee_info")
public class EmployeeDto{

	// 社員番号
	@Id
	@Column(name="emp_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String empId;

	// 氏名
	@Column(name="emp_name")
	private String empName;

	// フリガナ
	@Column(name="emp_kana")
	private String empKana;

	// 所属部署
	@Column(name="affiliation")
	private String affi;

	// 役職
	@Column(name="emp_title")
	private String empTitle;

	// 連絡先
	@Column(name="contact")
	private String contact;

	// メールアドレス
	@Column(name="email")
	private String email;

	// 入社日
	@Column(name="date_emp")
	private String dateEmp;

}