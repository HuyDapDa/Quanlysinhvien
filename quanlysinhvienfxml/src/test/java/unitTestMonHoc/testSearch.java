/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package unitTestMonHoc;

import com.homework.doituong.MonHoc;
import com.homework.doituong.sinhVien;
import com.homework.services.quanLyMonHoc;
import java.sql.SQLException;
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
public class testSearch {

    ObservableList<MonHoc> dsMH = FXCollections.observableArrayList();

    public testSearch() {
    }

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
    public void TestSearchTrue() {
        try {
            quanLyMonHoc qlMH = new quanLyMonHoc();
            qlMH.docDanhSachMonHoc(dsMH);
        } catch (SQLException ex) {
            Logger.getLogger(testSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (MonHoc mh : this.dsMH) {
            if (mh.getMaMH().equalsIgnoreCase("ITEC0702")) {
                assertEquals("He thong tin quan ly", mh.getTenMH());
            }
        }
    }

    @Test
    public void TestSearchFalse() {
        try {
            quanLyMonHoc qlMH = new quanLyMonHoc();
            qlMH.docDanhSachMonHoc(dsMH);
        } catch (SQLException ex) {
            Logger.getLogger(testSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (MonHoc mh : this.dsMH) {
            if (mh.getMaMH().equalsIgnoreCase("ITEC0702")) {
                assertEquals("mang may tinh", mh.getTenMH());
            }
        }
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
