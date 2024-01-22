var usedItemsReportFunc = {};

/**
 * 図番入力欄からフォーカスが外れた時に呼び出す関数
 */
usedItemsReportFunc.onItemNoBlur = function(input) {
	//メイン処理を呼び出し
	usedItemsReportFunc.findRecordByItemName(input);
	var table = document.getElementById('usedItemsTable');

	//最終行のindexを取得
	var lastRowIndex = usedItemsReportFunc.getLastRowIndex(table);
	//最終行の図番欄の値を取得
	var lastRowItemNo = usedItemsReportFunc.getLastRowItemNo(lastRowIndex);
	//最終行の図番欄がnullでない場合は行を追加
	if (lastRowItemNo !== '') {
		usedItemsReportFunc.insertTable(table, lastRowIndex);
	}
	//最終行の図番欄がnullの場合は何もせず終了
};

/**
 * 引数（入力された図番）を元にdbからレコードを検索し、
 * 検索結果に応じた内容を表示する関数
 * @param input：入力された図番
 * */
usedItemsReportFunc.findRecordByItemName = function(input) {
	var inputItemNo = input.value;

	// 入力行の index を取得
	var rowIndex = usedItemsReportFunc.getRowIndexFromInput(input);

	// 各行の要素に対応する ID を構築
	var itemNameId = 'itemName-' + rowIndex;
	var basicQuantityId = 'basicQuantity-' + rowIndex;

	// 対応する要素を取得
	var dispItemName = document.getElementById(itemNameId);
	var dispBasicQuantity = document.getElementById(basicQuantityId);

	if (!inputItemNo) {
		//入力されたinputタグの入力内容がnull or 空の場合は、物品名と数量をクリア
		dispItemName.innerText = '';
		dispBasicQuantity.value = '';
	} else {
		//dbからinputItemNoに応じた内容をフェッチ
		usedItemsReportFunc.fetchItemDetails(inputItemNo, dispItemName, dispBasicQuantity);
	}
};

/**
 * 入力を受け付けた行のindexを取得する関数。
 * @param input：入力を受け付けたinput タグ
 * @returns テーブルのindex番号
 */
usedItemsReportFunc.getRowIndexFromInput = function(input) {
	var inputId = input.id;
	var index = inputId.split('-')[1];
	return index;
}
/**
 * dbから図番（inputItemNo）に応じた内容を取得
 * @param  inputItemNo：入力を受け付けた図番欄の値
 * @param  dispItemName：入力を受け付けた図番欄と同行の物品名欄
 * @param  dispBasicQuantity：入力を受け付けた図番欄と同行の数量欄   
*/
usedItemsReportFunc.fetchItemDetails = function(inputItemNo, dispItemName, dispBasicQuantity) {
	$.ajax({
		type: 'GET',
		url: '/repair_report/getInventoryDetails',
		data: { inputItemNo: inputItemNo },
		success: function(data) {
			if (!data) {
				//レコードが取得できなかった場合
				dispItemName.innerText = '未登録の部品';
				dispBasicQuantity.value = 0;
			} else {
				//レコードが取得できた場合
				dispItemName.innerText = data.itemName;
				dispBasicQuantity.value = data.basicQuantity;
			}
		},
		error: function() {
			console.error('Failed to fetch item details.');
		}
	});
}

/**
 * テーブルの最終行のindexを取得
 * @returns 最終行のindex番号
 */
usedItemsReportFunc.getLastRowIndex = function(table) {
	var lastRow = table.rows[table.rows.length - 1];
	var indexCell = lastRow.cells[0];
	var index = parseInt(indexCell.innerText);
	return index;
}

/**
 * 最終行の図番欄の値を取得する処理
 * @param  lastRowIndex ：最終行のindex番号
 * @returns 最終行の図番欄の値
*/
usedItemsReportFunc.getLastRowItemNo = function(lastRowIndex) {
	var lastRow = document.getElementById('usedItemsTable').rows[lastRowIndex];
	return lastRow.cells[1].getElementsByTagName('input')[0].value;
}

/**
 * 最終行の後ろに新たな行を追加し最終行の図番入力欄をフォーカスする関数
 * @param table：対象テーブル
 * @param lastRowIndex：最終行のindex
*/
usedItemsReportFunc.insertTable = function(table, lastRowIndex) {
	var newRow = table.insertRow(-1);
	var newRowIndex = lastRowIndex + 1;

	//SEQ
	var seqCell = newRow.insertCell(0);
	seqCell.id = 'index-' + newRowIndex;
	seqCell.innerText = newRowIndex;

	//図番入力欄
	var itemNoInput = document.createElement('input');
	itemNoInput.type = 'text';
	itemNoInput.id = 'itemNo-' + newRowIndex;
	itemNoInput.setAttribute('onblur', 'usedItemsReportFunc.onItemNoBlur(this)');
	newRow.insertCell(1).appendChild(itemNoInput);

	//物品名
	var itemNameCell = newRow.insertCell(2);
	itemNameCell.id = 'itemName-' + newRowIndex;
	itemNameCell.setAttribute('aria-readonly', 'true');

	//数量入力欄
	var quantityInput = document.createElement('input');
	quantityInput.type = 'number';
	quantityInput.min = '0';
	quantityInput.id = 'basicQuantity-' + newRowIndex;
	newRow.insertCell(3).appendChild(quantityInput);

	newRow.cells[1].getElementsByTagName('input')[0].focus();
};

/**
 *取得情報を次画面へ送信する関数
*/
usedItemsReportFunc.submitCheckReport = function() {
	console.log("submitCheckReport function is called");
	var tempReportsDiv = document.getElementById('tempReports')
	console.log('tempReports' + tempReportsDiv);

	console.log('tempReportData1：' + tempReportsDiv.getAttribute('tempReports'));
//	console.log('tempReportData2：' + tempReportsDiv.getAttribute('tempUsedItemsList'));

	usedItemsReportFunc.ajax(tempReportsDiv.getAttribute('tempReports'))
}

usedItemsReportFunc.ajax = function(elements) {
	console.log("usedItemsReportFunc.ajax is called");
	$.ajax({
		type: 'GET',
		url: '/repair_report/test',
		data: { tempReports: elements },
		success: function(data) {
			console.log('success:ajax')
		},
		error: function() {
			console.error('Failed to fetch item details.');
		}
	});
}
