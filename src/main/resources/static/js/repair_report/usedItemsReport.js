
/**
 * /repair_report/usedItemsReport.htmlに適用するjs
 * /Original_RIMS/src/main/resources/static/js/repair_report/usedItemsReport.js
 */

/**
 * ページ読込の際に、テーブルの2行目以降を非表示にする処理
 */
document.addEventListener('DOMContentLoaded', function() {
	var rowsToHide = document.querySelectorAll('tbody tr:not(:first-child)');
	rowsToHide.forEach(function(row) {
		row.setAttribute('hidden', 'true');
	})
})

/**
 * 図番が入力された際に、次の行の図番欄がnullでなければ非表示を解除する処理
*/
$(document).ready(function() {
	$('input[id="itemNo"]').on('blur', function() {
		var currentRow = $(this).closest('tr');
		var currentItemNo = currentRow.find('input[id="itemNo"]').val();

		if (currentItemNo !== undefined && currentItemNo !== null && currentItemNo.trim() !== '') {
			//図番欄の入力値がnull相当でない場合に次の処理
			
			usedItemsReportFunc.findRecordByItemNo(currentRow,currentItemNo);
			
			var nextRow = currentRow.next();
			// 次の行が非表示の場合、非表示を解除
			if (nextRow.attr('hidden')) {
				nextRow.removeAttr('hidden');
			}
		}else{
			//図番欄の入力値がnull相当の場合、物品名の列に”図番が未入力です”を表示
			currentRow.find('td[id="itemName"]').text('図番が未入力です');
		}
	});
})