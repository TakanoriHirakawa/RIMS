/**
 *admin/admin_resister/signup.htmlに適用
 */

$(document).ready(function() {
	$('#checkButton').click(function() {
		var userId = $('#userIdInput').val();
		console.log('userId:', userId); // userIdをコンソールに出力
		$.ajax({
			type: 'POST',
			url: '/admin/admin_resister/signup/checkUserIdDuplicate', // 重複チェックを行うエンドポイントのURLを指定してください
			data: { userId: userId },
			success: function(response) {
				$('#checkUserIdResult').text(response);
				if (!response.flg){
					$('#submitButton').attr('type', 'submit');
				}
				
			}
		});
	});
});
