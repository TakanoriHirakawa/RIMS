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
	document.getElementById("tempRepairingForm.completionDate").value = yyyy + '-' + mm + '-' + dd;

	//②初期値の取得
	var contractDropdown = document.getElementById('contractDropdown');
	var productDropdown = document.getElementById('productDropdown');
	var requestDate = document.getElementById('tempRepairingForm.requestDate')
	var initialContractId = contractDropdown.value;
	//③ 初期表示時に契約名に応じた製品名を表示
	repairingHomeFunc.updateProductDropdown(initialContractId);

	//④ 契約名称の選択が変更されたときの処理
	if (contractDropdown && productDropdown) {
		contractDropdown.addEventListener('change', function() {
			var selectedContractId = this.value;
			//関数呼び出し
			repairingHomeFunc.updateProductDropdown(selectedContractId);
		});
	}
	//⑤依頼日と契約名称が選択されたときの処理
	if (requestDate && contractDropdown) {
		requestDate.addEventListener('change', function() {
			var selectedContractId = contractDropdown.value;
			var inputValue = requestDate.value;
			//関数呼び出し
			repairingHomeFunc.updateDeadLineDate(inputValue, selectedContractId);
		});
	}
}
