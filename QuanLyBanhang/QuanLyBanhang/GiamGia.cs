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
    public partial class GiamGia : Form
    {
        private string sql;
        public GiamGia()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            btn_tangHuy.Enabled=false;
            btn_tangok.Enabled=false;
            sql = " SELECT COUNT(*) FROM hdx inner join SanPham on SanPham.ma_sp=hdx.ma_sp WHERE mat_hang = N'" + cb_loaihang.Text.Trim() + "'";
            int count = Convert.ToInt16(Function.ExecuteScalar(sql));
            if (count > 0)
            {
                if (check(txt_ptGiam.Text))
                     {
                    int x = Convert.ToInt16(txt_ptGiam.Text);
                    float y = (float)(x / 100.0);
                    sql = "UPDATE hdx SET dg = dg- (dg * (" + y + ")) FROM hdx INNER JOIN SanPham ON hdx.ma_sp = SanPham.ma_sp where hdx.ma_sp=SanPham.ma_sp and mat_hang=N'" + cb_loaihang.Text.Trim() + "'";
                    Function.ExecuteNonQuery(sql);
                    MessageBox.Show("Cập nhật thành công", "Thông báo");
                }
            }
            else
            {
                MessageBox.Show("Mặt hàng này hiện không có trong kho");
            }
        }
        private void loadcombobox()
        {
            sql = "select loai_hang from Loai_Hang";
            DataTable dt = Function.GetDataTable(sql);

            cb_loaihang.DataSource = dt;
            cb_loaihang.DisplayMember = "loai_hang";
            cb_loaihang.ValueMember = "loai_hang";
        }
        private bool check(string key)
        {
            if (string.IsNullOrEmpty(key))
                return false;
            if (!int.TryParse(key, out int number) || number <1 || number >100 )
                return false;
            return true;

        }
        private void button2_Click(object sender, EventArgs e)
        {
            btn_tangHuy.Enabled = true;
            btn_tangok.Enabled = true;
            txt_ptGiam.Text = "";
            txt_ptGiam.Focus();
        }

        private void GiamGia_Load(object sender, EventArgs e)
        {
            loadcombobox();
        }

        private void btn_tangHuy_Click(object sender, EventArgs e)
        {
            txt_ptGiam.Text = "";
            txt_ptGiam.Focus();
            button1.Enabled = true;
            button2.Enabled = true;
        }

        private void btn_tangok_Click(object sender, EventArgs e)
        {
            button1.Enabled = false;
            button2.Enabled = false;
            sql = " SELECT COUNT(*) FROM hdx inner join SanPham on SanPham.ma_sp=hdx.ma_sp WHERE mat_hang = N'" + cb_loaihang.Text.Trim() + "'";
            int count=Convert.ToInt16(Function.ExecuteScalar(sql));
            if (count > 0)
            {
                if (check(txt_ptGiam.Text))
                {
                    int x = Convert.ToInt16(txt_ptGiam.Text);
                    float y = (float)(x / 100.0);
                    sql = "UPDATE hdx SET dg = dg+ (dg * (" + y + ")) FROM hdx INNER JOIN SanPham ON hdx.ma_sp = SanPham.ma_sp where hdx.ma_sp = SanPham.ma_sp and mat_hang = N'" + cb_loaihang.Text.Trim() + "'";
                    Function.ExecuteNonQuery(sql);
                    MessageBox.Show("Cập nhật thành công", "Thông báo");
                }
            }
            else
            {
                MessageBox.Show("Mặt hàng này hiện không có trong kho");
            }
        }
    }
}
