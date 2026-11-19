package people;

public class Owner {

    private String name;

    private String lastname;

    private String uuid;

    public Owner(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }
    public Owner(String uuid, String name, String lastname) {
        this(name, lastname);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
