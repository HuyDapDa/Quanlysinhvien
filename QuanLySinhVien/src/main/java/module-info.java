module com.homework.quanlysinhvien {
//    requires javafx.controls;
//    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires java.desktop;
    requires java.logging;
    opens com.homework.quanlysinhvien to javafx.fxml;
    exports com.homework.quanlysinhvien;
}
