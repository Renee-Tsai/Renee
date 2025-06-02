package EK;

import java.io.Serializable;

public class Quotation implements Serializable {
	private Integer number;
	private String useway;
	private String brand;
	private String size;
	private String fr;
	private String loadAndSpeed;
	private Integer price;
	private String note;
	public Quotation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Quotation(Integer number, String useway, String brand, String size, String fr, String loadAndSpeed,
			Integer price, String note) {
		super();
		this.number = number;
		this.useway = useway;
		this.brand = brand;
		this.size = size;
		this.fr = fr;
		this.loadAndSpeed = loadAndSpeed;
		this.price = price;
		this.note = note;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getUseway() {
		return useway;
	}
	public void setUseway(String useway) {
		this.useway = useway;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFr() {
		return fr;
	}
	public void setFr(String fr) {
		this.fr = fr;
	}
	public String getLoadAndSpeed() {
		return loadAndSpeed;
	}
	public void setLoadAndSpeed(String loadAndSpeed) {
		this.loadAndSpeed = loadAndSpeed;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Quotation [number=" + number + ", useway=" + useway + ", brand=" + brand + ", size=" + size + ", fr="
				+ fr + ", loadAndSpeed=" + loadAndSpeed + ", price=" + price + ", note=" + note + "]";
	}
	
	
}
