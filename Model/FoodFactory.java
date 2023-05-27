package Model;

public class FoodFactory {
     public static Meal createKitchen(String Kitchen) {
		
		if (Kitchen == null || Kitchen.isEmpty())
            return null;
		else if("Italian".equals(Kitchen))
			return new ItalianFood();
		else if("British".equals(Kitchen))
			return new BritshFood();
		
		return null;
	}
}
