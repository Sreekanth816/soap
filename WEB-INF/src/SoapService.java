package onjava;

public class SoapService extends Object {

	/** Creates new SoapService */
	public SoapService() {
	}

	/**
	 * the remote method accepting a Person object
	 */
	public int getSalary(Person name) {
		System.out.println("Hello dear " + name.getName() + " you are really " + name.getAge() + " and your address : "
				+ name.getAddress().getAddress());
		return 5467;
	}
	
	public float getTaxAmount(int salary) {
		System.out.println("Salary: " + salary);
		return salary / 123;
	}
}
