package com.group40;

public class CostsTracker extends Tracker {

	private final double hourlyWage;

	public CostsTracker(double rent, double amenities, double groceries, double hourlyWage)
	{
		super("Costs Tracker", "How many hours are needed to cover your expenses?", rent, amenities, groceries);
		this.hourlyWage = hourlyWage;
	}

	@Override
	public double getResult()
	{
		return getExpenses() / hourlyWage;
	}
}
