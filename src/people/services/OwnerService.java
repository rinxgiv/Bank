package people.services;

import people.Owner;
import people.factories.OwnerFactory;

import java.util.LinkedList;
import java.util.List;

public class OwnerService {

    private final OwnerFactory ownerFactory;

    private final List<Owner> owners = new LinkedList<>();

    public OwnerService(OwnerFactory ownerFactory) {
        this.ownerFactory = ownerFactory;
    }

    public Owner createOwner(String name, String lastname) {
        Owner owner = ownerFactory.createOwner(name, lastname);
        owners.add(owner);

        return owner;
    }

    public List<Owner> getOwners() {
        return owners;
    }
}
