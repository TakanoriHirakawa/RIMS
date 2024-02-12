/**
 * /repair_report/homeに適用するjs
 * /Original_RIMS/src/main/resources/static/js/repairingJs/repairingHome.js
 */

window.onload = function() {
	//①完了日にデフォルトで当日を入力する処理
	var today = new Date();
	today.setDate(today.getDate());
	var yyyy = today.getFullYear();
	var mm = ("0" + (today.getMonth() + 1)).slice(-2);
	var dd = ("0" + today.getDate()).slice(-2);
	document.getElementById("completionDate").value = yyyy + '-' + mm + '-' + dd;

	//②初期値の取得
	var initialId=$("#initialId").val();
	$("#userDropdown").val(initialId)
	var initialContractId = $("#initialContractId").val();
	$("#contractDropdown").val(initialContractId)
	
	//④ 初期表示時に契約名に応じた製品名を表示
	repairingHomeFunc.updateProductDropdown(initialContractId);
}

//④ 契約名称の選択が変更されたとき製品リストを更新する処理
$(document).ready(function() {
	$("#contractDropdown").on('change', function() {
		var selectedContractId = $("#contractDropdown").val();
		repairingHomeFunc.updateProductDropdown(selectedContractId)
	});
});

//⑤依頼日と契約名称が選択されたとき納期日を更新する処理
$(document).ready(function() {
	$("#contractDropdown,#requestDate").on('change', function() {
		var selectedContractId = $("#contractDropdown").val();
		var inputRequestDate = $("#requestDate").val();
		
		if( inputRequestDate !== null && inputRequestDate !== undefined && inputRequestDate.trim() !== ''){
			repairingHomeFunc.updateDeadLineDate(inputRequestDate, selectedContractId);		
		};
		
		});
});