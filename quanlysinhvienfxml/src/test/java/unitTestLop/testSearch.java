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
public class testSearch {
    private ObservableList<Lop> dsL = FXCollections.observableArrayList();
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
    public void testSearchTenLopTrue() throws SQLException
    {
        quanLyLop qlL= new quanLyLop();
        qlL.docDanhSachLop(dsL);
        
        for( Lop lo: this.dsL)
            if(lo.getMaLop().equalsIgnoreCase("CS2001"))
            {
                assertEquals("Khoa hoc may tinh", lo.getTenLop());
            }
    }
    @Test
    public void testSearchTenLopFalse() throws SQLException
    {
        quanLyLop qlL= new quanLyLop();
        qlL.docDanhSachLop(dsL);
        
        for( Lop lo: this.dsL)
            if(lo.getMaLop().equalsIgnoreCase("CS2001"))
            {
                assertEquals("He thong thong tin", lo.getTenLop());
            }
    }
    @Test
     public void testSearchGVCNTrue() throws SQLException
    {
        quanLyLop qlL= new quanLyLop();
        qlL.docDanhSachLop(dsL);
        
        for( Lop lo: this.dsL)
            if(lo.getMaLop().equalsIgnoreCase("CS2001"))
            {
                assertEquals("Nguyen Van A", lo.getGiaoVien());
            }
    }
     @Test
      public void testSearchGVCNFalse() throws SQLException
    {
        quanLyLop qlL= new quanLyLop();
        qlL.docDanhSachLop(dsL);
        
        for( Lop lo: this.dsL)
            if(lo.getMaLop().equalsIgnoreCase("CS2001"))
            {
                assertEquals("Nguyen Van B", lo.getGiaoVien());
            }
    }
        @Test
      public void testSearchMaLopTrue() throws SQLException
    {
        quanLyLop qlL= new quanLyLop();
        qlL.docDanhSachLop(dsL);
        
        for( Lop lo: this.dsL)
            if(lo.getGiaoVien().equalsIgnoreCase("Nguyen Van A"))
            {
                assertEquals("CS2001", lo.getMaLop());
            }
    }
        @Test
      public void testSearchMaLopFalse() throws SQLException
    {
        quanLyLop qlL= new quanLyLop();
        qlL.docDanhSachLop(dsL);
        
        for( Lop lo: this.dsL)
            if(lo.getGiaoVien().equalsIgnoreCase("Nguyen Van A"))
            {
                assertEquals("CS2002", lo.getMaLop());
            }
    }
       public void testSearchGV_TenMon() throws SQLException
    {
        quanLyLop qlL= new quanLyLop();
        qlL.docDanhSachLop(dsL);
        
        for( Lop lo: this.dsL)
            if(lo.getGiaoVien().equalsIgnoreCase("Nguyen Van A"))
            {
                assertEquals("Khoa hoc may tinh", lo.getTenLop());
            }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
