/**
 * /repair_report/homeに適用するjs
 * /Original_RIMS/src/main/resources/static/js/repairingHome.js
 */
window.onload = function() {
	//①完了日にデフォルトで当日を入力する処理
	var today = new Date();
	today.setDate(today.getDate());
	var yyyy = today.getFullYear();
	var mm = ("0" + (today.getMonth() + 1)).slice(-2);
	var dd = ("0" + today.getDate()).slice(-2);
	document.getElementById("completionDate").value = yyyy + '-' + mm + '-' + dd;

	//② 契約名称の選択が変更されたときの処理
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

	//③依頼日が入力されたら選択されている契約内容に応じた納期日を返す処理
	var requestDate = document.getElementById('requestDate')

	if (requestDate && contractDropdown) {
		requestDate.addEventListener('change', function() {
			var selectedContractId = contractDropdown.value;
			var inputValue = requestDate.value;
			updateDeadLineDate(inputValue,selectedContractId);
		});


		function updateDeadLineDate( requestDate,selectedContractId) {
			console.log('依頼日'+requestDate);
			console.log('依頼日'+selectedContractId);
			
			$.ajax({
				type: 'GET',
				url: '/repair_report/calcDeadLineDateByContractId',
				data: {
					selectedContractId: selectedContractId,
					requestDate: requestDate
				},
				success: function(data) {
				var resultDate = data
					console.log(resultDate)

					//取得したデータを使ってdeadLineDateを更新
					var deadLineDate = document.getElementById('deadLineDate');
					deadLineDate.value = resultDate;
				},
				error: function() {
					console.error('Failed to retrieve products.');
				}
			});
		}
	}
}