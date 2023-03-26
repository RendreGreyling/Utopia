package controller;

import controller.util.Property;
import model.util.Coordinate;

public abstract class ServiceListener {

    protected final Property property;

    public ServiceListener(Property property) {
        this.property = property;
    }
    public abstract void update(Coordinate coordinate);
}
