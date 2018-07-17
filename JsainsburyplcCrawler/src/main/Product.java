package main;
public class Product {
    private String title = null;
    private int caloriesPer100g = 0;
    private double unitPrice = 0;
    private String description = null;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public int getCalories() {
	return caloriesPer100g;
    }

    public void setCalories(int caloriesPer100g) {
	this.caloriesPer100g = caloriesPer100g;
    }

    public double getUnitPrice() {
	return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
	this.unitPrice = unitPrice;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
}
