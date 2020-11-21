package database;

import com.mysql.cj.conf.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Account 
{
	private SimpleIntegerProperty income;
	private SimpleIntegerProperty expenditure;
	private SimpleStringProperty name;
	private SimpleStringProperty  category;

	public int getIncome() 
	{
		return income.get();
	}
	public void setIncome(int income) 
	{
		this.income = new SimpleIntegerProperty(income);
	}
	public int getExpenditure() 
	{
		return expenditure.get();
	}
	public void setExpenditure(int expenditure) 
	{
		this.expenditure = new SimpleIntegerProperty(expenditure);
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	public String getCategory() {
		return category.get();
	}
	public void setCategory(String category) {
		this.category = new SimpleStringProperty(category);
	}
	public int getPrice()
	{
		if(this.income.get()==0)
		{
			return this.expenditure.get();
		}
		else
			return this.income.get();
	}
}
