using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QuanLyBanhang
{
    public partial class Form1 : Form
    {
        private bool isPB_MENUHovered = false;
        public Form1()
        {
            InitializeComponent();
            menuStrip1.Visible = false;
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void mnuDanhmuc_Click(object sender, EventArgs e)
        {

        }

        private void mnuBaocao_Click(object sender, EventArgs e)
        {

        }

        private void mnuChatlieu_Click(object sender, EventArgs e)
        {


        }

        private void mnuNhanvien_Click(object sender, EventArgs e)
        {

        }

        private void mnuKhachhang_Click(object sender, EventArgs e)
        {


        }

        private void mnuSanPham_Click(object sender, EventArgs e)
        {

        }

        private void mnuHoadonban_Click(object sender, EventArgs e)
        {

        }

        private void hóaĐơnToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void mặtHàngToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void mnuDoanhthu_Click(object sender, EventArgs e)
        {

        }

        private void tinhLuong_Menustrip_Click(object sender, EventArgs e)
        {

        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            DialogResult traloi;
            traloi = MessageBox.Show("Bạn có muốn đăng xuất", "Thông báo", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (traloi == DialogResult.Yes)
            {
                DangNhap dangnhap=  new DangNhap();
                dangnhap.Show();
                this.Hide();
            }
        }

        private void mnuThoat_Click(object sender, EventArgs e)
        {

        }

        private void Form1_Leave(object sender, EventArgs e)
        {

        }

        private void TK_menustrip_Click(object sender, EventArgs e)
        {

        }



        private void menuStrip1_MouseLeave(object sender, EventArgs e)
        {
        }

        private void menuStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }

        private void TK_menustrip_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            ThongKe tk = new ThongKe();
            tk.ShowDialog();
            this.Show();
        }

        private void mnuThoat_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            TaoTaiKhoan tk = new TaoTaiKhoan("Menu_Admin");
            tk.ShowDialog();
            this.Show();
        }

        private void mnuNhaCungCap_Click(object sender, EventArgs e)
        {
            this.Hide();
            NhaCungCap ncc = new NhaCungCap();
            ncc.ShowDialog();
            this.Show();
        }

        private void mnuNhanvien_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            NhanVien nv = new NhanVien();
            nv.ShowDialog();
            this.Show();
        }

        private void mnuKhachhang_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            KhachHangcs kh = new KhachHangcs();
            kh.ShowDialog();
            this.Show();
        }

        private void mnuSanPham_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            SanPham sanPham = new SanPham();
            sanPham.ShowDialog();
            this.Show();
        }

        private void mặtHàngToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            LoaiHang lh = new LoaiHang();
            lh.ShowDialog();
            this.Show();
        }

        private void mnuHoadonban_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            PhieuNhap pn = new PhieuNhap();
            pn.ShowDialog();
            this.Show();
        }

        private void hóaĐơnToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            HoaDoncs hd = new HoaDoncs("");
            hd.ShowDialog();
            this.Show();
        }

        private void tinhLuong_Menustrip_Click_1(object sender, EventArgs e)
        {
            this.Hide();
            LuongNhanVien luongNhanVien = new LuongNhanVien();
            luongNhanVien.ShowDialog();
            this.Show();
        }

        private void pictureBox1_MouseLeave(object sender, EventArgs e)
        {

        }

        private void PB_MENU_MouseLeave(object sender, EventArgs e)
        {
            if (!menuStrip1.Bounds.Contains(PointToClient(MousePosition)))
            {
                isPB_MENUHovered = false;
                PB_MENU.Visible = true; // Hiển thị PictureBox khi chuột rời khỏi
                menuStrip1.Visible = false; // Ẩn MenuStrip khi chuột rời khỏi
            }
        }
        protected override void OnMouseMove(MouseEventArgs e)
        {
            base.OnMouseMove(e);
            if (!isPB_MENUHovered && !menuStrip1.Bounds.Contains(PointToClient(MousePosition)))
            {
                PB_MENU.Visible = true; // Hiển thị PictureBox khi chuột rời khỏi MenuStrip
                menuStrip1.Visible = false; // Ẩn MenuStrip khi chuột rời khỏi MenuStrip
            }
        }

        private void PB_MENU_MouseEnter(object sender, EventArgs e)
        {
            isPB_MENUHovered = true;
            PB_MENU.Visible = false; // Ẩn PictureBox khi trỏ chuột vào
            menuStrip1.Visible = true;
        }

        private void pictureBox1_MouseClick(object sender, MouseEventArgs e)
        {
            menuStrip1.Visible = false;
            PB_MENU.Visible = true;
        }

        private void pictureBox1_MouseEnter(object sender, EventArgs e)
        {
            menuStrip1.Visible = false;
            PB_MENU.Visible = true;
        }
    }
}
