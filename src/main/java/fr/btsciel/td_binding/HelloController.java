package fr.btsciel.td_binding;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public TextField perimetre;
    public TextField surface;
    public TextField hauteur;
    public TextField largeur;
    public Slider sliderHauteur;
    public Slider sliderLargeur;
    DoubleProperty p = new SimpleDoubleProperty();
    DoubleProperty s = new SimpleDoubleProperty();
    DoubleProperty h = new SimpleDoubleProperty();
    DoubleProperty l = new SimpleDoubleProperty();
    StringConverter sc = new DoubleStringConverter();
    Double SEUIL_P = 1500.0, SEUIL_S = 5000.0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        p.bind((l.add(h)).multiply(2));
        s.bind(l.multiply(h));
        Bindings.bindBidirectional(hauteur.textProperty(),h,sc);
        Bindings.bindBidirectional(largeur.textProperty(),l,sc);
        perimetre.textProperty().bind(p.asString());
        surface.textProperty().bind(s.asString());
        Bindings.bindBidirectional(hauteur.textProperty(),sliderHauteur.valueProperty(),sc);
        sliderHauteur.visibleProperty().bind(Bindings.when(h.greaterThan(100))
                .then(false).otherwise(true));
        Bindings.bindBidirectional(largeur.textProperty(),sliderLargeur.valueProperty(),sc);
        sliderLargeur.visibleProperty().bind(Bindings.when(l.greaterThan(100))
                .then(false).otherwise(true));
        perimetre.backgroundProperty().bind(
                Bindings.when(p.greaterThan(SEUIL_P))
                        .then(new Background(new BackgroundFill(Color.RED, null, null)))
                        .otherwise(new Background(new BackgroundFill(Color.AQUA, null, null)))
        );
        surface.backgroundProperty().bind(
                Bindings.when(s.greaterThan(SEUIL_S))
                        .then(new Background(new BackgroundFill(Color.RED, null, null)))
                        .otherwise(new Background(new BackgroundFill(Color.AQUA, null, null)))
        );
    }
}