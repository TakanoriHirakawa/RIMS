/**
 * js2
 * repairingHome.jsの関数部
 * 
 */
var repairingHomeFunc = {};

/**
 * 引数（契約id）から該当契約の対象となる
 * 製品リスト（productDropdown）を生成、更新する関数
 * @param selectedContractId home.html上で選択された契約id
 * @returns productDropdown 選択された契約idの対象となる製品のドロップダウンリスト
 */
repairingHomeFunc.updateProductDropdown = function(selectedContractId) {
	$.ajax({
		type: 'GET',
		url: '/repair_report/getProductsByContractId',
		data: {
			selectedContractId: selectedContractId
		},
		success: function(data) {
			// dataが配列でない場合に対応
			var products = Array.isArray(data) ? data : Object.values(data);

			// 取得したデータを使ってproductDropdownを更新
			var productDropdown = document.getElementById('productDropdown');

			if (productDropdown) {
				productDropdown.innerHTML = ''; // ドロップダウンをクリア
				products.forEach(function(product) {
					var option = document.createElement('option');
					option.value = product.id;
					option.text = product.product_name;
					productDropdown.appendChild(option);
				});
			}
		},
		error: function() {
			console.error('Failed to retrieve products.');
		}
	});
};

/**
 * 引数（依頼日、契約id）から該当契約のleadTimeを取得し、
 * 依頼日に加算して、納期日（deadLineDate）を戻す関数。
 * @param requestDate home.html上でされた依頼日
 * @param selectedContractId home.html上で選択された契約id
 * @returns  deadLineDate
 */
repairingHomeFunc.updateDeadLineDate = function(requestDate, selectedContractId) {
	$.ajax({
		type: 'GET',
		url: '/repair_report/calcDeadLineDateByContractId',
		data: {
			selectedContractId: selectedContractId,
			requestDate: requestDate
		},
		success: function(data) {
			$("#deadlineDate").val(data);
		},
		error: function() {
			console.error('Failed to retrieve products.');
		}
	});
};

/**
 * <form id='tempRepairingForm'>を送信する関数
 */
function submitUsedItemsReport() {
	    // フォームデータを取得
    var formElement = document.getElementById('tempRepairing');
    var formData = new FormData(formElement);

    // フォームデータをオブジェクトに変換
    var formDataObject = {};
    //ここが要編集かも
    formData.forEach(function(value, key){
        formDataObject[key] = value;
    });
    
	document.getElementById('tempRepairing').submit();
};