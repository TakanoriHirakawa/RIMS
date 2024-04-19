/**
 * register_itemFunc.js
 */

var registerItemFunc = {};
var NON_NULL='入力必須項目です'
var MIN_OVER_MAX ='下限の値は上限未満にしてください'
var DUPLICATE_ITEM_NO='登録済みの図番です'
var DEFAULT_LOCAT='/repair_report/home'
var AJAX_FAIL='データの送信に失敗しました'

/**
 * 図番の入力チェック
 */
registerItemFunc.checkItemNo =function(inputElement){
	var itemNoVal = $(inputElement).val()
	//非ヌル・空文字
	registerItemFunc.isNullCurrentVal(inputElement)

	//図番の重複チェック
	registerItemFunc.checkDuplicate(itemNoVal,function(isDuplicate,isReject){
		if(isReject){
			//ajax通信に失敗した場合はAlert
			alert(AJAX_FAIL)
		}else{
			if(isDuplicate){
				//重複あり：Errorのspanタグの有無に応じた処理
				if(!$(inputElement).next('span.hasError').length){
					$(inputElement).after('<span class="hasError">'+DUPLICATE_ITEM_NO+'</span>');
				}else{
					$(inputElement).next('span.hasError').text(DUPLICATE_ITEM_NO);
				}
			}else{
				//重複なし：ErrorのSpanタグを取り除く
				if($(inputElement).next('span.hasError').length){
					$(inputElement).next('span.hasError').remove();
				}
			}
		}
	})
}

/**
 *ajaxでDBに登録済みの図番がないか確認 （図番の重複チェック）
*/
registerItemFunc.checkDuplicate=function(itemNoVal,callback,reject){
	$.ajax({
		url:'/inventory/checkDuplicate',
		type:'get',
		data:{itemNo:itemNoVal}
	})
	.done(function(response){
		if(response){
			callback(true);
		}else{
			callback(false);
		}
	})
	.fail(function(){
		//TODO；非同期通信に失敗した場合の適切なエラーハンドリングに修正
		reject(true)
	})
}

/**
 * 物品名の入力チェック
 */
registerItemFunc.checkItemName=function(inputElement){
	//非ヌル・空文字
	registerItemFunc.isNullCurrentVal(inputElement)
}

/**
 * 非NULLチェック
 * @param inputElement：現在の入力エリア
 */
registerItemFunc.isNullCurrentVal=function(inputElement){
	var currentVal = $(inputElement).val()
	if(currentVal==='' || currentVal===null){
		if(!$(inputElement).next('span.hasError').length){
			$(inputElement).after('<span class="hasError">'+NON_NULL+'</span>')
		}
		return true;
	}else{
		if($(inputElement).next('span.hasError').length){
			$(inputElement).next('span.hasError').remove();
		}
		return false;
	}
}

/**
 *登録ボタン押下時のエラーチェック
 * @return 入力エラーがあればtrue
*/
registerItemFunc.hasErrorCheck=function(){
	var hasError = false;
	
	//図番、物品名の非NULLチェック
	$("input.nonNull").each(function(){
		if(registerItemFunc.isNullCurrentVal(this)){
			hasError = true;
		}
	});

	var minVal = $('#stockMin').val();
	var maxVal = $('#stockMax').val();

	//minVal,maxVallがnull相当の場合は0
	if (minVal ==='' || minVal ===null){
		minVal=0
		$('#stockMin').val(minVal);
	}
	if(maxVal==='' || maxVal ===null){
		maxVal = 0
		$('#stockMax').val(maxVal);
	}	
	
	//minVal>0かつmaxVal>0かつminVal>=maxVal ならhasError=true
    if (parseInt(minVal) > 0 && parseInt(maxVal) > 0 && parseInt(minVal) >= parseInt(maxVal)) {
		if(!$('#stockMin').next('span.hasError').length){
			$('#stockMin').after('<span class="hasError">'+MIN_OVER_MAX+'</span>')
		}
        hasError = true;
    }else{
		if($('#stockMin').next('span.hasError').length){
			$('#stockMin').next('span.hasError').remove();
		}
	}
	return hasError;
};

