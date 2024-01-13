module com.homework.quanlysinhvienfxml {
   requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires java.desktop;
    requires java.logging;

    opens com.homework.quanlysinhvienfxml to javafx.fxml;
    opens com.homework.doituong to javafx.fxml;
    opens com.homework.controller to javafx.fxml;
    
    exports com.homework.quanlysinhvienfxml;
    exports com.homework.doituong;
    exports com.homework.controller;
}
