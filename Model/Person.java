package Model;

public abstract class Person {
private String name,email;
public Person(String n,String em) {
	this.name = n;
	this.email = em;
}
public String getName() {
	return this.name;
}

public String getEmail() {
	return this.email;
}

public String toString() {
	return this.name + " "+this.email;
}
}
