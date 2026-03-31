package dto.coffee;

import java.time.LocalDate;

public class CoffeeDto {

	//商品ID
	private Integer coffeeId;
	//銘柄名
	private String coffeeName;
	//価格
	private Integer coffeePrice;
	//在庫数量
	private Integer coffeeStock;
	//最終入荷日
	private LocalDate lastDate;

	
	public CoffeeDto() {
		
	}
	
	//コンストラクタ
	public CoffeeDto(Integer coffeeId, String coffeeName, Integer coffeePrice, Integer coffeeStock, LocalDate lastDate) {
		this.coffeeId = coffeeId;
		this.coffeeName = coffeeName;
		this.coffeePrice = coffeePrice;
		this.coffeeStock = coffeeStock;
		this.lastDate = lastDate;

	}
	
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
}
