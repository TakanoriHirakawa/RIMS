/**
 * /Original_RIMS/src/main/resources/static/js/inventory/receivingItems.js
 */

// 図番が入力された際の処理
$(document).ready(function() {
	// 関数①：図番の入力があった場合の処理
	$('#postRecevingItems').on('change', 'input[type="text"][id^="itemNo_"]', function() {
		receivingItemsFunc.handleItemNoInput($(this));
	});

	// 関数②：Enterキーが押された場合の処理
	$('#postRecevingItems').on('keypress', 'input[type="text"]', function(event) {
		receivingItemsFunc.handleKeyPress($(this), event);
	});
});