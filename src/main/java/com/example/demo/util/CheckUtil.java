package com.example.demo.util;

import java.util.Date;

/**
 * Check Util
 * @version 1.0
 * @author YUJIN LEE
 */
public class CheckUtil {

	/**
	 * エラーメッセージ表示
	 */
	// 社員番号
	public static String empIdMsg = "6桁の半角数字で入力してください";
	// 氏名
	public static String empNameMsg = "2桁以上、16桁以内の全角文字（姓+全角スペース+名前）で入力してください";
	// フリガナ
	public static String empKanaMsg = "4桁以上、24桁以内の全角カナ（姓+全角スペース+名前）で入力してください";
	// 所属部署
	public static String affiMsg = "所属部署を選択してください";
	// 役職
	public static String empTitleMsg = "役職を選択してください";
	// 連絡先
	public static String contactMsg = "半角数字記号の10桁以上、13桁以内の'-'（ハイフン）付きの電話番号形式で入力してください";
	// メールアドレス
	public static String emailMsg = "12桁以上、100桁以内のメールアドレス形式で入力してください （例：sample123@test.co.jp）";
	// 入社日
	public static String dateEmpMsg = "半角数字の日付形式(yyyy/mm/dd)で入力してください";
	// 内容変更理由
	public static String reasonMsg = "4桁以上、28桁以内の全角文字で入力してください";
	// 変更日
	public static String updateDtMsg = "半角数字の日付形式(yyyy/mm/dd)で入力してください";
	// 担当者名
	public static String picMsg = "2桁以上、16桁以内の全角文字（姓+全角スペース+名前）で入力してください";

	/**
	 * 型・桁数チェック
	 */
	// 社員番号(半角数字)
	public static String regex_empId = "^\\d{6}$";
	// 入社日(yyyy/MM/dd)
	public static Date regex_dateEmp;
	// 氏名(全角文字)
	public static String regex_empName = "^[^-~｡-ﾟ] {2,16}*$";
	// フリガナ(全角カナ)
	public static String regex_empKana = "^[ァ-ヶー]{4,24}*$";
	// 連絡先(半角数字記号)
	public static String regex_contact = "\\\\d{2,4}-\\\\d{2,4}-\\\\d{4}";
	// メールアドレス(メールアドレス)
	public static String regex_email = "([a-zA-Z0-9][a-zA-Z0-9_.+\\\\-]*)@(([a-zA-Z0-9][a-zA-Z0-9_\\\\-]+\\\\.)+[a-zA-Z]{2,6})";
	// 内容変更理由(全角文字)
	public static String regex_reason = "^[^-~｡-ﾟ] {4,28}*$";
	// 担当者名(全角文字)
	public static String regex_pic = "^[^-~｡-ﾟ] {2,16}*$";

}
