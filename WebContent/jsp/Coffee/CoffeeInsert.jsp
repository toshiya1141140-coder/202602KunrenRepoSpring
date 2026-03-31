<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- ライブラリ -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
  <!DOCTYPE html>
  <html lang="ja">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品登録画面</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/coffee/coffeeInsert.css">
    <script>
      //商品追加ボタン押下時
      function fnc_insert(form) {
        // 入力値(商品ID検索欄に入力されたデータ)を変数coffeeIdにまとめる
        var coffeeId = form.coffeeId.value;
        // 入力値(銘柄名検索欄に入力されたデータ)を変数coffeeNameにまとめる
        var coffeeName = form.coffeeName.value;

        //商品IDと銘柄名が両方未入力の場合
        if (coffeeId === "" && coffeeName === "") {
          alert("商品IDと銘柄名を入力してください。");
          return;
        }

        //商品IDは必須項目であり、条件によっては、アラートを発令
        //商品IDが空文字の時
        if (coffeeId === "") {
          alert("商品IDを入力してください。");
          form.coffeeId.value;
          return;
		//商品IDが半角数字じゃない時
        } else if (!/^[0-9]+$/.test(coffeeId)) {
          alert("商品IDは半角数字で入力してください。");
          form.coffeeId.value;
          return;
		//1以上の数値じゃない時
        } else if (Number(coffeeId) <= 0) {
          alert("商品IDには1以上の数値を入力してください。");
          form.coffeeId.value;
          return;
        }

        //銘柄名は必須項目であり、条件によっては、アラートを発令
        if (coffeeName === "") {
          alert("銘柄名を入力してください。");
          //エラー箇所にフォーカスを当てる
          form.coffeeName.value;
          return;
        //銘柄名の長さチェック（50文字以内）
        }else if (coffeeName.length > 50) {
           alert("銘柄名は50文字以内で入力してください。");
           form.coffeeName.value;
    	   return;
           }
    	//すべてのチェックをクリアしたら送信
        form.action = "CoffeeInsert";
        form.submit();
      }

      //戻るボタン押下時
      function fnc_back(form) {
        form.action = "DspCoffeeSearch";
        form.submit();
      }

      //ログインページボタン押下時
      function fnc_logout(form) {
        form.action = "Logout";
        form.submit();
      }
    </script>
  </head>
  <body>
    <!--ヘッダー情報-->
    <header>
      <div class="left_area">
        <img src="<%=request.getContextPath()%>/img/coffee/01385.png" alt="Kashiwagi Coffee ロゴ" class="cup_logo">
        <div class="shop_name">Kashiwagi Coffee</div>
        <img src="<%=request.getContextPath()%>/img/coffee/coffee_logo.png" alt="Kashiwagi Coffee ロゴ" class="shop_logo">
      </div>
      <h1>商品登録</h1>
      <form method="get">
			<div class="right_area">
				<button type="button" class="btn_logout" onclick="fnc_logout(this.form)">ログアウト</button>
			</div>
		</form>
    </header>
    <!--登録エリア-->
    <f:form modelAttribute="coffeeForm" class="entry_area" method="post">
      <div class="entry_row">
        <label>
          <p>商品ID <span>必須</span></p>
          <f:input path="coffeeId" />
        </label>
      </div>
      <div class="entry_row">
        <label>
          <p>銘柄名 <span>必須</span></p>
          <f:input path="coffeeName" />
        </label>
      </div>
      <div class="entry_row">
        <label>
          <p>価格(100g)</p>
          <f:input path="coffeePrice"/>
        </label>
      </div>
      <div class="entry_row">
        <label>
          <p>在庫数量(g)</p>
          <f:input path="coffeeStock"/>
        </label>
      </div>
      <div class="entry_row">
        <label>
          <p>最終入荷日</p>
          <f:input path="lastDate" type="date" />
        </label> 
      </div>
      <!-- ボタンエリア -->
      <div class="action-buttons">
        <f:button class="btn_entry btn" onclick="fnc_insert(this.form)">商品追加</f:button>
        <f:button class="btn_back btn" onclick="fnc_back(this.form)">在庫一覧へ戻る</f:button>
      </div>
      <!-- データが登録された場合に表示 -->
      <c:if test="${!empty insertMsg}">
        <div class="msg">
          <strong>${insertMsg}</strong>
        </div>
      </c:if>
      <!-- データの登録に失敗した時に表示 -->
      <c:if test="${!empty errorMsg}">
        <div class="msg">
          <strong>${errorMsg}</strong>
        </div>
      </c:if>
    </f:form>
  </body>

  </html>