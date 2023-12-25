/**
 * /repair_report/homeに適用するjs
 * /Original_RIMS/src/main/resources/static/js/repairingHome.js
 */
window.onload = function() {
	// 契約名称の選択が変更されたときの処理
	var contractDropdown = document.getElementById('contractDropdown');
	var productDropdown = document.getElementById('productDropdown');

	if (contractDropdown && productDropdown) {
		contractDropdown.addEventListener('change', function() {
			var selectedContractId = this.value;
			//関数呼び出し
			updateProductDropdown(selectedContractId);
		});
	}

	// 関連する製品名を取得し、productDropdownを更新する関数
	function updateProductDropdown(selectedContractId) {
		$.ajax({
			type: 'GET',
			url: '/repair_report/getProductsByContractId',
			data: {
				selectedContractId: selectedContractId
			},
			success: function(data) {
				// dataが配列でない場合に対応
				var products = Array.isArray(data) ? data : Object.values(data);
				console.log(products)

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
	}
};