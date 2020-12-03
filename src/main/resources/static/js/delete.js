/**
 * 削除ボタン押下時
 */
$(function() {
  $('.delete').on('click', function() {
    var deleteConfirm = confirm('社員情報を削除しても宜しいでしょうか？');


    if(deleteConfirm == true) {
      var clickDel = $(this)
      // 削除ボタンに社員番号をデータとして埋め込む
      var empId = clickDel.attr('name');

      var param = {
    	empId : empId
      };

      $.ajax({
        url: '/list/empList',
        type: 'POST',
        contentType: 'application/json',
        dataType:'JSON',
        data: JSON.stringify(param)
      })

     .done(function(data) {
        // 通信が成功した場合、クリックした要素の親要素の <tr> を削除
    	 location.reload('/list/empList');
      })

     .fail(function(data) {
        alert('エラー');
      });

    } else {
      (function(e) {
        e.preventDefault()
      });
    };
  });
});