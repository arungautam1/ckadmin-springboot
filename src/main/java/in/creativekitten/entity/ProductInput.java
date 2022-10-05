package in.creativekitten.entity;

public class ProductInput {

	private Integer id;
	private String title;
	private Double originalPrice;
	private Double discountedPrice;

	private String shortDescription;

	private String category;
	private String subcategory;

	private Double taxRate;
	private Integer stock;

	private Double shippingCost;
	private String deliveryTime;
	private String options;
	private String specifications;

	private String paragraph1;
	private String paragraph2;
	private String paragraph3;
	private String paragraph4;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getParagraph1() {
		return paragraph1;
	}

	public void setParagraph1(String paragraph1) {
		this.paragraph1 = paragraph1;
	}

	public String getParagraph2() {
		return paragraph2;
	}

	public void setParagraph2(String paragraph2) {
		this.paragraph2 = paragraph2;
	}

	public String getParagraph3() {
		return paragraph3;
	}

	public void setParagraph3(String paragraph3) {
		this.paragraph3 = paragraph3;
	}

	public String getParagraph4() {
		return paragraph4;
	}

	public void setParagraph4(String paragraph4) {
		this.paragraph4 = paragraph4;
	}

	@Override
	public String toString() {
		return "ProductInput [id=" + id + ", title=" + title + ", originalPrice=" + originalPrice + ", discountedPrice="
				+ discountedPrice + ", shortDescription=" + shortDescription + ", category=" + category
				+ ", subcategory=" + subcategory + ", taxRate=" + taxRate + ", stock=" + stock + ", shippingCost="
				+ shippingCost + ", deliveryTime=" + deliveryTime + ", options=" + options + ", specifications="
				+ specifications + ", paragraph1=" + paragraph1 + ", paragraph2=" + paragraph2 + ", paragraph3="
				+ paragraph3 + ", paragraph4=" + paragraph4 + "]";
	}

}
