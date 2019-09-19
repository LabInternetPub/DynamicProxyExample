import java.util.HashMap;

public class Main {
    HashMap<String, PersonBean> datingDB = new HashMap<String, PersonBean>();

    public static void main(String[] args) {
        Main test = new Main();
        test.drive();
    }

    public Main() {
        initializeDatabase();
    }

    public void drive() {
        PersonBean joe = getPersonFromDatabase("Joe Javabean");

        PersonBean ownerProxy = PersonBeanProxyFactory.getOwnerProxy(joe);
        System.out.println("Name is " + ownerProxy.getName());
        ownerProxy.setInterests("bowling, Go");
        System.out.println("Interests set from owner proxy: " + ownerProxy.getInterests());
        try {
            ownerProxy.setHotOrNotRating(10);
        } catch (Exception e) {
            System.out.println("Can't set rating from owner proxy");
        }
        System.out.println("Rating is " + ownerProxy.getHotOrNotRating());

        PersonBean nonOwnerProxy = PersonBeanProxyFactory.getNonOwnerProxy(joe);
        System.out.println("Name is " + nonOwnerProxy.getName());
        try {
            nonOwnerProxy.setInterests("bowling, Go");
        } catch (Exception e) {
            System.out.println("Can't set interests from non owner proxy");
        }
        nonOwnerProxy.setHotOrNotRating(3);
        System.out.println("Rating set from non owner proxy");
        System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
        System.out.println("Interests from non owner proxy: " + ownerProxy.getInterests());
    }


    PersonBean getPersonFromDatabase(String name) {
        return (PersonBean)datingDB.get(name);
    }

    void initializeDatabase() {
        PersonBean joe = new PersonBeanImpl();
        joe.setName("Joe Javabean");
        joe.setInterests("cars, computers, music");
        joe.setHotOrNotRating(7);
        datingDB.put(joe.getName(), joe);

        PersonBean kelly = new PersonBeanImpl();
        kelly.setName("Kelly Klosure");
        kelly.setInterests("ebay, movies, music");
        kelly.setHotOrNotRating(6);
        datingDB.put(kelly.getName(), kelly);
    }
}

