package people;

public class Owner {

    private String name;

    private String lastname;

    private String uuid;

    public Owner(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }

}
