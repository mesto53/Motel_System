package Model;

public class BritshFood extends Meal{
	
	public BritshFood() {
		this.price = 70;
	}
	
	
	public void createMeal() {
		System.out.println("Your Meal will consist of Bacon sandwich for breakfast, Steak and Kidney Pie for lunch,"
				+ " Fish and Chips for dinner. Enjoy your evening with your Pimm's Cup");
	}
}
