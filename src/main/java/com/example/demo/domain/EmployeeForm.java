package com.example.demo.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.example.demo.domain.valid.ValidGroup1;
import com.example.demo.domain.valid.ValidGroup2;
import com.example.demo.domain.valid.ValidGroup3;

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
	@NotBlank(groups = ValidGroup1.class, message = "{require_check}")
    @Length(groups = ValidGroup2.class, message = "{empId_length_check}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class, message = "{empId_pattern_check}")
	private String empId;

	// 氏名
	@NotBlank(groups = ValidGroup1.class, message = "{require_check}")
    @Length(groups = ValidGroup2.class, message = "{empName_length_check}")
    @Pattern(regexp = "^[^-~｡-ﾟ]*$", groups = ValidGroup3.class, message = "{empName_pattern_check}")
	private String empName;

	// フリガナ
	@NotBlank(groups = ValidGroup1.class, message = "{require_check}")
    @Length(groups = ValidGroup2.class, message = "{empKana_length_check}")
    @Pattern(regexp = "^[ァ-ヶー]*$", groups = ValidGroup3.class, message = "{empKana_pattern_check}")
	private String empKana;

	// 所属部署
	@NotBlank(groups = ValidGroup1.class, message = "{affi_require_check}")
	private String affi;

	// 役職
	@NotBlank(groups = ValidGroup1.class, message = "{empTitle_require_check}")
	private String empTitle;

	// 連絡先
	@NotBlank(groups = ValidGroup1.class, message = "{require_check}")
    @Length(groups = ValidGroup2.class, message = "{contact_length_check}")
    @Pattern(regexp = "^\\d{2,4}-\\d{2,4}-\\d{4}$", groups = ValidGroup3.class, message = "{contact_pattern_check}")
	private String contact;

	// メールアドレス
	@NotBlank(groups = ValidGroup1.class, message = "{require_check}")
	@Email(groups = ValidGroup3.class, message = "{email_check}")
	private String email;

	// 入社日
	@NotBlank(groups = ValidGroup1.class, message = "{require_check}")
	@Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", groups = ValidGroup3.class, message = "{dateEmp_pattern_check}")
	private String dateEmp;

}
