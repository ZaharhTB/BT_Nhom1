/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import DB.DBManager;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ktkun
 */
public class HoaDon extends javax.swing.JFrame {
    private String sql;
    private Map<String, String> spMap = new HashMap<>();
    private Map<String, String> khMap = new HashMap<>();
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form HoaDon
     */
    public HoaDon() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        load();
        loadcb();
        updateCB();
        clickAddText();  
    }
    private void load(){
        sql="select hdx.ma_hdx,KhachHang.ma_kh,KhachHang.ten_kh,SanPham.ma_sp,SanPham.ten_sp,hdx.ngay_lp,hdx.sl,hdx.dg,(hdx.sl*hdx.dg) from hdx inner join SanPham on hdx.ma_sp=SanPham.ma_sp inner join KhachHang on hdx.ma_kh=KhachHang.ma_kh";
        DBManager.Table(j, sql);
        tong();
        clickAddText();
    }
    private void tong(){
        TableModel ml= j.getModel();
        long sum=0;
        for(int row=0; row<ml.getRowCount(); row++){
            long sl = Integer.parseInt(ml.getValueAt(row, 6).toString());
            long dg = Integer.parseInt(ml.getValueAt(row, 7).toString());
            sum+=sl*dg;
        }
        txt_sum.setText(String.valueOf(sum));
    }
    private void clickAddText(){

            j.getSelectionModel().addListSelectionListener(event ->
                    {
                        if(!event.getValueIsAdjusting() && j.getSelectedRow() != -1){
                            int row=j.getSelectedRow();
                        txt_sp.setText(j.getValueAt(row, 0).toString());
                        cb_mkh.setSelectedItem(j.getValueAt(row,1).toString());                       
                        cb_tkh.setSelectedItem(j.getValueAt(row,2).toString());
                        cb_msp.setSelectedItem(j.getValueAt(row,3).toString());                       
                        cb_tsp.setSelectedItem(j.getValueAt(row,4).toString());                       
                        String dateString = j.getValueAt(row, 5).toString();
                        try{
                            SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = dbFormat.parse(dateString);               
                            // Thiết lập giá trị cho DateChooserCombo
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);

                            dcc_ngaynhap.setSelectedDate(cal);
                        }catch(ParseException ex){
                            ex.printStackTrace();
                        }      
                        s_sl.setValue(Integer.parseInt(j.getValueAt(row, 6).toString()));   
                        txt_dg.setText(j.getValueAt(row, 7).toString());
                        txt_vt.setText(String.valueOf(row+1));
                        }
                    }
            );
    }
    private void loadcb(){
        try{
        sql="select ma_sp,ten_sp from SanPham";
        ResultSet rs = DBManager.GetData(sql);
        
        sql="select ma_kh,ten_kh from KhachHang";
        ResultSet rs1 = DBManager.GetData(sql);
        
        cb_msp.removeAllItems();
        cb_tsp.removeAllItems();
        spMap.clear();

        cb_mkh.removeAllItems();
        cb_tkh.removeAllItems();
        khMap.clear();
        
        while(rs.next()){            
            String ma=rs.getString("ma_sp");
            String ten=rs.getString("ten_sp");
            cb_msp.addItem(ma);
            cb_tsp.addItem(ten);     
            spMap.put(ma, ten);     
        }
        
        while(rs1.next()){                     
            String makh=rs1.getString("ma_kh");
            String tenkh=rs1.getString("ten_kh");
            
            cb_mkh.addItem(makh);
            cb_tkh.addItem(tenkh);     
            khMap.put(makh, tenkh);           
        }
        rs.close();
        rs1.close();
        }catch(SQLException ex){
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private int getDG(String ma){
        int dg=0;   
        try{
         sql="select dg from hdn where ma_sp='"+ma+"'";
        ResultSet rs =DBManager.GetData(sql);              
        while(rs.next())
        {           
            dg = rs.getInt("dg");
        }
        }catch(SQLException ex){
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }      
        return dg;
    }
private void updateCB() {
    cb_msp.addActionListener(e -> {
        String Masp = (String) cb_msp.getSelectedItem();
        if (Masp != null) {
            cb_tsp.setSelectedItem(spMap.get(Masp));
        }
    });
    
    cb_tsp.addActionListener(e -> {
        String Tensp = (String) cb_tsp.getSelectedItem();
        if (Tensp != null) {
            for (Map.Entry<String, String> entry : spMap.entrySet()) {
                if (entry.getValue().equals(Tensp)) {
                    cb_msp.setSelectedItem(entry.getKey());
                    break;
                }
            }
        }
    });
    
    cb_mkh.addActionListener(e -> {
        String Makh = (String) cb_mkh.getSelectedItem();
        if (Makh != null) {
            cb_tkh.setSelectedItem(khMap.get(Makh));
        }
    });
    
    cb_tkh.addActionListener(e -> {
        String Tenkh = (String) cb_tkh.getSelectedItem();
        if (Tenkh != null) {
            for (Map.Entry<String, String> entry : khMap.entrySet()) {
                if (entry.getValue().equals(Tenkh)) {
                    cb_mkh.setSelectedItem(entry.getKey());
                    break;
                }
            }
        }
    });    
}

    
    private void addText(int row){
        if(row>=0 && row < j.getRowCount()){
                {
                        txt_sp.setText(j.getValueAt(row, 0).toString());
                        cb_mkh.setSelectedItem(j.getValueAt(row,1).toString());                       
                        cb_tkh.setSelectedItem(j.getValueAt(row,2).toString());
                        cb_msp.setSelectedItem(j.getValueAt(row,3).toString());                       
                        cb_tsp.setSelectedItem(j.getValueAt(row,4).toString());                       
                        String dateString = j.getValueAt(row, 5).toString();
                        try{
                            SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = dbFormat.parse(dateString);               
                            // Thiết lập giá trị cho DateChooserCombo
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);

                            dcc_ngaynhap.setSelectedDate(cal);
                        }catch(ParseException ex){
                            ex.printStackTrace();
                        }      
                        s_sl.setValue(Integer.parseInt(j.getValueAt(row, 6).toString()));   
                        txt_dg.setText(j.getValueAt(row, 7).toString());
                        txt_vt.setText(String.valueOf(row+1));
                }          
        }
    }
    private void clear()
    {
        Random rd = new Random();
        String msp="HDX"+rd.nextInt(500);
        txt_sp.setText(msp);
        txt_dg.setText(String.valueOf(getDG(cb_msp.getSelectedItem().toString())));
        s_sl.setValue(1);
    }
    private int getSL(String masp){
        sql="select sl from hdn where ma_sp='"+masp+"'";
        int sl =0;
        try{
            ResultSet rs = DBManager.GetData(sql);
            while(rs.next()){
                sl=rs.getInt("sl");
            }
        }catch(SQLException ex){
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_sp = new javax.swing.JTextField();
        txt_dg = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_dau = new javax.swing.JButton();
        btn_truoc = new javax.swing.JButton();
        btn_cuoi = new javax.swing.JButton();
        btn_sau = new javax.swing.JButton();
        txt_vt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cb_msp = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cb_tsp = new javax.swing.JComboBox<>();
        btn_lammoi = new javax.swing.JButton();
        btn_thoat = new javax.swing.JButton();
        txt_tkkh = new javax.swing.JTextField();
        btn_tk = new javax.swing.JButton();
        dcc_ngaynhap = new datechooser.beans.DateChooserCombo();
        jLabel4 = new javax.swing.JLabel();
        s_sl = new javax.swing.JSpinner();
        btn_file = new javax.swing.JButton();
        cb_tkh = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cb_mkh = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txt_sum = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        j = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản Phẩm  "));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Ngày Tạo");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Số Phiếu");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số Lượng");

        txt_sp.setEditable(false);
        txt_sp.setBackground(new java.awt.Color(255, 102, 102));
        txt_sp.setSelectionColor(new java.awt.Color(0, 0, 0));

        txt_dg.setEditable(false);
        txt_dg.setBackground(new java.awt.Color(255, 102, 102));
        txt_dg.setSelectionColor(new java.awt.Color(0, 0, 0));

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_dau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_dau.setText("<<");
        btn_dau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dauActionPerformed(evt);
            }
        });

        btn_truoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_truoc.setText("<");
        btn_truoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_truocActionPerformed(evt);
            }
        });

        btn_cuoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_cuoi.setText(">>");
        btn_cuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cuoiActionPerformed(evt);
            }
        });

        btn_sau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_sau.setText(">");
        btn_sau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sauActionPerformed(evt);
            }
        });

        txt_vt.setEditable(false);
        txt_vt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mã Sản Phẩm");

        cb_msp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_mspItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tên Sản Phẩm");
        jLabel6.setRequestFocusEnabled(false);

        btn_lammoi.setText("Làm mới");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });

        btn_thoat.setText("Thoát");
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });

        txt_tkkh.setText("Nhập tên khách hàng");
        txt_tkkh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tkkhMouseClicked(evt);
            }
        });

        btn_tk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_tk.setText("Tìm kiếm");
        btn_tk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tkActionPerformed(evt);
            }
        });

        dcc_ngaynhap.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dcc_ngaynhap.setCalendarPreferredSize(new java.awt.Dimension(500, 220));

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel4.setText("Đơn Giá");

    btn_file.setText("Excel");
    btn_file.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_fileActionPerformed(evt);
        }
    });

    jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel7.setText("Tên Khách Hàng");

    jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel8.setText("Mã Khách Hàng");

    jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel9.setText("Tổng số tiền: ");

    txt_sum.setEditable(false);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txt_tkkh, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_tk))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dcc_ngaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(42, 42, 42)
                                    .addComponent(txt_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cb_mkh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(113, 113, 113)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_tkh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cb_tsp, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cb_msp, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(33, 33, 33)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(s_sl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txt_dg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(29, 29, 29))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(102, 102, 102)
                    .addComponent(btn_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(109, 109, 109)
                    .addComponent(btn_file, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(btn_dau)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_sum, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btn_truoc)
                            .addGap(18, 18, 18)
                            .addComponent(txt_vt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_sau)
                            .addGap(19, 19, 19)
                            .addComponent(btn_cuoi)))
                    .addGap(15, 15, 15))))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(cb_msp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(s_sl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(2, 2, 2))
                                .addComponent(dcc_ngaynhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_dg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_tsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cb_mkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cb_tkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))))
            .addGap(36, 36, 36)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_file, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btn_tk, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tkkh, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_sau)
                    .addComponent(btn_cuoi)
                    .addComponent(txt_vt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_truoc)
                    .addComponent(btn_dau)))
            .addGap(11, 11, 11)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_sum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel9)))
    );

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Phiếu Nhập"));

    j.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null}
        },
        new String [] {
            "Số Phiếu", "Mã Khách Hàng", "Tên Khách Hàng", "Mã Sản Phẩm", "Tên Sản Phẩm", "Ngày Nhập", "Số Lượng", "Đơn Giá", "Thành Tiền"
        }
    ));
    jScrollPane1.setViewportView(j);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:]
        if(btn_them.getText()=="Thêm"){
            btn_them.setText("Hủy");
            btn_sua.setText("Lưu");
            btn_xoa.setEnabled(false);
            btn_thoat.setEnabled(false);
            btn_file.setEnabled(false);
        }else if(btn_them.getText()=="Lưu"){
            if(!txt_dg.getText().isEmpty()){
                String date = sdf1.format(dcc_ngaynhap.getSelectedDate().getTime());
                long sl=0,dg=0;
                try{
                    sl=(int)s_sl.getValue();
                    dg=Integer.parseInt(txt_dg.getText());
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Gia Tri khong hop le","Thong bao",JOptionPane.ERROR_MESSAGE);
                }
                sql="Update hdx set ngay_lp='"+date+"' , sl='"+s_sl.getValue()+"',dg='"+txt_dg.getText()+"', thanh_tien='"+sl*dg+"' where ma_hdx='"+txt_sp.getText()+"' and ma_sp='"+cb_msp.getSelectedItem()+"' and ma_kh='"+cb_mkh.getSelectedItem()+"'";
                DBManager.Update(sql);
                load();
                JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                clear();
                cb_tsp.requestFocus();
            }else{
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            btn_them.setText("Thêm");
            btn_sua.setText("Sửa");
            btn_xoa.setEnabled(true);
            btn_thoat.setEnabled(true);
            btn_file.setEnabled(true);
            clear();
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        if(btn_sua.getText()=="Sửa"){
            btn_them.setText("Lưu");
            btn_sua.setText("Hủy");
            btn_xoa.setEnabled(false);
            btn_thoat.setEnabled(false);
            btn_file.setEnabled(false);
        }else if(btn_sua.getText()=="Lưu"){
            if(!txt_dg.getText().isEmpty()){
                String date = sdf1.format(dcc_ngaynhap.getSelectedDate().getTime());
                long sl_moi=0,dg=0;
                try{
                    sl_moi=(int)s_sl.getValue();
                    dg=Integer.parseInt(txt_dg.getText());
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Gia Tri khong hop le","Thong bao",JOptionPane.ERROR_MESSAGE);
                }
                long sl_cu = getSL(cb_msp.getSelectedItem().toString());
                if(sl_cu > 0)
                {
                    if(sl_cu >= sl_moi)
                    {
                        long sl_ht = sl_cu-sl_moi;
                        sql="insert into hdx Values('"+txt_sp.getText()+"','"+cb_mkh.getSelectedItem().toString()+"','"+cb_msp.getSelectedItem().toString()+"','"+date+"','"+s_sl.getValue()+"','"+txt_dg.getText()+"','"+sl_moi*dg+"')";
                        DBManager.Update(sql);
                        String sql1="update hdn set sl='"+sl_ht+"' where ma_sp='"+cb_msp.getSelectedItem()+"'";
                        DBManager.Update(sql1);
                        load();
                        JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        clear();
                        cb_tsp.requestFocus();
                    }else{
                       JOptionPane.showMessageDialog(null, "Số lượng tồn kho không đủ ", "Thông báo", JOptionPane.WARNING_MESSAGE); 
                       JOptionPane.showMessageDialog(null, "Số lượng sản phẩm hiện có : "+sl_cu );
                    }
                    
                }else{
                   JOptionPane.showMessageDialog(null, "Số lượng sản phẩm đã hết !\n Vui lòng chọn sản phẩm khác", "Thông báo", JOptionPane.WARNING_MESSAGE); 
                }
            }else{
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ đơn giá", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            btn_them.setText("Thêm");
            btn_sua.setText("Sửa");
            btn_xoa.setEnabled(true);
            btn_thoat.setEnabled(true);
            btn_file.setEnabled(true);
            clear();
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        int kq = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa?", "Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if(kq==JOptionPane.OK_OPTION){
            if(!txt_dg.getText().isEmpty()){
                sql="delete from hdx where ma_hdx='"+txt_sp.getText()+"' and ma_sp='"+cb_msp.getSelectedItem().toString()+"' and ma_kh='"+cb_mkh.getSelectedItem().toString()+"'";
                DBManager.Update(sql);
                load();
                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                clear();
                cb_tsp.requestFocus();
            }else{
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_dauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dauActionPerformed
        // TODO add your handling code here:
        if(j.getRowCount()>0){
            j.setRowSelectionInterval(0, 0);
            txt_vt.setText(String.valueOf(1));
            addText(0);
        }
    }//GEN-LAST:event_btn_dauActionPerformed

    private void btn_truocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_truocActionPerformed
        // TODO add your handling code here:
        if(j.getRowCount()>0){
            int sl=j.getSelectedRow();
            if(sl>0){
                int back = sl-1;
                j.setRowSelectionInterval(back, back);
                txt_vt.setText(String.valueOf(back+1));
                addText(back);
            }
        }
    }//GEN-LAST:event_btn_truocActionPerformed

    private void btn_cuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cuoiActionPerformed
        // TODO add your handling code here:
        if(j.getRowCount()>0){
            int end = j.getRowCount()-1;
            j.setRowSelectionInterval(end, end);
            txt_vt.setText(String.valueOf(end+1));
            addText(end);
        }
    }//GEN-LAST:event_btn_cuoiActionPerformed

    private void btn_sauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sauActionPerformed
        // TODO add your handling code here:
        if(j.getRowCount()>0){
            int sl= j.getSelectedRow();
            if(sl>=0 && sl <j.getRowCount()-1){
                int next=sl+1;
                j.setRowSelectionInterval(next, next);
                txt_vt.setText(String.valueOf(next+1));
                addText(next);
            }
        }
    }//GEN-LAST:event_btn_sauActionPerformed

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu mn = new Menu();
        mn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void txt_tkkhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tkkhMouseClicked
        // TODO add your handling code here:
        txt_tkkh.setText("");
    }//GEN-LAST:event_txt_tkkhMouseClicked

    private void btn_tkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tkActionPerformed
        // TODO add your handling code here:
        if(txt_tkkh.getText().isEmpty()){
            load();
        }else{
            sql="select hdx.ma_hdx,KhachHang.ma_kh,KhachHang.ten_kh,SanPham.ma_sp,SanPham.ten_sp,hdx.ngay_lp,hdx.sl,hdx.dg,(hdx.sl*hdx.dg) from hdx inner join SanPham on hdx.ma_sp=SanPham.ma_sp inner join KhachHang on hdx.ma_kh=KhachHang.ma_kh where KhachHang.ten_kh like '%"+txt_tkkh.getText()+"%'";
            DBManager.Table(j, sql);
            tong();
        }
    }//GEN-LAST:event_btn_tkActionPerformed

    private void btn_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fileActionPerformed
        // TODO add your handling code here:
    int[] selectedRows = j.getSelectedRows();
    if (selectedRows.length == 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một dòng để xuất file.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        return; // Không có dòng nào được chọn, không thực hiện hành động xuất file
    }

    // Xuất file Excel chỉ cho các dòng được chọn
    exportToExcel(selectedRows);        
    }//GEN-LAST:event_btn_fileActionPerformed

    private void exportToExcel(int[] selectedRows) {
    try (XSSFWorkbook workbook = new XSSFWorkbook()) {
        Sheet sheet = workbook.createSheet("PhieuNhap");

        // Tạo tiêu đề
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("DANH SÁCH HÓA ĐƠN");
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 8));

        // Tạo tiêu đề cột
        Row headerRow = sheet.createRow(2);
        String[] headers = {"Số Phiếu","Mã Khách Hàng","Tên Khách Hàng" ,"Mã Sản Phẩm", "Tên Sản Phẩm", "Ngày Lập", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        CellStyle headerStyle = createCellStyle(workbook, true);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Tạo CellStyle cho định dạng tiền tệ
        CellStyle currencyStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        currencyStyle.setDataFormat(format.getFormat("#,##0.00\\ \"VND\""));
        currencyStyle.setBorderBottom(BorderStyle.THIN);
        currencyStyle.setBorderTop(BorderStyle.THIN);
        currencyStyle.setBorderLeft(BorderStyle.THIN);
        currencyStyle.setBorderRight(BorderStyle.THIN);
        currencyStyle.setAlignment(HorizontalAlignment.CENTER);

        // Tạo CellStyle cho định dạng ngày tháng
        CellStyle dateStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        dateStyle.setBorderBottom(BorderStyle.THIN);
        dateStyle.setBorderTop(BorderStyle.THIN);
        dateStyle.setBorderLeft(BorderStyle.THIN);
        dateStyle.setBorderRight(BorderStyle.THIN);
        dateStyle.setAlignment(HorizontalAlignment.CENTER);

        // Thêm dữ liệu vào bảng
        TableModel model = j.getModel();
        CellStyle dataStyle = createCellStyle(workbook, false);
        double totalSum = 0; // Tổng của cột "Thành Tiền"
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Row row = sheet.createRow(i + 3);
            for (int colIndex = 0; colIndex < model.getColumnCount(); colIndex++) {
                Cell cell = row.createCell(colIndex);
                String cellValue = model.getValueAt(rowIndex, colIndex).toString();
                if (colIndex == 5) { // Kiểm tra nếu là cột ngày lập
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(cellValue);
                        cell.setCellValue(date);
                        cell.setCellStyle(dateStyle);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                } else if (colIndex == 7 || colIndex == 8) { // Định dạng các cột "Đơn Giá" và "Thành Tiền"
                    double value = Double.parseDouble(cellValue);
                    cell.setCellValue(value);
                    cell.setCellStyle(currencyStyle);
                    if (colIndex == 8) {
                        totalSum += value; // Tính tổng cột "Thành Tiền"
                    }
                } else {
                    cell.setCellValue(cellValue);
                    cell.setCellStyle(dataStyle);
                }
            }
        }

        // Tạo ô tổng số tiền dưới cùng của cột "Thành Tiền"
        Row totalSumRow = sheet.createRow(selectedRows.length + 3);
        Cell totalSumCellLabel = totalSumRow.createCell(7); // Ô chứa Tổng Số Tiền nằm cột 6
        totalSumCellLabel.setCellValue("Tổng Số Tiền:");
        totalSumCellLabel.setCellStyle(headerStyle);
        Cell totalSumCell = totalSumRow.createCell(8); // Ô chứa giá trị tổng
        totalSumCell.setCellValue(totalSum);
        totalSumCell.setCellStyle(currencyStyle);

        // Điều chỉnh độ rộng của cột theo nội dung
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Tạo file tạm thời
        File tempFile = File.createTempFile("PhieuNhap_", ".xlsx");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            workbook.write(fos);
        }

        // Mở file Excel
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(tempFile);
        } else {
            JOptionPane.showMessageDialog(null, "Desktop không được hỗ trợ. Không thể mở file.");
        }

    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tạo file: " + ex.getMessage());
    }
}

    private CellStyle createCellStyle(XSSFWorkbook workbook, boolean isHeader) {
    CellStyle style = workbook.createCellStyle();
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderTop(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.CENTER);

    if (isHeader) {
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
    return style;
}    
    
    private void cb_mspItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_mspItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String selectedMaSP = cb_msp.getSelectedItem().toString(); 
            int dg = getDG(selectedMaSP);       
            txt_dg.setText(String.valueOf(dg)); 
        }       
    }//GEN-LAST:event_cb_mspItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cuoi;
    private javax.swing.JButton btn_dau;
    private javax.swing.JButton btn_file;
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_sau;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JButton btn_tk;
    private javax.swing.JButton btn_truoc;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> cb_mkh;
    private javax.swing.JComboBox<String> cb_msp;
    private javax.swing.JComboBox<String> cb_tkh;
    private javax.swing.JComboBox<String> cb_tsp;
    private datechooser.beans.DateChooserCombo dcc_ngaynhap;
    private javax.swing.JTable j;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner s_sl;
    private javax.swing.JTextField txt_dg;
    private javax.swing.JTextField txt_sp;
    private javax.swing.JTextField txt_sum;
    private javax.swing.JTextField txt_tkkh;
    private javax.swing.JTextField txt_vt;
    // End of variables declaration//GEN-END:variables
}
