/**
 * /Original_RIMS/src/main/resources/static/js/inventory/receivingItemsFunc.js
 */

var receivingItemsFunc = {};

/**
 * 図番欄が入力された際に次の２つの処理を行う関数
 * １：入力内容に応じた物品名を返す処理
 * ２：入力した行の次の行が非表示の場合に、非表示を解除する処理
 **/
receivingItemsFunc.handleItemNoInput = function(input) {
	var currentRow = input.closest('tr');
	var currentItemNo = input.val().trim();

	if (currentItemNo == '' || currentItemNo == null) {
		currentRow.find('td[id^="itemName_"]').text('図番未入力')
	} else {
		// 物品名と数量を表示する関数を呼び出す
		receivingItemsFunc.findRecordByItemNo(currentRow, currentItemNo);

		var nextRow = currentRow.next();
		// 次の行が非表示の場合、非表示を解除
		if (nextRow.hasClass('hidden-row')) {
			nextRow.removeClass('hidden-row');
		}
	}

}
/***
 * EnterKey押下による誤入庫防止
 */
receivingItemsFunc.handleKeyPress = function(input, event) {
	if (event.keyCode === 13) { // Enterキーが押された場合
		event.preventDefault(); // デフォルトの動作をキャンセル
		return false; // フォームの送信を防止
	}
}

/**
* 引数（入力された図番）を元にdbからレコードを検索し、
* 検索結果に応じた内容を表示する関数
* @param currentRow：現在の入力行
* @param currentItemNo：入力された図番
* @returns 物品名列および数量列の表示
* */
receivingItemsFunc.findRecordByItemNo = function(currentRow, currentItemNo) {
	console.log('findRecordByItemNo is called')

	$.ajax({
		type: 'GET',
		url: '/repair_report/findRecordByItemNo',
		data: {
			currentItemNo: currentItemNo
		},
		success: function(data) {
			if (!data || Object.keys(data).length === 0) {
				// レコードが発見できなかった場合の処理
				console.log('Record not found');
				currentRow.find('td[id^="itemName_"]').text('未登録の部品です');
			} else {
				// レコードが発見できた場合の処理
				currentRow.find('td[id^="itemName_"]').text(data.itemName);

			}
		},
		error: function() {
			console.error('Failed to retrieve products.');
		}
	});
}