/**
 * register_item.js
 */

var registerItem = {};
var registerItemFunc = {};

// リダイレクトが発生した場合のみ実行
if (window.location.search.includes("redirect=true")) {
	alert('物品の登録が成功しました');

	//次に表示するページの選択(動作確認済)
	//		if(confirm('続けて登録を行いますか？')){
	//				registerItemFunc.nextPage(true);
	//		}else{
	//				registerItemFunc.nextPage(false);
	//		}
}

//入力内容を登録する処理
registerItem.registerItems = function() {
	console.log('registerItems is called')
	var formElement = document.getElementById('itemDetails')
	var formData = new FormData(formElement);

	//フォームデータをオブジェクトに変換
	var formDataObject = {};

	formData.forEach(function(value, key) {
		formDataObject[key] = value;
	});

	document.getElementById('itemDetails').submit();
}