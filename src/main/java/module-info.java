module com.example.filmutleie2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filmutleie2 to javafx.fxml;
    exports com.example.filmutleie2;
}