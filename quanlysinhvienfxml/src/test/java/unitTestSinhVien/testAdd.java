/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package unitTestSinhVien;

import com.homework.doituong.sinhVien;
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

     ObservableList<sinhVien> dsSV = FXCollections.observableArrayList();

    public testAdd() {
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
    public void testAddSV() throws SQLException {
        quanLySinhVien qlSV = new quanLySinhVien();
         try {
             qlSV.docDanhSachSinhVien(dsSV);
         } catch (ParseException ex) {
             Logger.getLogger(testAdd.class.getName()).log(Level.SEVERE, null, ex);
         }
        qlSV.themSinhVien("Nguyen Van ", "A", "Nam", "1990-01-01", "Binh Dinh", "CS2001");
        for (sinhVien sv : this.dsSV) {
            if (sv.getMaSV()==11) {
                assertEquals("Nguyen Van A", sv.getHoSV()+" "+sv.getTenSV());
            }
        }
    }

}

// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}

