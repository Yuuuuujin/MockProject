/**
 * 社員情報変更モーダル
 */
$('#editModal').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget) // モーダルをトリガーするボタン
	var recipient = button.data('whatever') // data属性から情報を取得する
	// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	var modal = $(this)
	modal.find('.modal-title').text('社員情報変更 ' + recipient)
	modal.find('.modal-body input').val(recipient)
})