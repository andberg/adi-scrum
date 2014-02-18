package se.adi.floggit.classes;

import java.util.List;

public final class Product
{
	private final int id;
	private final String name;
	private final String description;
	private final double cost;
	private final double rrp;
	private final List<String> categories;

	public Product(int id, String name, double rrp)
	{
		this.id = id;
		this.name = name;
		this.description = null;
		this.cost = 0;
		this.rrp = rrp;
		this.categories = null;
	}

	public Product(int id, String name, String description, double cost,
			double rrp, List<String> categories)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.rrp = rrp;
		this.categories = categories;
	}

	public Product(String name, String description, double cost, double rrp,
			List<String> categories)
	{
		this.id = 0;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.rrp = rrp;
		this.categories = categories;
	}

	@Override
	public String toString()
	{
		return "Product ID: " + getId() + "\nName: " + getName()
				+ "\nDescription: " + getDescription() + "\nCost: " + getCost()
				+ "\nRrp: " + getRrp() + "\nCategories: " + getCategories()
				+ "\n";
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public double getRrp()
	{
		return rrp;
	}

	public double getCost()
	{
		return cost;
	}

	public List<String> getCategories()
	{
		return categories;
	}
}