package Model;
import java.util.ArrayList;

public class Publisher {
private	String notification;
private ArrayList<Client> subscribers = new ArrayList<Client>();

public void addSubscriber(Client c) {
	this.subscribers.add(c);
}

public void unSubscribe(Client c) {
	this.subscribers.remove(c);
	
}

public void setRoomsPrices(String roomType,int price) {
	this.notification = roomType + " rent is for "+price+" per one night";
	this.notifySubscribers();
}

public void discountOnRooms(String roomType,int discount) {
	this.notification = "we're having a discount by "+discount+" percent on "+ roomType;
	this.notifySubscribers();
}

public void notifySubscribers() {
	for(Client c: this.subscribers)
		c.update(notification);
}

}
