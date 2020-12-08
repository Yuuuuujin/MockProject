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
	requireEmpId(inputId, illegalCharArray, necessaryCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// 必須項目の作成
		this.createRequried(input);

		// すべてを簡単にチェックできるように、この入力を入力ログに追加
		this.inputLog.push(["requireEmpId", inputId, illegalCharArray, necessaryCharArray]);

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
			invalidString += this.necessaryCharCheck(input, necessaryCharArray);
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
	requireEmpName(inputId, illegalCharArray, necessaryCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// 必須項目の作成
		this.createRequried(input);

		// すべてを簡単にチェックできるように、この入力を入力ログに追加
		this.inputLog.push(["requireEmpName", inputId, illegalCharArray, necessaryCharArray]);

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
			invalidString += this.necessaryCharCheck(input, necessaryCharArray);
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
	requireEmpKana(inputId, illegalCharArray, necessaryCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// Create requried
		this.createRequried(input);

		// Add this input to the input log, for easy check alls
		this.inputLog.push(["requireEmpKana", inputId, illegalCharArray, necessaryCharArray]);

		// Check string for issues while editing
		$(input).on('input focus', input, () =>
		{
			// Append any invalid issues to string when editing
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// Enable submit again on an input change
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// Check string for issues after editing
		$(input).on('focusout', input, () =>
		{
			invalidString += this.necessaryCharCheck(input, necessaryCharArray);
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
	requireContact(inputId, illegalCharArray, necessaryCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// Create requried
		this.createRequried(input);

		// Add this input to the input log, for easy check alls
		this.inputLog.push(["requireContact", inputId, illegalCharArray, necessaryCharArray]);

		// Check string for issues while editing
		$(input).on('input focus', input, () =>
		{
			// Append any invalid issues to string when editing
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// Enable submit again on an input change
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// Check string for issues after editing
		$(input).on('focusout', input, () =>
		{
			invalidString += this.necessaryCharCheck(input, necessaryCharArray);
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
	requireEmail(inputId, illegalCharArray, necessaryCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// Create requried *
		this.createRequried(input);

		// Add this input to the input log, for easy check alls
		this.inputLog.push(["requireEmail", inputId, illegalCharArray, necessaryCharArray]);

		// Check string for issues while editing
		$(input).on('input focus', input, () =>
		{
			// Append any invalid issues to string when editing
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// Enable submit again on an input change
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// Check string for issues after editing
		$(input).on('focusout', input, () =>
		{
			invalidString += this.necessaryCharCheck(input, necessaryCharArray);
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
	requireDateEmp(inputId, illegalCharArray, necessaryCharArray)
	{
		let input = $("#" + inputId);
		let invalidString = "";

		// Create requried *
		this.createRequried(input);

		// Add this input to the input log, for easy check alls
		this.inputLog.push(["requireDateEmp", inputId, illegalCharArray, necessaryCharArray]);

		// Check string for issues while editing
		$(input).on('input focus', input, () =>
		{
			// Append any invalid issues to string when editing
			invalidString = "";
			invalidString += this.illegalCharCheck(input, illegalCharArray);
			this.showWarning(input, inputId, invalidString);
		});

		// Enable submit again on an input change
		$(input).on('input', input, () =>
		{
			this.submitDisabled(false, this.submitButtonText);
		});

		// Check string for issues after editing
		$(input).on('focusout', input, () =>
		{
			invalidString += this.necessaryCharCheck(input, necessaryCharArray);
			invalidString += this.dateEmpCheck(input);
			this.showWarning(input, inputId, invalidString);
			// 検証結果を保持する
			this.valid(input);
		});

		return invalidString;
	}

	////////////
	// Checks //
	////////////
	/*
        Checks return nothing if there are no issues.
        If there is a validation issue, a string is returned that explains the issue.
        The main functions call these depending on their parameters.

        Returns: String containing the error or blank
	 */

	// Checks if contains unwanted text
	illegalCharCheck(input, illegalCharArray)
	{
		// Reset loop stringe
		let illegalsUsed = "";
		// loop through each illegal item to check for
		$(illegalCharArray).each(function()
				{
			if (input.val().indexOf(this) >= 0)
			{
				// Append illegal strings to var
				// check if char is a space
				if (!this.trim().length == 0)
				{
					illegalsUsed += " " + this;
				}
				else
				{
					illegalsUsed += " spaces"
				}
			}
				});

		// Create output based on result of illegals concatination
		if (illegalsUsed === "")
		{
			return "";
		}
		else
		{
			return "Cannot use:" + illegalsUsed + ". ";
		}
	}

	// Check if doesnt contain needed text
	necessaryCharCheck(input, necessaryCharArray)
	{
		let notUsed = "";
		// loop through each illegal item to check for
		$(necessaryCharArray).each(function()
				{
			if (!(input.val().indexOf(this) >= 0))
			{
				notUsed += " " + this;
			}
				});

		// Create output based on result of illegals concatination
		if (notUsed === "")
		{
			return "";
		}
		else
		{
			return "Must contain:" + notUsed + ". ";
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
			return"半角数字の6桁で入力してください";
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
			return"全角文字の2桁以上、16桁以内で入力してください";
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
			return"全角カナの4桁以上、24桁以内で入力してください";
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
			return "半角数字記号の10桁以上、12桁以内の'-'（ハイフン）付きの電話番号形式で入力してください";
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
			return "メールアドレス形式で入力してください （例：sample123@test.co.jp）";
		}
	}
	// 入社日：日付形式をチェック
	dateEmpCheck(input)
	{
		if (input.val().match(/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/))
		{
			return "";
		}
		else
		{
			return "半角数字の日付形式(yyyy/mm/dd)で入力してください";
		}
	}


	////////////////
	// Check Alls //
	////////////////
	/*
        These functions deal with form submission, checking all inputs before allowing submission
	 */


	// Enable/Disable the submit button, and change its value
	submitDisabled(trueFalse, value)
	{
		$(this.submitButton).prop('disabled', trueFalse);
		$(this.submitButton).val(value);
	}



	checkAll()
	{
		$(this.form).submit( (e) =>
		{
			// Loop through every input added to object
			$(this.inputLog).each( (i) =>
			{
				// Reset loop variables
				let invalidString = "";
				let invalidCheckString = "";
				let thisLog = this.inputLog[i];

				// Make block scope elements to help understand which elements in the array are which
				let inputId = thisLog[1];
				let input = $("#" + inputId);
				let illegalCharArray = thisLog[4];
				let necessaryCharArray = thisLog[5];

				// Check for issues
				invalidString = "";
				invalidString += this.illegalCharCheck(input, illegalCharArray);
				invalidString += this.necessaryCharCheck(input, necessaryCharArray);
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

				// Display issues
				if (invalidString)
				{
					this.showWarning(input, inputId, invalidString);
					this.submitDisabled(true, "Error, please check your form");
					// Stop submission
					e.preventDefault();
				}
				if (invalidCheckString)
				{
					this.showWarning(passConfirm, passConfirmId, invalidCheckString);
					this.submitDisabled(true, "Error, please check your form");
					// Stop submission
					e.preventDefault();
				}


			});

		});

	}



	////////////////////
	// Class Updating //
	////////////////////
	/*
        Simplifies redundant class code into a few functions
	 */

	// Perform restrictions depending on the input
	showWarning(input, inputId, invalidString)
	{
		// Provide proper styling and feedback
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

	// Adds a valid class to element and removes invalid
	makeValid(element)
	{
		if (!element.hasClass(this.validC))
		{
			element.addClass(this.validC);
		}
		// Remove invalid class if it exists
		if (element.hasClass(this.invalidC))
		{
			element.removeClass(this.invalidC);
		}
	}

	// Adds a valid class to element and removes invalid
	removeValid(element)
	{
		// Remove invalid class if it exists
		if (element.hasClass(this.validC))
		{
			element.removeClass(this.validC);
		}
	}

	// adds invalid class to element and removes valid class
	makeInvalid(element)
	{
		if (!element.hasClass(this.invalidC))
		{
			element.addClass(this.invalidC);
		}
		// Remove valid class
		if (element.hasClass(this.validC))
		{
			element.removeClass(this.validC);
		}
	}


	/////////////////////
	// Text Generation //
	/////////////////////
	/*
        Visual output such as errors or *
	 */

	// Create required after text
	createRequried(input)
	{
		$("<span class='text-danger'></span>").insertBefore(input)
	}

	// Creates responsive tiny text below inputs to inform user of issues
	generateFeedback(input, inputId, validityClass, prompt)
	{
		// Delete feedback if it already exists to make room for new content
		$('#' + inputId + '-feedback').remove();

		// Create feedback element if it does not exist
		$('<div id="' + inputId + '-feedback" class="' + validityClass + '">' + prompt + '</div>').insertAfter(input);
	}



}

