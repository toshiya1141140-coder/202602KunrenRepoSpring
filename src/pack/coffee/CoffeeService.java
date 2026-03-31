package pack.coffee;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.coffee.ICoffeeDao;
import dto.coffee.CoffeeDto;

/**
 * コーヒーサービスクラス
 */
@Service
public class CoffeeService {

	@Autowired
	private ICoffeeDao iCoffeeDao;
	
	//全件取得メソッド
	public List<CoffeeDto> getSearchAll() throws Exception {
		return iCoffeeDao.getSearchAll();
	}
	
	//検索条件に沿ったメソッド
	public List<CoffeeDto> selectByKey(Integer coffeeId, String strName) throws Exception {
		return iCoffeeDao.selectBykey(coffeeId, strName);
	}
	
	//登録メソッド
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public int insertCoffee(Integer coffeeId, String coffeeName, Integer coffeePrice, Integer coffeeStock, LocalDate lastDate) throws Exception {
		//商品ID重複チェック
		if (iCoffeeDao.existsByCoffeeId(coffeeId)) {
			throw new IllegalArgumentException("この商品IDは既に登録されています");
		}
		return iCoffeeDao.insertCoffee(coffeeId, coffeeName, coffeePrice, coffeeStock, lastDate);
	}
	
	//削除メソッド
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public int deleteCoffee(Integer radio) throws Exception {
		return iCoffeeDao.deleteCoffee(radio);
	}
	
	//更新初期表示メソッド
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public List<CoffeeDto> displayUpdate(Integer radio) throws Exception {
		return iCoffeeDao.displayUpdate(radio);
	}
	
	//更新メソッド
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public int updateCoffee(Integer coffeeId, String coffeeName, Integer coffeePrice, Integer coffeeStock, LocalDate lastDate) throws Exception {
		return iCoffeeDao.updateCoffee(coffeeId, coffeeName, coffeePrice, coffeeStock, lastDate);
	}
	
	//並べ替えメソッド
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public List<CoffeeDto> sort(String sort) throws Exception {
		return iCoffeeDao.sortCoffee(sort);
	}
}
