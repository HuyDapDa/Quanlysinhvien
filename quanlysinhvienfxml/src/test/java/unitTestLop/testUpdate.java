/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package unitTestLop;

import com.homework.doituong.Lop;
import com.homework.services.quanLyLop;
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
public class testUpdate {

    public testUpdate() {
    }
    private ObservableList<Lop> dsL = FXCollections.observableArrayList();

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
        quanLyLop qlL = new quanLyLop();
        qlL.fixLop("TESTER", "test2", "Nguyen Cao Danh");
        try {
            qlL.docDanhSachLop(dsL);
        } catch (SQLException ex) {
            Logger.getLogger(testUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Lop lo : this.dsL) {
            if (lo.getMaLop().equalsIgnoreCase("TESTER")) {
                assertEquals("test", lo.getTenLop());
            }
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
