package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
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

import com.example.demo.domain.EmployeeForm;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

/**
 * 社員情報登録画面
 * @version 1.0
 * @author YUJIN LEE
 */
@Controller
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
	@GetMapping("/signup")
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

        // コンテンツ部分に社員情報一覧を表示するための文字列を登録
        model.addAttribute("contents", "employee/signup :: empList_contents");
        // 社員情報一覧の生成
        List<EmployeeDto> empList = empService.selectAll();
        // Modelに社員情報リストを登録
        model.addAttribute("empList", empList);
        // データ件数を取得
        int count = empService.count();
        model.addAttribute("empListCnt", count);

		return "employee/signup";

	}

	/**
	 * 社員情報登録画面のPOST用コントローラー
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return redirect:/signup
	 */
	@PostMapping("/signup")
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