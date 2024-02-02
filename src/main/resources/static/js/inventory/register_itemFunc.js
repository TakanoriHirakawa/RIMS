/**
 * register_itemFunc.js
 */

var registerItemFunc = {};

registerItemFunc.registItem = function(formData) {
	console.log('登録する処理の呼び出し')
	console.log(formData);

	$.ajax({
		url: '/inventory/registItem',
		type: 'post',
		data: { formData: formData },
		contentType: false,
		processData: false,
		success: function() {
			//成功時の処理
			alert('物品の登録が成功しました');
		},
		error: function() {
			// エラー時の処理
			alert('Error loading the next page.');
		}
	})

}

registerItemFunc.nextPage = function(ans) {
	$.ajax({
		url: '/inventory/nextPageAfterRegistItem',
		type: 'post',
		data: { ans: ans },
		success: function(data) {
			// 成功時の処理（例: ページ遷移）
			var form = $('<form method="post">' +
				'<input type="hidden" name="ans" >' +
				'</form>');
			form.attr('action', data);
			$('body').append(form);
			form.submit();
		},
		error: function() {
			// エラー時の処理
			alert('Error loading the next page.');
		}
	});
}