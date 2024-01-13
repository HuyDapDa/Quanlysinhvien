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
public class testUpdate {

    private ObservableList<sinhVien> dsSV = FXCollections.observableArrayList();

    public testUpdate() {
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
    public void testUpdate() {

        quanLySinhVien qlSV = new quanLySinhVien();
        qlSV.updateSV(15, "Nguyen", "Khuyen", "Nam", "2002-09-09", "Tp.HCM", "CS2001");
        try {
            qlSV.docDanhSachSinhVien(dsSV);
        } catch (SQLException ex) {
            Logger.getLogger(testUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(testUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (sinhVien sv : this.dsSV) {
            if (sv.getMaSV() == 15) {
                assertEquals("Huynh Hiep Thien", sv.getHoSV() + " " + sv.getTenSV());
            }
        }

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
