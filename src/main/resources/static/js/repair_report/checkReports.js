/**
 * checkReports.js
 */

 /**
 * ページ読込の際に、テーブルの2行目以降について
 * 図番の値がnull相当の場合にhidden非表示にする処理
 */
//$(document).ready(function(){
//    $("#usedItemsReportTable tbody tr").each(function(){
//        var itemNo = $(this).find("td:nth-child(2)").text().trim(); // 図番の列の値を取得
//        if(itemNo === '') { // もし図番の値が空（null相当）なら
//            $(this).hide(); // その行を非表示にする
//        }
//    });
//});

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