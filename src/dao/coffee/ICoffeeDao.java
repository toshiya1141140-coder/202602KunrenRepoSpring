package dao.coffee;

import java.time.LocalDate;
import java.util.List;

import dto.coffee.CoffeeDto;

/**
 * CoffeeDaoインタフェースクラス
 */
public interface ICoffeeDao {
	//全件取得用抽象メソッド
	List<CoffeeDto> getSearchAll() throws Exception;

	//検索条件用抽象メソッド
	List<CoffeeDto> selectBykey(Integer coffeeId, String coffeeName) throws Exception;

	//登録用抽象メソッド
	int insertCoffee(Integer coffeeId, String coffeeName, Integer coffeePrice, Integer coffeeStock, LocalDate lastDate) throws Exception;
	
	//商品IDの重複チェック抽象メソッド
	boolean existsByCoffeeId(Integer coffeeId) throws Exception;

	//削除用抽象メソッド
	int deleteCoffee(Integer radio) throws Exception;

	//更新初期表示用抽象メソッド
	List<CoffeeDto> displayUpdate(Integer radio) throws Exception;

	//更新処理用抽象メソッド
	int updateCoffee(Integer coffeeId, String coffeeName, Integer coffeePrice, Integer coffeeStock, LocalDate lastDate) throws Exception;
	
	//並べ替え用抽象メソッド
	List<CoffeeDto> sortCoffee(String sort) throws Exception;	
}
