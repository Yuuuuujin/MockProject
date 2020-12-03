package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

/**
 * 社員情報一覧 Controller
 * @version 1.0
 * @author YUJIN LEE
 */
@Controller
public class ListController {

	@Autowired
	private EmployeeService empService;

    /**
     * 社員情報一覧画面のGET用メソッド
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
}
