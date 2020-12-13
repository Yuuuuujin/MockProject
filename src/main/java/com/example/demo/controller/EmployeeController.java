package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.UpdateForm;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

/**
 * 社員情報一覧・社員情報変更 Controller
 * @version 1.0
 * @author YUJIN LEE
 */
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	// 所属部署セレクタ用変数
	private Map<String, String> affi;
	// 役職セレクタ用変数
	private Map<String, String> empTitle;

	/**
	 * 所属部署セレクタの初期化メソッド
	 * @return affi
	 */
	private Map<String, String> initAffiSelect(){

		Map<String, String> affi = new LinkedHashMap<>();

		// 所属部署をMapに格納
		affi.put("役員","役員");
		affi.put("人事部", "人事部");
		affi.put("営業部", "営業部");
		affi.put("システム運用部", "システム運用部");
		affi.put("システム開発部", "システム開発部");
		affi.put("配属待ち", "配属待ち");

		return affi;
	}

	/**
	 * 役職セレクタの初期化メソッド
	 * @return empTitle
	 */
	private Map<String, String> initTitleSelect(){

		Map<String, String> empTitle = new LinkedHashMap<>();

		// 役職をMapに格納
		empTitle.put("代表取締役社長", "代表取締役社長");
		empTitle.put("代表取締役", "代表取締役");
		empTitle.put("部長", "部長");
		empTitle.put("課長", "課長");
		empTitle.put("チームリーダー", "チームリーダー");
		empTitle.put("一般社員", "一般社員");

		return empTitle;
	}

	/**
	 * 社員情報一覧画面のGET用
	 * @param model
	 * @return employee/list
	 */
	@GetMapping("/list")
	public String getEmpList(Model model) {

		// 社員情報一覧画面
		model.addAttribute("title", "社員情報一覧画面");
		// コンテンツ部分に社員情報一覧を表示するための文字列を登録
		model.addAttribute("contents", "employee/list :: list_contents");
		// 社員情報一覧の生成
		List<EmployeeDto> empList = empService.selectAll();
		//Modelに社員情報リストを登録
		model.addAttribute("empList", empList);
		//データ件数を取得
		int count = empService.count();
		model.addAttribute("empListCnt", count);

		return "employee/list";

	}

	/**
	 * 社員情報変更画面のGET用
	 * @param empId
	 * @param model
	 * @return employee/edit
	 */
	@GetMapping("/edit/{empId}")
	public String getEmpEdit(@PathVariable("empId") String empId,
			Model model) {

		// 社員情報を取得
		EmployeeDto empDto = empService.selectOne(empId);
		// Modelに登録
		model.addAttribute("updateForm", empDto);
		// 社員情報変更画面
		model.addAttribute("title", "社員情報変更画面");
		// コンテンツ部分に社員情報を表示するための文字列を登録
		model.addAttribute("contents", "employee/edit :: empEdit_contents");
		// 所属部署セレクタの初期化メソッド呼び出し
		affi = initAffiSelect();
		// 役職セレクタの初期化メソッド呼び出し
		empTitle = initTitleSelect();
		// 所属部署セレクタ用のMapをModelに登録
		model.addAttribute("affi", affi);
		// 役職セレクタ用のMapをModelに登録
		model.addAttribute("empTitle", empTitle);

		// 社員番号のチェック
		if (empId != null && empId.length() > 0) {
			UpdateForm form = new UpdateForm();

			// EmployeeDtoクラスをフォームクラスに変換
			form.setEmpId(empDto.getEmpId());
			form.setEmpName(empDto.getEmpName());
			form.setEmpKana(empDto.getEmpKana());
			form.setAffi(empDto.getAffi());
			form.setEmpTitle(empDto.getEmpTitle());
			form.setContact(empDto.getContact());
			form.setEmail(empDto.getEmail());
			form.setDateEmp(empDto.getDateEmp());

			// Modelに登録
			model.addAttribute("employeeForm", form);
		}

		return "employee/edit";
	}
	/**
	 * 社員情報変更画面のPOST用
	 * @param form
	 * @param model
	 * @return redirect:/list
	 */
	@PostMapping(value = "/edit")
	public String postEmpEdit(@ModelAttribute UpdateForm form,
			Model model) {

		// EmployeeDtoインスタンスの生成
		EmployeeDto empDto = new EmployeeDto();

		// フォームクラスをempDtoクラスに変換
		// 社員番号
		empDto.setEmpId(form.getEmpId());
		// 氏名
		empDto.setEmpName(form.getEmpName());
		// フリガナ
		empDto.setEmpKana(form.getEmpKana());
		// 所属部署
		empDto.setAffi(form.getAffi());
		// 役職
		empDto.setEmpTitle(form.getEmpTitle());
		// 連絡先
		empDto.setContact(form.getContact());
		// メールアドレス
		empDto.setEmail(form.getEmail());
		// 入社日
		empDto.setDateEmp(form.getDateEmp());

		try {

			//更新実行
			boolean updateResult = empService.update(empDto);

			if (updateResult == true) {

				model.addAttribute("updateResult", "更新成功");

			} else {
				model.addAttribute("updateResult", "更新失敗");

				return "employee/edit";
			}

		} catch(DataAccessException e) {

			model.addAttribute("updateResult", "更新失敗(トランザクションテスト)");

		}

		// 社員情報一覧画面を表示
		return "redirect:/list";
	}

	/**
	 * DataAccessException発生時の処理メソッド
	 * @param e
	 * @param model
	 * @return error
	 */
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error","内部サーバーエラー（DB）：ExceptionHandler");

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message","EmployeeControllerでDataAccessExceptionが発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

	/**
	 * Exception発生時の処理メソッド
	 * @param e
	 * @param model
	 * @return error
	 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error","内部サーバーエラー：ExceptionHandler");

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message","EmployeeControllerでExceptionが発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";

	}

}