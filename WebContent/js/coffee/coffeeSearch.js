/**
 *検索初期画面のJS 
 */

/**
 * 
 * 検索ボタン押下時
 * 
 */
function fnc_search(form) {
	// 入力値(商品ID検索欄に入力されたデータ)を変数coffeeIdにまとめる
	var coffeeId = form.coffeeId.value;
	// 入力値(銘柄名検索欄に入力されたデータ)を変数coffeeNameにまとめる
	var coffeeName = form.coffeeName.value;

	//両方未入力ならエラー
	if (coffeeId === "" && coffeeName === "") {
		alert("商品IDまたは銘柄名のどちらかを入力してください。");
		//在庫一覧表示メソッドを呼び出すため、DspCoffeeSearchへ移動
		form.action = "DspCoffeeSearch";
		form.submit();
		return;
	}

	//商品IDの数値・正の数チェック
	//空文字じゃない時
	if (coffeeId !== "") {
		// 「数値ではない（isNaN）」または「0以下（Number(coffeeId) <= 0）」の場合
		if (isNaN(coffeeId) || Number(coffeeId) <= 0) {
			alert("商品IDには1以上の数値を入力してください。");

			//入力欄にカーソルを戻す
			form.coffeeId.focus();

			//在庫一覧表示メソッドを呼び出すため、DspCoffeeSearchへ移動
			form.action = "DspCoffeeSearch";
			form.submit();

			//ここで処理を終了
			return;
		}
	}

	//銘柄名の長さチェック（50文字以内）
	if (coffeeName.length > 50) {
		alert("銘柄名は50文字以内で入力してください。");
		form.coffeeName.focus();
		//在庫一覧表示メソッドを呼び出すため、DspCoffeeSearchへ移動
		form.action = "DspCoffeeSearch";
		form.submit();
		return;
	}

	//action(フォームの送信先の設定)
	form.action = "CoffeeSearch";
	//submit
	form.submit();
}

/**
 * 
 * クリアボタン押下時
 * 
 */
function fnc_clear(form) {
	// 入力欄をすべて空にする
	form.coffeeId.value = "";
	form.coffeeName.value = "";

	//在庫一覧表示メソッドを呼び出すため、DspCoffeeSearchへ移動
	form.action = "DspCoffeeSearch";
	form.submit();
}

/**
 * 
 * ログインページボタン押下時
 * 
 */
function fnc_logout(form) {
	form.action = "Logout";
	form.submit();
}

/**
 * 
 * 削除ボタン押下時
 * 
 */
function fnc_delete(form) {
	//削除確認(ダイアログ)の表示
	if (confirm('本当に削除してもよろしいいですか？')) {
		// ユーザーが「OK」を選択した場合の処理
		alert('削除しました。');
	} else {
		// ユーザーが「キャンセル」を選択した場合の処理
		alert('キャンセルしました。');
		return;
	}
	//「OK」が選択された時、送信
	form.action = "CoffeeDelete";
	form.submit();
}

/**
 * 
 * 登録ボタン押下時
 * 
 */
function fnc_insert(form) {
	form.action = "DspCoffeeInsert";
	form.submit();
}

/**
 * 
 * 更新ボタン押下時
 * 
 */
function fnc_update(form) {
	form.action = "DspCoffeeUpdate";
	form.submit();
}

/**
 * 
 * 商品ID・価格・在庫数量・最終入荷日の並べ替え機能
 * DOMContentLoaded→HTMLの読込が完了した瞬間に発生するイベント
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	const form = document.getElementById("js_form");
	const mainSort = document.getElementById("mainSort");

	mainSort.addEventListener("change", () => {
		// フォームのactionを書き換えて送信
		form.action = "/202602KunrenRepoSpring/CoffeeSort";
		form.submit();
	});
});
