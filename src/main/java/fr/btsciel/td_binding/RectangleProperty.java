package fr.btsciel.td_binding;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class RectangleProperty {
    private DoubleProperty h = new SimpleDoubleProperty();
    private DoubleProperty l = new SimpleDoubleProperty();
    private DoubleProperty p = new SimpleDoubleProperty();
    private DoubleProperty s = new SimpleDoubleProperty();

    public DoubleProperty hProperty() {
        return h;
    }
    public DoubleProperty lProperty() {
        return l;
    }
    public DoubleProperty pProperty() {
        p.bind((l.add(h)).multiply(2));
        return p;
    }
    public DoubleProperty sProperty() {
        s.bind(l.multiply(h));
        return s;
    }
}
