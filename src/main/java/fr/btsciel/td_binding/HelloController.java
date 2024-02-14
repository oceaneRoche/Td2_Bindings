package fr.btsciel.td_binding;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public TextField perimetre;
    public TextField surface;
    public TextField hauteur;
    public TextField largeur;
    public Slider sliderHauteur;
    public Slider sliderLargeur;
    public Rectangle rectangle;
    public Circle cercle;
    public Slider sliderCercle;
    public TextField rayon;
    StringConverter sc = new DoubleStringConverter() {
        @Override
        public Double fromString(String value) {
            value = value.replace(",", ".");
            value = value.replace("m","").trim();
            BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }

        @Override
        public String toString(Double value) {
            DecimalFormat df = new DecimalFormat("#.00 m");
            return df.format(value);
        }
    };
    RectangleProperty monrectangle = new RectangleProperty();
    CercleProperty monCercle = new CercleProperty();
    Double SEUIL_P = 1500.0, SEUIL_S = 5000.0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Calcul pour le rectangle
        Bindings.bindBidirectional(hauteur.textProperty(),monrectangle.hProperty(),sc);
        Bindings.bindBidirectional(largeur.textProperty(),monrectangle.lProperty(),sc);
        perimetre.textProperty().bind(monrectangle.pProperty().asString("%.2f m"));
        surface.textProperty().bind(monrectangle.sProperty().asString("%.2f m²"));
        Bindings.bindBidirectional(hauteur.textProperty(),sliderHauteur.valueProperty(),sc);
        sliderHauteur.visibleProperty().bind(Bindings.when(monrectangle.hProperty().greaterThan(100))
                .then(false).otherwise(true));
        Bindings.bindBidirectional(largeur.textProperty(),sliderLargeur.valueProperty(),sc);
        sliderLargeur.visibleProperty().bind(Bindings.when(monrectangle.lProperty().greaterThan(100))
                .then(false).otherwise(true));
        perimetre.backgroundProperty().bind(
                Bindings.when(monrectangle.pProperty().greaterThan(SEUIL_P))
                        .then(new Background(new BackgroundFill(Color.RED, null, null)))
                        .otherwise(new Background(new BackgroundFill(Color.AQUA, null, null)))
        );
        surface.backgroundProperty().bind(
                Bindings.when(monrectangle.sProperty().greaterThan(SEUIL_S))
                        .then(new Background(new BackgroundFill(Color.RED, null, null)))
                        .otherwise(new Background(new BackgroundFill(Color.AQUA, null, null)))
        );
        rectangle.heightProperty().bind(monrectangle.hProperty());
        rectangle.widthProperty().bind(monrectangle.lProperty());

        //Calcul pour le Cercle
        Bindings.bindBidirectional(rayon.textProperty(),monCercle.rayonProperty(),sc);
        perimetre.textProperty().bind(monCercle.perimetreProperty().asString("%.2f m"));
        surface.textProperty().bind(monCercle.surfaceProperty().asString("%.2f m²"));
        sliderCercle.visibleProperty().bind(Bindings.when(monCercle.rayonProperty().greaterThan(100))
                .then(false).otherwise(true));
        Bindings.bindBidirectional(rayon.textProperty(),sliderCercle.valueProperty(),sc);
        perimetre.backgroundProperty().bind(
                Bindings.when(monCercle.perimetreProperty().greaterThan(SEUIL_P))
                        .then(new Background(new BackgroundFill(Color.RED, null, null)))
                        .otherwise(new Background(new BackgroundFill(Color.AQUA, null, null)))
        );
        surface.backgroundProperty().bind(
                Bindings.when(monCercle.surfaceProperty().greaterThan(SEUIL_S))
                        .then(new Background(new BackgroundFill(Color.RED, null, null)))
                        .otherwise(new Background(new BackgroundFill(Color.AQUA, null, null)))
        );
        cercle.radiusProperty().bind(monCercle.rayonProperty());
    }
}