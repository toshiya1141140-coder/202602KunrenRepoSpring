package pack.coffee.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class CoffeeForm {
	
	//商品ID
	private Integer coffeeId;
	//銘柄名
	private String coffeeName;
	//価格
	private Integer coffeePrice;
	//在庫数量
	private Integer coffeeStock;
	//最終入荷日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastDate;
	//ラジオボタン
	private Integer radio;
	//並べ替え
	private String sort;
	
	//商品ID
	public Integer getCoffeeId() {
		return coffeeId;
	}
	public void setCoffeeId(Integer coffeeId) {
		this.coffeeId = coffeeId;
	}
	
	//銘柄名
	public String getCoffeeName() {
		return coffeeName;
	}
	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}
	
	//価格
	public Integer getCoffeePrice() {
		return coffeePrice;
	}
	public void setCoffeePrice(Integer coffeePrice) {
		this.coffeePrice = coffeePrice;
	}
	
	//在庫数
	public Integer getCoffeeStock() {
		return coffeeStock;
	}
	public void setCoffeeStock(Integer coffeeStock) {
		this.coffeeStock = coffeeStock;
	}
	
	//最終入荷日
	public LocalDate getLastDate() {
		return lastDate;
	}
	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}
	
	//ラジオボタン
	public Integer getRadio() {
		return radio;
	}
	public void setRadio(Integer radio) {
		this.radio = radio;
	}
	
	//ソート
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
