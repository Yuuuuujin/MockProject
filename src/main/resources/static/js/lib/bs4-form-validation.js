/**
 * 入力フォームチェック
 * */
class Validation
{

	constructor(formId)
	{
		// フォームグローバル
		this.form = $("#" + formId);
		this.submitButton = $(this.form).find('input[type="submit"]');
		this.submitButtonText = this.submitButton.val();

		// グローバルinput配列
		this.inputLog = [];

		// 基本css クラス
		this.validC = "is-valid";
		this.invalidC = "is-invalid";

		// チェックに引っかかラなかったら、submitする
		this.checkAll();
	}

	/////////////
	//主な機能//
	////////////
	/*
        このカテゴリの関数は、フォーム検証の応答レイヤを追加するために呼び出される
	 */

	/*
        社員番号
	 */
	requireEmpId(inputId, illegalCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// すべてを簡単にチェックできるように、この入力を入力ログに追加
		this.inputLog.push(["requireEmpId", inputId, illegalCharArray]);

		// 編集中に文字列に問題がないかどうかを確認
		$(input).on('input focus', input, () =>
		{
			// 編集時に無効な問題を文字列に追加します
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// 入力変更時に送信を再度有効にする
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// 編集後に文字列に問題がないか確認
		$(input).on('focusout', input, () =>
		{
			invalidString += this.empIdCheck(input);
			this.showWarning(input, inputId, invalidString);
			// 検証結果を保持する
			this.valid(input);
		});

		return invalidString;
	}

	/*
    	氏名
	 */
	requireEmpName(inputId, illegalCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// すべてを簡単にチェックできるように、この入力を入力ログに追加
		this.inputLog.push(["requireEmpName", inputId, illegalCharArray]);

		// 編集中に文字列に問題がないかどうかを確認
		$(input).on('input focus', input, () =>
		{
			// 編集時に無効な問題を文字列に追加します
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// 入力変更時に送信を再度有効にする
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// 編集後に文字列に問題がないか確認
		$(input).on('focusout', input, () =>
		{
			invalidString += this.empNameCheck(input);
			this.showWarning(input, inputId, invalidString);
			// 検証結果を保持する
			this.valid(input);
		});

		return invalidString;
	}

	/*
		フリガナ
	 */
	requireEmpKana(inputId, illegalCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// すべてを簡単にチェックできるように、この入力を入力ログに追加
		this.inputLog.push(["requireEmpKana", inputId, illegalCharArray]);

		// 編集中に文字列に問題がないかどうかを確認
		$(input).on('input focus', input, () =>
		{
			// 編集時に無効な問題を文字列に追加します
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// 入力変更時に送信を再度有効にする
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// 編集後に文字列に問題がないか確認
		$(input).on('focusout', input, () =>
		{
			invalidString += this.empKanaCheck(input);
			this.showWarning(input, inputId, invalidString);
			// 検証結果を保持する
			this.valid(input);
		});

		return invalidString;
	}

	/*
		連絡先
	 */
	requireContact(inputId, illegalCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// すべてを簡単にチェックできるように、この入力を入力ログに追加
		this.inputLog.push(["requireContact", inputId, illegalCharArray]);

		// 編集中に文字列に問題がないかどうかを確認
		$(input).on('input focus', input, () =>
		{
			// 編集時に無効な問題を文字列に追加します
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// 入力変更時に送信を再度有効にする
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// 編集後に文字列に問題がないか確認
		$(input).on('focusout', input, () =>
		{
			invalidString += this.contactCheck(input);
			this.showWarning(input, inputId, invalidString);
			// 検証結果を保持する
			this.valid(input);
		});

		return invalidString;
	}

	/*
        メールアドレス
	 */
	requireEmail(inputId, illegalCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// すべてを簡単にチェックできるように、この入力を入力ログに追加
		this.inputLog.push(["requireEmail", inputId, illegalCharArray]);

		// 編集中に文字列に問題がないかどうかを確認
		$(input).on('input focus', input, () =>
		{
			// 編集時に無効な問題を文字列に追加します
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// 入力変更時に送信を再度有効にする
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// 編集後に文字列に問題がないか確認
		$(input).on('focusout', input, () =>
		{
			invalidString += this.emailCheck(input);
			this.showWarning(input, inputId, invalidString);
			// 検証結果を保持する
			this.valid(input);
		});

		return invalidString;
	}

	/*
    	入社日
	 */
	requireDateEmp(inputId, illegalCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// すべてを簡単にチェックできるように、この入力を入力ログに追加
		this.inputLog.push(["requireDateEmp", inputId, illegalCharArray]);

		// C編集中に文字列に問題がないかどうかを確認
		$(input).on('input focus', input, () =>
		{
			// 編集時に無効な問題を文字列に追加します
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// 入力変更時に送信を再度有効にする
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// 編集後に文字列に問題がないか確認
		$(input).on('focusout', input, () =>
		{
			invalidString += this.dateEmpCheck(input);
			this.showWarning(input, inputId, invalidString);
			// 検証結果を保持する
			this.valid(input);
		});

		return invalidString;
	}

	////////////
	//チェック//
	////////////
	/*
		問題がない場合、チェックでは何も返さない。
		検証の問題がある場合は、問題を説明する文字列が返す。
		主な関数は、パラメータに応じてこれらを呼び出す。
        Returns: エラーまたは空白を含む文字列
	 */

	// 不要なテキストが含まれているかどうかを確認
	illegalCharCheck(input, illegalCharArray)
	{
		let illegalsUsed = "";
		// 不正な項目を一つ一つ目を通してチェックする
		$(illegalCharArray).each(function()
				{
			if (input.val().indexOf(this) >= 0)
			{
				// varに不正な文字列を追加する
				// char がスペースであるかどうかを確認
				if (!this.trim().length == 0)
				{
					illegalsUsed += " " + this;
				}
				else
				{
					illegalsUsed += "スペース"
				}
			}
				});

		// 不正連結の結果に基づく出力の作成
		if (illegalsUsed === "")
		{
			return "";
		}
		else
		{
			return illegalsUsed + "は入力できません。";
		}
	}

	// 社員番号：半角数字をチェック
	empIdCheck(input)
	{
		if(input.val().match(/^[0-9]{6}$/))
		{
			return "";
		}
		else
		{
			return"半角数字の6桁で入力してください。";
		}
	}
	// 氏名：全角文字をチェック
	empNameCheck(input)
	{
		if(input.val().match(/^[^\x20-\x7e]{2,16}$/))
		{
			return "";
		}
		else
		{
			return"全角文字の2桁以上、16桁以下で入力してください。";
		}
	}
	// フリガナ：全角カナをチェック
	empKanaCheck(input)
	{
		if(input.val().match(/^[ァ-ンヴー]{4,24}$/))
		{
			return "";
		}
		else
		{
			return"全角カナの4桁以上、24桁以下で入力してください。";
		}
	}
	// 連絡先：半角数字記号をチェック
	contactCheck(input)
	{
		if (input.val().match(/^[0-9]{2,4}-[0-9]{4}-[0-9]{4}$/))
		{
			return "";
		}
		else
		{
			return "半角数字記号の10桁以上、13桁以下の'-'（ハイフン）付きの電話番号形式で入力してください。";
		}
	}
	// メールアドレス形式をチェック
	emailCheck(input)
	{
		if (input.val().match(/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/))
		{
			return "";
		}
		else
		{
			return "半角英数字記号のメールアドレス形式で入力してください。 （例：sample123@test.co.jp）";
		}
	}
	// 入社日：日付形式をチェック
	dateEmpCheck(input)
	{
		if (input.val().match(/^\d{4}[\/](0?[1-9]|1[012])[\/](0?[1-9]|[12][0-9]|3[01])$/))
		{
			return "";
		}
		else
		{
			return "半角数字の日付形式(yyyy/mm/dd)で入力してください。";
		}
	}


	////////////////////
	// 全てをチェック //
	////////////////////
	/*
        これらの関数は、フォームの送信を処理し、送信を許可する前にすべての入力をチェックする。
	 */


	// [送信] ボタンを有効または無効にし、その値を変更する。
	submitDisabled(trueFalse, value)
	{
		$(this.submitButton).prop('disabled', trueFalse);
		$(this.submitButton).val(value);
	}



	checkAll()
	{
		$(this.form).submit( (e) =>
		{
			// オブジェクトに追加されたすべての入力をループする。
			$(this.inputLog).each( (i) =>
			{
				// ループ変数のリセット
				let invalidString = "";
				let invalidCheckString = "";
				let thisLog = this.inputLog[i];

				// ブロックスコープ要素を作成して、Array内のどの要素がどの要素であるかを把握しやすくする。
				let inputId = thisLog[1];
				let input = $("#" + inputId);
				let illegalCharArray = thisLog[4];

				// 問題を確認
				invalidString = "";
				invalidString += this.illegalCharCheck(input, illegalCharArray);
				if (thisLog[0] === "requireEmpId")
				{
					invalidString += this.empIdCheck(input);
				}
				if (thisLog[0] === "requireEmpName")
				{
					invalidString += this.empNameCheck(input);
				}
				if (thisLog[0] === "requireEmpKana")
				{
					invalidString += this.empKanaCheck(input);
				}
				if (thisLog[0] === "requireContact")
				{
					invalidString += this.contact(input);
				}
				if (thisLog[0] === "requireEmail")
				{
					invalidString += this.emailCheck(input);
				}
				if (thisLog[0] === "requireDateEmp")
				{
					invalidString += this.dateEmpCheck(input);
				}

				// 画面に問題を表示
				if (invalidString)
				{
					this.showWarning(input, inputId, invalidString);
					this.submitDisabled(true, "入力内容を確認してください。");
					// Stop submission
					e.preventDefault();
				}
				if (invalidCheckString)
				{
					this.showWarning(passConfirm, passConfirmId, invalidCheckString);
					this.submitDisabled(true, "入力内容を確認してください。");
					// Stop submission
					e.preventDefault();
				}


			});

		});

	}



	////////////////////
	// クラスを更新 ////
	////////////////////
	/*
        Simplifies redundant class code into a few functions
	 */

	// 入力に応じた制限の実行
	showWarning(input, inputId, invalidString)
	{
		// 適切なスタイルとフィードバックを提供する。
		if (invalidString)
		{
			this.generateFeedback(input, inputId, "invalid-feedback", invalidString);
			this.makeInvalid(input);
		}
		else
		{
			this.generateFeedback(input, inputId, "", "");
			this.makeValid(input);
		}
	}

	// 有効なクラスを要素に追加し、無効なクラスを削除する。
	makeValid(element)
	{
		if (!element.hasClass(this.validC))
		{
			element.addClass(this.validC);
		}
		// 無効なクラスが存在する場合は削除する。
		if (element.hasClass(this.invalidC))
		{
			element.removeClass(this.invalidC);
		}
	}

	// 有効なクラスを要素に追加し、無効なクラスを削除する。
	removeValid(element)
	{
		// 無効なクラスが存在する場合は削除する。
		if (element.hasClass(this.validC))
		{
			element.removeClass(this.validC);
		}
	}

	// 要素に無効なクラスを追加し、有効なクラスを削除する。
	makeInvalid(element)
	{
		if (!element.hasClass(this.invalidC))
		{
			element.addClass(this.invalidC);
		}
		// 有効なクラスの削除
		if (element.hasClass(this.validC))
		{
			element.removeClass(this.validC);
		}
	}


	/////////////////////
	// テキストの生成 ///
	/////////////////////
	/*
        エラーを出力する。
	 */
	// 入力した内容の下にテキストを作成する。
	generateFeedback(input, inputId, validityClass, prompt)
	{
		// 新しいコンテンツを保存するためにフィードバックがすでに存在する場合は削除する。
		$('#' + inputId + '-feedback').remove();

		// 存在しない場合はフィードバック要素を作成する。
		$('<div id="' + inputId + '-feedback" class="' + validityClass + '">' + prompt + '</div>').insertAfter(input);
	}



}

