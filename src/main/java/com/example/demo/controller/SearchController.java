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
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.SearchForm;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

/**
 * 社員情報検索 Controller
 * @version 1.0
 * @author YUJIN LEE
 */
@Controller
@RequestMapping("/search")
public class SearchController {

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
	 * 社員情報検索画面のGET用コントローラー
	 * @param form
	 * @param model
	 * @return employee/search
	 */
	@GetMapping
	public String getSearch(SearchForm form,
			Model model) {

		// 社員情報検索画面
		model.addAttribute("title", "社員情報検索画面");

		// 所属部署セレクタの初期化メソッド呼び出し
		affi = initAffiSelect();
		// 役職セレクタの初期化メソッド呼び出し
		empTitle = initTitleSelect();
		// 所属部署セレクタ用のMapをModelに登録
		model.addAttribute("affi", affi);
		// 役職セレクタ用のMapをModelに登録
		model.addAttribute("empTitle", empTitle);

		return "employee/search";

	}

	@PostMapping
	public String postSearch(@ModelAttribute SearchForm form,
			BindingResult result,
			Model model) {

		// 社員番号
		String empId = form.getEmpId();
		// 氏名
		String empName = form.getEmpName();
		// フリガナ
		String empKana = form.getEmpKana();
		// 所属部署
		String affi = form.getAffi();
		// 役職
		String empTitle = form.getEmpTitle();
		// 連絡先
		String contact = form.getContact();
		// メールアドレス
		String email = form.getEmail();
		// 入社日
		String dateEmp = form.getDateEmp();


		if(empId == "" && empName == "" &&
				empKana == "" && affi == "" &&
				empTitle == "" && contact == "" &&
				email == "" && dateEmp == "") {

			// エラーメッセージを表示
			model.addAttribute("error", "検索条件を入力してください");

			return "employee/search";

		} else if (result.hasErrors()) {

			model.addAttribute("empId", form.getEmpId());
			model.addAttribute("empName", form.getEmpName());
			model.addAttribute("empKana",form.getEmpKana());
			model.addAttribute("affi", form.getAffi());
			model.addAttribute("empTitle", form.getEmpTitle());
			model.addAttribute("contact", form.getContact());
			model.addAttribute("email", form.getEmail());
			model.addAttribute("dateEmp", form.getDateEmp());

			return "employee/search";

		} else {

			// 社員情報検索画面
			model.addAttribute("title", "社員情報検索画面");

	    	List<EmployeeDto> searchList = empService.search(form);

	        // 検索結果Listをmodelに渡す
	    	model.addAttribute("searchList", searchList);
	    	//データ件数を取得
	    	int count = empService.count();
	    	model.addAttribute("count", count);

	        return "employee/search";

		}

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
		model.addAttribute("message","SearchControllerでDataAccessExceptionが発生しました");

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
		model.addAttribute("message","SearchControllerでExceptionが発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";

	}


}
