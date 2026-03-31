package pack.coffee;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.coffee.CoffeeDto;
import pack.coffee.form.CoffeeForm;

/**
 * コーヒー情報コントローラークラスです
 * 説明：コーヒー情報を操作するクラス
 */
@Controller
public class CoffeeController {

	@Autowired
	private CoffeeService coffeeService;
	
	/**
	 * ルートURL（"/"）にアクセスされた際の初期表示処理。
	 * コーヒー検索画面表示用のURL（/DspCoffeeSearch）へリダイレクトする。
	 *
	 * @return コーヒー検索画面表示処理へのリダイレクトパス
	 */
	// 追記：元々用意されていたログイン機能およびメニュー画面を削除し、
	// アプリケーションへ直アクセスした際のトップページを検索初期画面に変更した。
	// 既存のgetSearchAll()の処理を活かすため、root()メソッドを追加し、
	// DspCoffeeSearchのURLへリダイレクトするよう実装した。
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() {
	    return "redirect:/DspCoffeeSearch";
	}
	
	/**
	 * 検索画面初期表示
	 * @param model
	 * @return 初期検索画面
	 */
	@RequestMapping(value = "/DspCoffeeSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String getSearchAll(Model model, @ModelAttribute("coffeeForm") CoffeeForm coffeeForm) {
		try {
			//全件データを取得
			List<CoffeeDto> list = coffeeService.getSearchAll();

			//取得したデータを画面に渡す為、dbdataに格納
			model.addAttribute("dbdata", list);

			//遷移先画面へ
			//View名を返却する
			return "../jsp/Coffee/CoffeeSearch";
		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			//エラーメッセージをdbdataに格納
			model.addAttribute("errorMsg", "データベースエラーが発生しました。");
			//遷移先画面へ
			return "../jsp/Coffee/CoffeeSearch";
		}
	}

	/**
	 * 検索ボタン押下時、検索条件に応じて検索結果を表示させる
	 * @param model
	 * @param coffeeForm
	 * @return 初期検索画面
	 */
	@RequestMapping(value = "/CoffeeSearch", method = RequestMethod.POST)
	public String selectByKey(Model model, @ModelAttribute("coffeeForm") CoffeeForm coffeeForm) {

		try {

			//検索条件に沿ったデータを取得
			List<CoffeeDto> list = coffeeService.selectByKey(coffeeForm.getCoffeeId(), coffeeForm.getCoffeeName());

			//取得したデータを画面に渡す為、dbdataに格納
			model.addAttribute("dbdata", list);

			//遷移先画面へ
			//View名を返却する
			return "../jsp/Coffee/CoffeeSearch";

		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			//エラーメッセージをdbdataに格納
			model.addAttribute("errorMsg", "データベースエラーが発生しました。");
			//遷移先画面へ
			return "../jsp/Coffee/CoffeeSearch";
		}
	}

	/**
	 * 登録画面遷移
	 * @param model
	 * @param coffeeForm
	 * @return 登録画面
	 */
	@RequestMapping(value = "/DspCoffeeInsert", method = RequestMethod.POST)
	public String DspInsert(Model model, @ModelAttribute("coffeeForm") CoffeeForm coffeeForm) {

		//遷移先画面へ
		//View名を返却する
		return "../jsp/Coffee/CoffeeInsert";
	}

	/**
	 * 新規登録ボタン押下時、登録処理を行う
	 * @param model
	 * @param coffeeForm
	 * @return 登録画面
	 */
	@RequestMapping(value = "/CoffeeInsert", method = RequestMethod.POST)
	public String insertCoffee(Model model, @ModelAttribute("coffeeForm") CoffeeForm coffeeForm) {

		try {
			//登録処理
			//戻り値は登録した件数
			int insertRow = coffeeService.insertCoffee(coffeeForm.getCoffeeId(),
					coffeeForm.getCoffeeName(),
					coffeeForm.getCoffeePrice(),
					coffeeForm.getCoffeeStock(),
					coffeeForm.getLastDate());

			//登録が成功した場合、trueの文字列を変数msgにまとめる
			String msg = (insertRow == 1) ? "1件のデータを登録しました" : "データの登録に失敗しました";

			//登録可否メッセージを格納
			model.addAttribute("insertMsg", msg);

			//遷移先画面へ
			//View名を返却する
			return "../jsp/Coffee/CoffeeInsert";

		} catch (IllegalArgumentException e) {
			//スタックトレース表示
			e.printStackTrace();

			// 重複エラーの場合、Modelにメッセージを追加
			model.addAttribute("errorMsg", e.getMessage());

			//遷移先画面へ
			//View名を返却する
			return "../jsp/Coffee/CoffeeInsert";

		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();

			// その他の例外
			model.addAttribute("errorMsg", "登録中にエラーが発生しました");

			//遷移先画面へ
			//View名を返却する
			return "../jsp/Coffee/CoffeeInsert";
		}
	}

	/**
	 * 削除ボタン押下時、削除処理を行う
	 * @param model
	 * @param coffeeForm
	 * @return 検索画面
	 */
	@RequestMapping(value = "/CoffeeDelete", method = RequestMethod.POST)
	public String deleteCoffee(Model model, @ModelAttribute("coffeeForm") CoffeeForm coffeeForm) {

		try {

			//削除処理 戻り値は削除した件数
			int deleteRow = coffeeService.deleteCoffee(coffeeForm.getRadio());

			//削除した件数が1件の時だけ、trueが実行
			String msg = (deleteRow == 1) ? "1件のデータを削除しました" : "データの削除に失敗しました";

			//削除可否メッセージを格納
			model.addAttribute("deleteMsg", msg);

			//全件データを取得
			List<CoffeeDto> list = coffeeService.getSearchAll();

			//リストをdbdataに格納
			model.addAttribute("dbdata", list);

			//遷移先画面へ
			return "../jsp/Coffee/CoffeeSearch";
		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			//遷移先画面へ
			return "../jsp/Coffee/CoffeeSearch";
		}
	}

	/**
	 * 検索画面から更新ボタン押下時、ラジオボタンで選択されたデータを更新画面へ初期表示する
	 * @param model
	 * @param coffeeForm
	 * @return 更新画面　/　検索画面
	 */
	@RequestMapping(value = "/DspCoffeeUpdate", method = RequestMethod.POST)
	public String dspUpdateCoffee(Model model, @ModelAttribute("coffeeForm") CoffeeForm coffeeForm) {

		//初期化
		CoffeeDto coffeeDto = null;

		try {
			//選択されたラジオボタンを条件に、データを取得
			List<CoffeeDto> list = coffeeService.displayUpdate(coffeeForm.getRadio());

			//nullや空文字じゃない時、選択された1件を取得し、DTOに詰める
			if (list != null && !list.isEmpty()) {
				coffeeDto = list.get(0);
			} else {
				//エラーメッセージをdbdataに格納
				model.addAttribute("errorMsg", "選択されたデータが見つかりませんでした。");
				//遷移先画面へ
				return "../jsp/Coffee/CoffeeSearch";
			}

			// 取得したデータを、そのまま引数の coffeeForm に詰め直す
			// これにより、JSPの <f:form modelAttribute="coffeeForm"> と紐付く
			//coffeeForm.setCoffeeId(coffeeDto.getCoffeeId());
			//coffeeForm.setCoffeeName(coffeeDto.getCoffeeName());
			//coffeeForm.setCoffeePrice(coffeeDto.getCoffeePrice());
			//coffeeForm.setCoffeeStock(coffeeDto.getCoffeeStock());
			//coffeeForm.setLastDate(coffeeDto.getLastDate());

			//コピー元のオブジェクト（coffeeDto）から、同じ名前の変数（フィールド）を探して、コピー先のオブジェクト（coffeeForm）へ値を丸ごと移し替える
			//第1引数（Source）: データが入っている方（今回はDBから取得した coffeeDto）
			//第2引数（Target）: データを移したい方（今回は画面に渡す空の coffeeForm）
			BeanUtils.copyProperties(coffeeDto, coffeeForm);

			//詰めなおしたcoffeeFormを更新画面に渡すために、coffeeFormに格納
			model.addAttribute("coffeeForm", coffeeForm);

			//遷移先画面へ
			return "../jsp/Coffee/CoffeeUpdate";
		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			//遷移先画面へ
			return "../jsp/Coffee/CoffeeSearch";
		}
	}

	/**
	 * 更新フォームで変更された値をDBに再登録する
	 * @param model
	 * @param coffeeForm
	 * @return 検索画面
	 */
	@RequestMapping(value = "/CoffeeUpdate", method = RequestMethod.POST)
	public String updateCoffee(Model model, @ModelAttribute("coffeeForm") CoffeeForm coffeeForm) {

		try {
			//更新処理を実行し、更新した件数を戻す
			int updateRow = coffeeService.updateCoffee(coffeeForm.getCoffeeId(),
					coffeeForm.getCoffeeName(),
					coffeeForm.getCoffeePrice(),
					coffeeForm.getCoffeeStock(),
					coffeeForm.getLastDate());

			//更新が成功したなら
			if (updateRow == 1) {
				//初期画面に更新成功メッセージを格納
				model.addAttribute("updateMsg", "1件のデータを更新しました");

				//全件データを取得し、CoffeeSearch.jspへデータを渡す
				model.addAttribute("dbdata", coffeeService.getSearchAll());

				//初期検索画面へ
				return "../jsp/Coffee/CoffeeSearch";
			} else {
				//エラーメッセージを格納
				model.addAttribute("errorMsg", "データの更新に失敗しました");

				//初期検索画面へ
				return "../jsp/Coffee/CoffeeSearch";
			}
		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			//遷移先画面へ
			return "../jsp/Coffee/CoffeeSearch";
		}
	}

	/**
	 * 初期検索画面でCSV出力ボタン押下時、CSVファイルを書き出す。
	 * @param response
	 */
	@RequestMapping(value = "/CoffeeCsvDownload", method = RequestMethod.GET)
	public void csvDownload(HttpServletResponse response) {
		//ブラウザにCSVファイルとして扱わせる。
		//MS932→Excel対応
		response.setContentType("text/csv; charset=MS932");
		//ブラウザにダウンロードさせる命令
		//保存ファイル名はcoffee.csv
		response.setHeader("Content-Disposition", "attachment; filename=\"coffee.csv\"");

		//PrintWriter→AutoCloseableを実装。tryブロック終了後、自動でclose()が呼ばれる
		try (PrintWriter pw = response.getWriter()) {

			//全件データを取得
			List<CoffeeDto> list = coffeeService.getSearchAll();

			//CSVの1行目はヘッダーとして書き出す
			//printlnで自動的に改行が入る
			pw.println("商品ID,銘柄名,価格(100g),在庫数量(g),最終入荷日");

			//拡張for文で繰り返す
			for (CoffeeDto coffeedto : list) {
				//予約席にカンマ区切りで順番に並べて文字を組み立てる
				pw.println(String.format("%s,%s,%s,%s,%s",
						coffeedto.getCoffeeId(),
						coffeedto.getCoffeeName(),
						coffeedto.getCoffeePrice(),
						coffeedto.getCoffeeStock(),
						coffeedto.getLastDate()));
			}
			//バッファ（一時的な貯金箱）に溜まっているデータを、強制的に相手（ブラウザ）へ送り出す
			//データの書き出しが確実に終わったことを明示的にシステムへ伝えるために、習慣として flush() を書くらしい。
			pw.flush();

		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
		}
	}

	/**
	 * 並べ替えボタン選択時、並べ替え処理をする
	 * @param model
	 * @param coffeeForm
	 * @return 検索画面
	 */
	@RequestMapping(value = "/CoffeeSort", method = RequestMethod.POST)
	public String sortCoffee(Model model, @ModelAttribute("coffeeForm") CoffeeForm coffeeForm) {

		try {
			//画面から送信された並び替え条件を元に、sortメソッドを呼び出して並び替えたのデータを取得
			List<CoffeeDto> list = coffeeService.sort(coffeeForm.getSort());
			
			//取得したリストをdbdataに格納
			model.addAttribute("dbdata", list);

			//初期検索画面へ
			return "../jsp/Coffee/CoffeeSearch";
		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			//初期検索画面へ
			return "../jsp/Coffee/CoffeeSearch";
		}
	}
}
