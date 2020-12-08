package com.example.demo.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 社員情報登録 form
 * 社員情報変更 form
 * @version 1.0
 * @author YUJIN LEE
 */
@Data
public class EmployeeForm {

	// 社員番号
	@NotBlank
	private String empId;

	// 氏名
	@NotBlank
	private String empName;

	// フリガナ
	@NotBlank
	private String empKana;

	// 所属部署
	@NotBlank
	private String affi;

	// 役職
	@NotBlank
	private String empTitle;

	// 連絡先
	@NotBlank
	private String contact;

	// メールアドレス
	@NotBlank
	private String email;

	// 入社日
	@NotBlank
	private String dateEmp;

}
