# コーヒー在庫管理システム（Spring MVC）

## 概要

このプロジェクトは、コーヒー豆の在庫情報を管理するためのWebアプリケーションです。
Spring MVCを使用して、以下の機能を実装しています。

* 検索
* 登録
* 更新
* 削除
* CSVダウンロード
* 並べ替え

---

## 使用技術

* Java
* Spring MVC
* JSP（ビュー）
* JDBC（DB接続）
* HTML / CSS / JavaScript
* Apache Tomcat

---

## 機能一覧

### 1. 検索機能

* 商品ID・銘柄名でコーヒー豆を検索できます

### 2. 登録機能

* 新しいコーヒー豆の情報を登録できます

### 3. 更新機能

* 登録済みデータの編集が可能です

### 4. 削除機能

* 不要なデータを削除できます

### 5. CSVダウンロード

* データをCSV形式で出力

### 6. 並べ替え機能

* 条件に応じてデータを並べ替えることができます

---

## 画面構成

* 一覧画面（検索結果表示）
* 登録画面
* 更新画面

---

## ディレクトリ構成

```
src/
 ├─ controller（pack.coffee）   // リクエスト処理
 ├─ dao（dao.coffee）           // DBアクセス
 ├─ dto（dto.coffee）           // データ受け渡し
 ├─ form                        // フォーム用クラス
 ├─ util（cm）                  // 共通処理
 └─ constant（cnst）            // 定数

WebContent/
 ├─ css/        // スタイルシート
 ├─ img/        // 画像ファイル
 ├─ js/         // JavaScript
 ├─ jsp/        // JSPファイル（画面）
 ├─ WEB-INF/    // 設定ファイル（外部から直接アクセス不可）
 └─ index.jsp   // トップページ（一覧画面へリダイレクト）
```

---

## 実行方法

### 1. 環境準備

* Java（JDK）
* Eclipse（または任意のIDE）
* Apache Tomcat

### 2. 手順

1. このリポジトリをクローン

```
git clone https://github.com/toshiya1141140-coder/202602KunrenRepoSpring.git
```

2. Eclipseにインポート

3. Tomcatを設定

4. プロジェクトを右クリック
   →「実行」→「サーバーで実行」を選択

5. ブラウザでアクセス
   http://localhost:8080/202602KunrenRepoSpring/DspCoffeeSearch

※ コーヒー一覧画面が表示されます

---

## 学習ポイント

このプロジェクトでは以下を学べます：

* Spring MVCの基本構成
* DispatcherServletの役割
* Controller / Service / DAO の責務分離
* JSPとの連携
* CRUD処理の実装
