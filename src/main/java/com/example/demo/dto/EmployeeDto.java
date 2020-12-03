package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 社員情報DTOクラス
 * @version 1.0
 * @author YUJIN LEE
 */
@Data
public class EmployeeDto implements Serializable{

	// 社員番号
	private String empId;

	// 氏名
	private String empName;

	// フリガナ
	private String empKana;

	// 所属部署
	private String affi;

	// 役職
	private String empTitle;

	// 連絡先
	private String contact;

	// メールアドレス
	private String email;

	// 入社日
	private Date dateEmp;

	// 内容変更理由
	private String reason;

	// 変更日
	private Date updateDt;

	// 担当者名
	private String pic;

}