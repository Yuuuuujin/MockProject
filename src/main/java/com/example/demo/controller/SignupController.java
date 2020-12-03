package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.EmployeeForm;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/signup")
public class SignupController {

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
	 * @return title
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
	 * 社員情報登録画面のGET用コントローラー
	 * @param form
	 * @param model
	 * @return employee/signup
	 */
	@GetMapping
	public String getSignUp(@ModelAttribute EmployeeForm form,
			Model model) {

		// 社員情報登録画面
		model.addAttribute("title", "社員情報登録画面");

		// 所属部署セレクタの初期化メソッド呼び出し
		affi = initAffiSelect();
		// 役職セレクタの初期化メソッド呼び出し
		empTitle = initTitleSelect();
		// 所属部署セレクタ用のMapをModelに登録
		model.addAttribute("affi", affi);
		// 役職セレクタ用のMapをModelに登録
		model.addAttribute("empTitle", empTitle);

		return "employee/signup";

	}

	/**
	 * 社員情報登録画面のPOST用コントローラー
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return redirect:/signup
	 */
	@PostMapping
	public String postSignUp(@ModelAttribute EmployeeForm form,
			BindingResult bindingResult,
			Model model) {

		// formの中身をコンソールに出して確認する
		System.out.println(form);

		// insert用変数
		EmployeeDto empDto = new EmployeeDto();

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

		// 社員登録処理
		boolean result = empService.insert(empDto);

		// 社員登録結果判定
		if(result == true) {
			System.out.println("insert成功");
		} else {
			System.out.println("insert失敗");
		}

		// signup.htmlにリダイレクト
		return "redirect:/signup";
	}

//	@GetMapping("/signup/{id:.+}")
//	public String getEmpEdit(@ModelAttribute EmployeeForm form,
//			Model model,
//			@PathVariable("id") String empId) {
//
//		// 社員番号確認（デバッグ）
//		System.out.println("empId = " + empId);
//
//		// コンテンツ部分に社員情報を表示するために文字列を登録
//		model.addAttribute("contents", "employee/signup :: empEdit_contents");
//
//		// 所属部署用セレクタの初期化
//		affi = initAffiSelect();
//
//		// 役職用セレクタの初期化
//		empTitle = initTitleSelect();
//
//		// 社員番号のチェック
//		if(empId != null && empId.length() > 0) {
//
//			// 社員情報を取得
//			EmployeeDto empDto = empService.selectOne(empId);
//
//			// EmployeeDtoクラスをフォームクラスに変換
//			// 社員番号
//			form.setEmpId(empDto.getEmpId());
//			// 氏名
//			form.setEmpName(empDto.getEmpName());
//			// フリガナ
//			form.setEmpKana(empDto.getEmpKana());
//			// 所属部署
//			form.setAffi(empDto.getAffi());
//			// 役職
//			form.setEmpTitle(empDto.getEmpTitle());
//			// 連絡先
//			form.setContact(empDto.getContact());
//			// メールアドレス
//			form.setEmail(empDto.getEmail());
//			// 入社日
//			form.setDateEmp(empDto.getDateEmp());
//			// 内容変更理由
//			form.setReason(empDto.getReason());
//			// 変更日
//			form.setUpdateDt(empDto.getUpdateDt());
//			// 担当者名
//			form.setPic(empDto.getPic());
//
//			// Modelに登録
//			model.addAttribute("employeeForm", form);
//		}
//
//		return "employee/signup";
//	}
//
//	// 社員情報更新用処理
//	@PostMapping(value = "/signup", params = "update")
//	public String postEmpEditUpdate(@ModelAttribute EmployeeForm form,
//			Model model) {
//
//		System.out.println("保存ボタンの処理");
//
//		// EmployeeDtoインスタンスの生成
//		EmployeeDto empDto = new EmployeeDto();
//
//		// フォームクラスをEmployeeDtoクラスに変換
//		// 社員番号
//		empDto.setEmpId(form.getEmpId());
//		// 氏名
//		empDto.setEmpName(form.getEmpName());
//		// フリガナ
//		empDto.setEmpKana(form.getEmpKana());
//		// 所属部署
//		empDto.setAffi(form.getAffi());
//		// 役職
//		empDto.setEmpTitle(form.getEmpTitle());
//		// 連絡先
//		empDto.setContact(form.getContact());
//		// メールアドレス
//		empDto.setEmail(form.getEmail());
//		// 入社日
//		empDto.setDateEmp(form.getDateEmp());
//		// 内容変更理由
//		empDto.setReason(form.getReason());
//		// 変更日
//		empDto.setUpdateDt(form.getUpdateDt());
//		// 担当者名
//		empDto.setPic(form.getPic());
//
//		try {
//
//			// 更新（保存）実行
//			boolean result = empService.update(empDto);
//
//			if(result == true) {
//				model.addAttribute("result", "更新成功");
//			} else {
//				model.addAttribute("result", "更新失敗");
//			}
//		} catch(DataAccessException e) {
//
//			model.addAttribute("result","更新失敗(トランザクションテスト)");
//
//		}
//
//        // 社員情報登録一覧を表示
//        return getEmpDetail(model);
//
//	}

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
		model.addAttribute("message","SignupControllerでDataAccessExceptionが発生しました");

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
		model.addAttribute("message","SignupControllerでExceptionが発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";

	}

}