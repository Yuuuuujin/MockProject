package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.EmployeeForm;
import com.example.demo.domain.valid.GroupOrder;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

/**
 * 社員情報変更画面
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
	 * 社員情報一覧画面のGET用コントローラー
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
     * 社員情報変更画面のGETメソッド用処理.
     */
	@GetMapping("/edit/{empId}")
	public String getEmpEdit(@PathVariable("empId") String empId,
			Model model) {

        // 社員情報を取得
        EmployeeDto empDto = empService.selectOne(empId);
        // Modelに登録
        model.addAttribute("employeeForm", empDto);

        // 社員番号確認（デバッグ）
        System.out.println("empId = " + empId);

		// 社員情報変更画面
		model.addAttribute("title", "社員情報変更画面");

        // コンテンツ部分に社員情報を表示するための文字列を登録
        model.addAttribute("contents", "employee/edit :: empEdit_contents");

        affi = initAffiSelect();
        empTitle = initTitleSelect();

        model.addAttribute("affi", affi);
        model.addAttribute("empTitle", empTitle);

        // 社員番号のチェック
        if (empId != null && empId.length() > 0) {
        	EmployeeForm form = new EmployeeForm();

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
	 * 社員情報更新用処理.
	 */
	@PostMapping(value = "/edit", params = "update")
	public String update(@ModelAttribute @Validated(GroupOrder.class) EmployeeForm form,
			BindingResult result,
			Model model) {

		System.out.println("更新ボタンの処理");

		if(result.hasErrors()) {
			model.addAttribute("empId", form.getEmpId());
			model.addAttribute("empName", form.getEmpName());
			model.addAttribute("empKana",form.getEmpKana());
			model.addAttribute("affi", form.getAffi());
			model.addAttribute("empTitle", form.getEmpTitle());
			model.addAttribute("contact", form.getContact());
			model.addAttribute("email", form.getEmail());
			model.addAttribute("dateEmp", form.getDateEmp());

			return "employee/edit";
		}

		//Userインスタンスの生成
		EmployeeDto empDto = new EmployeeDto();

		//フォームクラスをUserクラスに変換
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
			}

		} catch(DataAccessException e) {

			model.addAttribute("updateResult", "更新失敗(トランザクションテスト)");

		}

		// 社員情報一覧画面を表示
		return "redirect:/list";
	}

}