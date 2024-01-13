/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package unitTestLop;

import com.homework.doituong.Lop;
import com.homework.doituong.MonHoc;
import com.homework.services.quanLyLop;
import java.sql.SQLException;
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

    public ObservableList<Lop> dsL = FXCollections.observableArrayList();

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
    public void testAdd() throws SQLException {
        quanLyLop qlL = new quanLyLop();
        qlL.docDanhSachLop(dsL);
        qlL.themLop("TEST001", "Lop TEST", "", dsL);
        for (Lop lo : this.dsL) {
            if (lo.getMaLop().equalsIgnoreCase("TEST001")) {
                assertEquals("Lop TEST", lo.getTenLop());
            }
        }
        // TODO add test methods here.
        // The methods must be annotated with annotation @Test. For example:
        //
        // @Test
        // public void hello() {}
    }
}
