/**
 * checkReports.js
 */

 /**
 * ページ読込の際に、テーブルの2行目以降について
 * 図番の値がnull相当の場合にhidden非表示にする処理
 */
    $(document).ready(function(){
        // ページ読み込み時に実行される処理
        $('#usedItemsReportTable tbody tr:gt(0)').each(function() {
            var itemNoValue = $(this).find('input[type="text"][name*="itemNo"]').val();
            // 図番の値がnull相当の場合に行を非表示にする
            if (itemNoValue == null || itemNoValue.trim() === '') {
                $(this).hide();
            }
        });
    });

/**
 *< button id ="backToHome" > がクリックされたときの処理
 * */ 
$(document).ready(function(){
    $("#backToHome").click(function(){
        // フォームを送信する
        $("#submitForm").attr("action", "/repair_report/home"); // フォームのアクションを指定
        $("#submitForm").attr("method", "post"); // POSTリクエストを設定
        $("#submitForm").submit(); // フォームを送信
    });
});

/**
 *< button id ="" > がクリックされたときの処理
 * */ 
$(document).ready(function(){
    $("#backToUsedItemsReport").click(function(){
        // フォームを送信する
        $("#submitForm").attr("action", "/repair_report/usedItemsReport"); // フォームのアクションを指定
        $("#submitForm").attr("method", "post"); // POSTリクエストを設定
        $("#submitForm").submit(); // フォームを送信
    });
});