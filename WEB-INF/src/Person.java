package onjava;
public class Person extends Object {

    /** Holds value of property name. */
    public String name;
    
    /** Holds value of property age. */
    public int age;

	public Address address;
    
    /** Creates new Name */
    public Person() {
    }

    /** Getter for property name.
     * @return Value of property name.
 */
    public String getName() {
        return name;
    }
    
    /** Setter for property name.
     * @param name New value of property name.
 */
    public void setName(String name) {
        this.name = name;
    }
    
    /** Getter for property age.
     * @return Value of property age.
 */
    public int getAge() {
        return age;
    }
    
    /** Setter for property age.
     * @param age New value of property age.
 */
    public void setAge(int age) {
        this.age = age;
    }

	public Address getAddressObj() {
		return address;
	}
	public void setAddressObj(Address add) {
		address = add;
	}
    
}
