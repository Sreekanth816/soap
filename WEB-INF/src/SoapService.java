package onjava;

public class SoapService extends Object {

    /** Creates new SoapService */
    public SoapService() {
    }
    
    /** the remote method accepting a Person object
    */
    public String sayGreeting(Person name) {
        return "Hello dear "+name.getName()+" you are really "+name.getAge() + " and your address : " + name.getAddressObj().getAddress();
    }
}
