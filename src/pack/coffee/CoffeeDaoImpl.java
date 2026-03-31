package pack.coffee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dao.coffee.ICoffeeDao;
import dto.coffee.CoffeeDto;

/**
 * ICoffeeDaoインタフェースの実装クラス
 */
@Repository
public class CoffeeDaoImpl implements ICoffeeDao {

	//JdbcTemplateクラスをAutowiredでDI
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//全件取得
	@Override
	public List<CoffeeDto> getSearchAll() throws Exception {

		//SQL実行分
		String SQL = "SELECT * FROM t_coffee ORDER BY coffee_id;";
		
		//SQLを実行し、結果をCoffeeDtoのリストとして取得
		List<CoffeeDto> list = jdbcTemplate.query(
				SQL, new Object[] {}, new RowMapper<CoffeeDto>() {
					public CoffeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						CoffeeDto coffeeDto = new CoffeeDto();
						coffeeDto.setCoffeeId(rs.getInt("coffee_id"));
						coffeeDto.setCoffeeName(rs.getString("coffee_name"));
						coffeeDto.setCoffeePrice(rs.getInt("coffee_price"));
						coffeeDto.setCoffeeStock(rs.getInt("coffee_stock"));
						//Date型をLocalDate型に変換してcoffeeDtoにセット
						coffeeDto.setLastDate(rs.getDate("last_date").toLocalDate());
						return coffeeDto;
					}
				});
		//nullまたは文字数が0の時、nullを返す
		if (list == null || list.size() == 0) {
			return null;
		}
		return list;
	}

	//検索条件に応じて取得
	@Override
	public List<CoffeeDto> selectBykey(Integer intId, String strName) throws Exception {

		//SQL実行分
		String SQL = "SELECT * FROM t_coffee;";

		//strIdが空文字またはnullじゃない時宣言
		boolean hasId = (intId != null && !(intId < 0));
		//strNameが空文字またはnullじゃない時宣言
		boolean hasName = (strName != null && !strName.isEmpty());

		//両方未入力の場合
		if (!hasId && !hasName) {
			SQL = "SELECT * FROM t_coffee";
			//商品IDだけが入力された場合
		} else if (hasId && !hasName) {
			SQL = "SELECT * FROM t_coffee WHERE coffee_id =" + "'" + intId + "'";
			//銘柄名だけが入力された場合
		} else if (!hasId && hasName) {
			SQL = "SELECT * FROM t_coffee WHERE coffee_name =" + "'" + strName + "'";
			//両方入力された場合
		} else if (hasId && hasName) {
			SQL = "SELECT * FROM t_coffee WHERE coffee_id = '" + intId + "' AND coffee_name = '" + strName + "'";
		}
		
		//SQLを実行し、結果をCoffeeDtoのリストとして取得
		List<CoffeeDto> list = jdbcTemplate.query(
				SQL, new Object[] {}, new RowMapper<CoffeeDto>() {
					public CoffeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						CoffeeDto coffeeDto = new CoffeeDto();
						coffeeDto.setCoffeeId(rs.getInt("coffee_id"));
						coffeeDto.setCoffeeName(rs.getString("coffee_name"));
						coffeeDto.setCoffeePrice(rs.getInt("coffee_price"));
						coffeeDto.setCoffeeStock(rs.getInt("coffee_stock"));
						//Date型をLocalDate型に変換してcoffeeDtoにセット
						coffeeDto.setLastDate(rs.getDate("last_date").toLocalDate());
						return coffeeDto;
					}
				});
		//nullまたは文字数が0の時、nullを返す
		if (list == null || list.size() == 0) {
			return null;
		}
		return list;
	}

	//登録
	@Override
	public int insertCoffee(Integer strId, String strName, Integer strPrice, Integer strStock, LocalDate lastDate) throws Exception {

		//SQL実行分
		String SQL = "INSERT INTO t_coffee "
				+ "(coffee_id, coffee_name, coffee_price, coffee_stock, last_date, create_date, update_date) "
				+ "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

		try {
			//登録処理
			int ret = jdbcTemplate.update(SQL, new Object[] { strId, strName, strPrice, strStock, lastDate });

			//戻り値は登録した件数
			return ret;
		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			
			return 0;
		}

	}

	//削除
	@Override
	public int deleteCoffee(Integer radio) throws Exception {

		//SQL実行分
		String SQL = "DELETE FROM t_coffee WHERE coffee_id = ? ";

		try {
			//削除処理
			int ret = jdbcTemplate.update(SQL, new Object[] { radio });

			//戻り値は削除した件数
			return ret;

		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			
			return 0;
		}
	}

	//更新画面初期表示
	@Override
	public List<CoffeeDto> displayUpdate(Integer radio) throws Exception {

		//SQL実行分
		String SQL = "SELECT * FROM t_coffee WHERE coffee_id = ?";
		
		//SQLを実行し、結果をCoffeeDtoのリストとして取得
		List<CoffeeDto> list = jdbcTemplate.query(
				SQL, new Object[] { radio }, new RowMapper<CoffeeDto>() {
					public CoffeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						CoffeeDto coffeeDto = new CoffeeDto();
						coffeeDto.setCoffeeId(rs.getInt("coffee_id"));
						coffeeDto.setCoffeeName(rs.getString("coffee_name"));
						coffeeDto.setCoffeePrice(rs.getInt("coffee_price"));
						coffeeDto.setCoffeeStock(rs.getInt("coffee_stock"));
						//Date型をLocalDate型に変換してcoffeeDtoにセット
						coffeeDto.setLastDate(rs.getDate("last_date").toLocalDate());
						return coffeeDto;
					}
				});
		//nullまたは文字数が0の時、nullを返す
		if (list == null || list.size() == 0) {
			return null;
		}
		return list;
	}

	//更新処理
	@Override
	public int updateCoffee(Integer coffeeId, String strName, Integer strPrice, Integer strStock, LocalDate lastDate) throws Exception {

		//SQL実行分
		String SQL = "UPDATE t_coffee SET coffee_name = ?, "
				+ "coffee_price = ?, "
				+ "coffee_stock = ?, "
				+ "last_date = ?, "
				+ "update_date = CURRENT_TIMESTAMP "
				+ "WHERE coffee_id = ?";

		try {
			//更新処理
			int ret = jdbcTemplate.update(SQL, new Object[] { strName, strPrice, strStock, lastDate, coffeeId });

			//戻り値は更新した件数
			return ret;

		} catch (Exception e) {
			//スタックトレース表示
			e.printStackTrace();
			
			return 0;
		}
	}

	//並べ替え処理
	@Override
	public List<CoffeeDto> sortCoffee(String sortVal) throws Exception {

		//SQL文
		String sql = "SELECT * FROM t_coffee";

		//switch式で条件分岐
		String sortedSQL = switch (sortVal) {
		//商品ID 昇順
		case "id_asc" -> sql + " ORDER BY coffee_id ASC";
		//商品ID 降順
		case "id_desc" -> sql + " ORDER BY coffee_id DESC";
		//価格 安い順
		case "price_asc" -> sql + " ORDER BY coffee_price ASC";
		//価格 高い順
		case "price_desc" -> sql + " ORDER BY coffee_price DESC";
		//在庫数 少ない順
		case "stock_asc" -> sql + " ORDER BY coffee_stock ASC";
		//在庫数 多い順
		case "stock_desc" -> sql + " ORDER BY coffee_stock DESC";
		//最終入荷日 古い順
		case "lastDate_asc" -> sql + " ORDER BY last_date ASC";
		//最終入荷日 新しい順
		case "lastDate_desc" -> sql + " ORDER BY last_date DESC";
		//上記以外(商品ID 昇順)
		default -> sql + " ORDER BY coffee_id ASC";
		};
		
		 //SQLを実行し、結果をCoffeeDtoのリストとして取得
		List<CoffeeDto> list = jdbcTemplate.query(
				sortedSQL,
				(ResultSet rs, int rowNum) -> {
					CoffeeDto coffeeDto = new CoffeeDto();
					coffeeDto.setCoffeeId(rs.getInt("coffee_id"));
					coffeeDto.setCoffeeName(rs.getString("coffee_name"));
					coffeeDto.setCoffeePrice(rs.getInt("coffee_price"));
					coffeeDto.setCoffeeStock(rs.getInt("coffee_stock"));
					//Date型をLocalDate型に変換してcoffeeDtoにセット
					coffeeDto.setLastDate(rs.getDate("last_date").toLocalDate());

					return coffeeDto;
				});
		//nullまたは空文字の時、nullを返す
		if (list == null || list.size() == 0) {
			return null;
		}
		return list;
	}

	//商品ID重複チェック
	@Override
	public boolean existsByCoffeeId(Integer coffeeId) throws Exception {

		//SQl実行分
		String SQL = "SELECT COUNT(*) FROM t_coffee WHERE coffee_id = ?";

		//第1引数: SQL文
		//第2引数: 戻り値の型
		//第3引数以降: SQLの「?」に入る値 (可変長引数なのでそのまま渡せる)
		//何らかの理由で戻り値にnullが返ってきてしまった場合intだと入らないので、Integerにしている。
		Integer ret = jdbcTemplate.queryForObject(SQL, Integer.class, coffeeId);

		//判定結果を返す
		return ret != null && ret > 0;
	}
}
