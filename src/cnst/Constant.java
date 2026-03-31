package cnst;
/**
 * システム共通定数定義クラス
 * @author  k_takahashi
 * @version 00.00 2020/11/20
 */
public class Constant {

	/******** ↓キー **********/
	/** データベース接続 **/
	public static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
	public static final String JDBC_USER = "postgres";
	public static final String JDBC_PASS = "postgres";

	//アップロードの保存先
	public static final String UPLOAD = "C:/java_2024/pleiades/workspace/DispRepoSpring/WebContent/upload/";
	
	//戻り値
	public static final int RET_OK = 0;
	public static final int RET_NG = -1;

	/******** ↓パス **********/
	/** ログイン画面 **/
	public static final String LOGIN = "/jsp/Login.jsp";
	/** メニュー画面 **/
	public static final String MENU = "/jsp/Menu.jsp";
	
	/** エラー画面 **/
	public static final String SYSTEM_ERR = "/jsp/ErrSystem.jsp";
	/** ログインエラー画面 **/
	public static final String LOGIN_SYSTEM_ERR = "../jsp/ErrSystemLogin";

	/******** ↓文字列定数 **********/
	public static final String COLON = ":";

	/** エラーメッセージ(システムエラー：JDBCドライバ読込) **/
	public static final String MSG_SYSTEM_ERR_JDBC_DRIVER = "システムエラー：JDBCドライバ読込処理で異常終了しました。";
	/** エラーメッセージ(システムエラー：) **/
	public static final String MSG_ERR_SYSTEM = "システム管理者にご連絡をお願いします。";
	/** エラーメッセージ(システムエラー：) **/
	public static final String MSG_ERR_SYSTEM_LOGIN = "システムエラー(ログイン処理)：システム管理者にご連絡をお願いします。";
	/** エラーメッセージ(システムエラー：) **/
	public static final String MSG_ERR_SYSTEM_SEARCH = "システムエラー(検索処理)：システム管理者にご連絡をお願いします。";

	/** エラーメッセージ(ログイン) **/
	public static final String MSG_ERR_LOGIN01 = "ログインIDまたはパスワードが誤っています。";

}
