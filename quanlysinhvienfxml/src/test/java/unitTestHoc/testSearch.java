/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package unitTestHoc;

import com.homework.doituong.Hoc;
import com.homework.services.quanLyHoc;
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
public class testSearch {

    ObservableList<Hoc> ds = FXCollections.observableArrayList();

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
    public void testSearchDiemTrue() throws SQLException {
        quanLyHoc qLH = new quanLyHoc();
        try {
            qLH.docDanhSachHoc(ds);
        } catch (ParseException ex) {
            Logger.getLogger(testSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Hoc ho : this.ds) {
            if (ho.getMaMH().equalsIgnoreCase("ITEC4415") && ho.getMaSV() == 10) {
                assertEquals(0.0, ho.getDiem());
            }
        }
    }
    @Test
    public void testSearchDiemFalse() throws SQLException {
        quanLyHoc qLH = new quanLyHoc();
        try {
            qLH.docDanhSachHoc(ds);
        } catch (ParseException ex) {
            Logger.getLogger(testSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Hoc ho : this.ds) {
            if (ho.getMaMH().equalsIgnoreCase("ITEC4415") && ho.getMaSV() == 10) {
                assertEquals(8.0, ho.getDiem());
            }
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
