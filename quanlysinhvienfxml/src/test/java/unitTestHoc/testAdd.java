/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package unitTestHoc;

import com.homework.doituong.Hoc;
import com.homework.doituong.MonHoc;
import com.homework.doituong.sinhVien;
import com.homework.services.quanLyHoc;
import com.homework.services.quanLyMonHoc;
import com.homework.services.quanLySinhVien;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class testAdd {

    public testAdd() {
    }
    ObservableList<sinhVien> dsSV = FXCollections.observableArrayList();
    ObservableList<MonHoc> dsMH = FXCollections.observableArrayList();
    ObservableList<Hoc> ds = FXCollections.observableArrayList();

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAdd() throws SQLException {
        quanLyMonHoc qlMH = new quanLyMonHoc();
        quanLySinhVien qlSV = new quanLySinhVien();
        try {
            qlMH.docDanhSachMonHoc(dsMH);
            qlSV.docDanhSachSinhVien(dsSV);
        } catch (ParseException ex) { 
            Logger.getLogger(testAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        quanLyHoc qlHoc = new quanLyHoc();
        qlHoc.themSV_MH("POLI1234", "6", dsSV, dsMH);
        for (Hoc ho : this.ds) {
            if (ho.getMaMH().equalsIgnoreCase("POLI1234")) {
                assertEquals("6", ho.getMaSV());
            }
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
