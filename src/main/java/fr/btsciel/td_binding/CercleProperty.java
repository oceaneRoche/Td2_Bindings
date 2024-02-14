package fr.btsciel.td_binding;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CercleProperty {
    private DoubleProperty rayon = new SimpleDoubleProperty();
    private DoubleProperty surface = new SimpleDoubleProperty();
    private DoubleProperty perimetre = new SimpleDoubleProperty();


    public DoubleProperty rayonProperty() {
        return rayon;
    }
    public DoubleProperty perimetreProperty() {
        perimetre.bind((rayon.multiply(Math.PI)).multiply(2));
        return perimetre;
    }
    public DoubleProperty surfaceProperty() {
        surface.bind((rayon.multiply(rayon)).multiply(Math.PI));
        return surface;
    }
}
