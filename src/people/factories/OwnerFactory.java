package people.factories;

import people.Owner;

import java.util.UUID;

public class OwnerFactory {

    public Owner createOwner(String name, String lastname) {
        String uuid = UUID.randomUUID().toString();

        return new Owner(uuid, name, lastname);
    }
}
