package com.example.filmutleie2;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

class Film{
    public String tittel;
    public double utLeiePris;
    public Person leidAv;

    // konstruktør inn her
    public Film(String tittel, double utLeiePris, Person leidAv) {
        this.tittel = tittel;
        this.utLeiePris = utLeiePris;
        this.leidAv = leidAv;
    }

}

class Person{
    public String navn;
    public String telefonnr;

    // konstruktør inn her
    public Person(String navn, String telefonnr) {
        this.navn = navn;
        this.telefonnr = telefonnr;
    }
}

class FilmUtleie{

    // instansier arrayet her
    private List<Film> filmer;

    public FilmUtleie() {
        // legg inn filmene som skal være til utleie her
        // to filmer holder
        filmer = new ArrayList<>();
        filmer.add(new Film("Star Wars", 29.90, null));
        filmer.add(new Film("Harry Potter", 19.90, null));
        filmer.add(new Film("Matrix", 39.90, null));

    }

    public String leiUt(String navn, String telefonnr, String tittel){
        // sjekk om filmen finnes og at den ikke er leid ut (at leidAv er null)
        // returner meldinger om det var vellykket utleie
        // om ikke filmen fantes
        // eller om filmen fantes men den var utleid'
        for(Film film : filmer) {
            if(film.tittel.equals(tittel)) {
                if(film.leidAv == null) {
                    film.leidAv = new Person(navn, telefonnr);
                    return tittel + " er vellykket leid ut!";
                }
                return tittel + " er leid ut fra før, dessverre!";
            }
        }
        return "Filmen finnes dessverre ikke.";
    }

    public String leverInn(String tittel){
        // sjekk om filmen finnes og at den er utleid (at det er et personobjekt på leidAv)
        // dersom dette nullstill person-pekeren i filmen og returner at den ble levert
        // returner feilmelding dersom filmen ikke finnes
        // returner også feilmelding dersom filmen finnes med ikke er utleid
        for(Film film : filmer) {
            if(film.tittel.equals(tittel)) {
                if(film.leidAv != null) {
                    film.leidAv = null;
                    return "Filmen er levert!";
                }
                return "Filmen er ikke utleid, så man ikke levere den inn.";
            }
        }
        return "Filmen finnes ikke";
    }

    @Override
    public String toString(){
        String ut = "";
        for(Film film : filmer) {
            ut += "Tittel: " + film.tittel + "\n" +
                    "Pris: " + film.utLeiePris + "\n" +
                    "Utleid: " + (film.leidAv == null ? "Nei" : film.leidAv.navn) + "\n";
            ut += "\n";
        }
        return ut;
    }
}


public class FXMLDocumentController {

    // instansier Filmutleien her
    private FilmUtleie filmUtleie = new FilmUtleie();

    @FXML
    private TextField txtKundeNavn;

    @FXML
    private TextField txtKundeTelefon;

    @FXML
    private TextField txtFilmTittel;

    @FXML
    private Label lblOversikt;


    @FXML
    void leiUt(ActionEvent event) {
        // kall leiUt og legg resultatet i oversikts-labelen
        String resultat = filmUtleie.leiUt(txtKundeNavn.getText(), txtKundeTelefon.getText(), txtFilmTittel.getText());
        lblOversikt.setText(resultat);
    }

    @FXML
    void leverInn(ActionEvent event) {
        // kall lever inn og legg resultatet i oversikts-labelen
        String resultat = filmUtleie.leverInn(txtFilmTittel.getText());
        lblOversikt.setText(resultat);
    }

    @FXML
    void visUtleie(ActionEvent event) {
        // kall toString metoden og legg resultatet i oversikts-labelen
        lblOversikt.setText(filmUtleie.toString());
    }
}