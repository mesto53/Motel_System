package Model;

public class ItalianFood extends Meal{
	
	public ItalianFood() {
		this.price = 150;
	}
	
     public void createMeal() {
		System.out.println("Your Meal will consist of Cheese and wine for breakfast, Pizza for lunch,"
				+ " Pasta for dinner. Enjoy your evening with your Tequila");
	}
}
