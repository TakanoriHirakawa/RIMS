/**
 * register_item.js
 */
var registerItem = {};
var registerItemFunc = {};

$(function() {
    $('#itemNo').on('change', function(event) {
        registerItemFunc.checkItemNo(event.target);
    });
    $('#itemName').on('change', function(event) {
        registerItemFunc.checkItemName(event.target);
    });
});

/**
 * 登録ボタンのアクション
 */
registerItem.registItem = function() {
	//errorCheckを実施
	if(registerItemFunc.hasErrorCheck()){
		return;
	}
	//Errorがなければsubmit
	 $("#itemDetails").submit();
}

/**
 * 登録後のredirectを受けた場合に次の処理を確認する
 */
$(document).ready(function(){
	var saveParam = new URLSearchParams(window.location.search).get('save')
	if(saveParam === null){
		return
	}
	if(saveParam === 'fail'){
		alert('登録に失敗しました')
	}else{
		alert('登録に成功しました')
		if (confirm('続けて登録を行いますか')){
			window.location.href ='/inventory/register_item'
		}else{
			window.location.href='/inventory/inventory'
		}
	}
})
 