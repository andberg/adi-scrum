package se.adi.floggit.classes;

import java.util.ArrayList;
import java.util.List;

public final class Product {

	private final int id;
	private final String name;
	private final String description;
	private final double rrp;
	private final double cost;
	private final List<String> categories;
	
	
	public Product(int id, String name, String description, double rrp, double cost, List<String> categories) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rrp = rrp;
		this.cost = cost;
		this.categories = categories;
		
	}
	
	public Product(String name, String description, double rrp, double cost, String... categories) {
	
		this.id = 0;
		this.name = name;
		this.description = description;
		this.rrp = rrp;
		this.cost = cost;
		this.categories = new ArrayList<String>();
		for(String category: categories)
		{
			this.categories.add(category);
		}
		
		
	}
	
	@Override
	public String toString() {
		
		String categoriesTemp = "";
		for(String category: this.categories){
			categoriesTemp = categoriesTemp + category + " ";
		}
		
		return "Product ID: " + id + 
				"\nName: " + name + 
				"\nDescription: " + description + 
				"\nRrp: " + rrp + 
				"\nCost: " + cost + 
				"\nCategories: " + categoriesTemp;
	}
	
}
