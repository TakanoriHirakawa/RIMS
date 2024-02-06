var usedItemsReportFunc = {};

/**
 * 引数（入力された図番）を元にdbからレコードを検索し、
 * 検索結果に応じた内容を表示する関数
 * @param currentRow：現在の入力行
 * @param currentItemNo：入力された図番
 * @returns 物品名列および数量列の表示
 * */
usedItemsReportFunc.findRecordByItemNo = function(currentRow,currentItemNo) {
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
				currentRow.find('td[id="itemName"]').text('未登録の部品です')
			} else {
				// レコードが発見できた場合の処理
				currentRow.find('td[id="itemName"]').text(data.itemName)
				currentRow.find('input[id="quantity"]').val(data.basicQuantity)
			}
		},
		error: function() {
			console.error('Failed to retrieve products.');
		}
	});
}

