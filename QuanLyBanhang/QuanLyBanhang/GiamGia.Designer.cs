namespace QuanLyBanhang
{
    partial class GiamGia
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(GiamGia));
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.panel1 = new System.Windows.Forms.Panel();
            this.txt_ptGiam = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.panel5 = new System.Windows.Forms.Panel();
            this.cb_loaihang = new System.Windows.Forms.ComboBox();
            this.label5 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.button2 = new System.Windows.Forms.Button();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.btn_tangHuy = new System.Windows.Forms.Button();
            this.btn_tangok = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.panel1.SuspendLayout();
            this.panel5.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.panel1);
            this.groupBox1.Controls.Add(this.panel5);
            this.groupBox1.Dock = System.Windows.Forms.DockStyle.Left;
            this.groupBox1.Location = new System.Drawing.Point(0, 0);
            this.groupBox1.Margin = new System.Windows.Forms.Padding(4);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Padding = new System.Windows.Forms.Padding(4);
            this.groupBox1.Size = new System.Drawing.Size(545, 214);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            // 
            // panel1
            // 
            this.panel1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.panel1.Controls.Add(this.txt_ptGiam);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Location = new System.Drawing.Point(34, 122);
            this.panel1.Margin = new System.Windows.Forms.Padding(4);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(479, 51);
            this.panel1.TabIndex = 13;
            // 
            // txt_ptGiam
            // 
            this.txt_ptGiam.Location = new System.Drawing.Point(171, 10);
            this.txt_ptGiam.Margin = new System.Windows.Forms.Padding(4);
            this.txt_ptGiam.MaxLength = 100;
            this.txt_ptGiam.Name = "txt_ptGiam";
            this.txt_ptGiam.Size = new System.Drawing.Size(290, 27);
            this.txt_ptGiam.TabIndex = 4;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(16, 14);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(136, 20);
            this.label1.TabIndex = 2;
            this.label1.Text = "Phần Trăm Giảm";
            // 
            // panel5
            // 
            this.panel5.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.panel5.Controls.Add(this.cb_loaihang);
            this.panel5.Controls.Add(this.label5);
            this.panel5.Location = new System.Drawing.Point(34, 40);
            this.panel5.Margin = new System.Windows.Forms.Padding(4);
            this.panel5.Name = "panel5";
            this.panel5.Size = new System.Drawing.Size(479, 51);
            this.panel5.TabIndex = 12;
            // 
            // cb_loaihang
            // 
            this.cb_loaihang.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cb_loaihang.FormattingEnabled = true;
            this.cb_loaihang.Location = new System.Drawing.Point(165, 10);
            this.cb_loaihang.Margin = new System.Windows.Forms.Padding(4);
            this.cb_loaihang.Name = "cb_loaihang";
            this.cb_loaihang.Size = new System.Drawing.Size(296, 28);
            this.cb_loaihang.TabIndex = 3;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(19, 10);
            this.label5.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(86, 20);
            this.label5.TabIndex = 2;
            this.label5.Text = "Loại Hàng";
            // 
            // button1
            // 
            this.button1.Image = ((System.Drawing.Image)(resources.GetObject("button1.Image")));
            this.button1.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.button1.Location = new System.Drawing.Point(26, 110);
            this.button1.Margin = new System.Windows.Forms.Padding(4);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(134, 74);
            this.button1.TabIndex = 3;
            this.button1.Text = "Lưu";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.button2);
            this.groupBox2.Controls.Add(this.button1);
            this.groupBox2.Location = new System.Drawing.Point(752, 17);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(194, 197);
            this.groupBox2.TabIndex = 5;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Giảm Giá";
            // 
            // button2
            // 
            this.button2.Image = ((System.Drawing.Image)(resources.GetObject("button2.Image")));
            this.button2.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.button2.Location = new System.Drawing.Point(26, 27);
            this.button2.Margin = new System.Windows.Forms.Padding(4);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(134, 74);
            this.button2.TabIndex = 4;
            this.button2.Text = "Hủy";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.btn_tangHuy);
            this.groupBox3.Controls.Add(this.btn_tangok);
            this.groupBox3.Location = new System.Drawing.Point(552, 17);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(194, 197);
            this.groupBox3.TabIndex = 6;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Tăng Giá";
            // 
            // btn_tangHuy
            // 
            this.btn_tangHuy.Image = ((System.Drawing.Image)(resources.GetObject("btn_tangHuy.Image")));
            this.btn_tangHuy.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btn_tangHuy.Location = new System.Drawing.Point(26, 27);
            this.btn_tangHuy.Margin = new System.Windows.Forms.Padding(4);
            this.btn_tangHuy.Name = "btn_tangHuy";
            this.btn_tangHuy.Size = new System.Drawing.Size(134, 74);
            this.btn_tangHuy.TabIndex = 4;
            this.btn_tangHuy.Text = "Hủy";
            this.btn_tangHuy.UseVisualStyleBackColor = true;
            this.btn_tangHuy.Click += new System.EventHandler(this.btn_tangHuy_Click);
            // 
            // btn_tangok
            // 
            this.btn_tangok.Image = ((System.Drawing.Image)(resources.GetObject("btn_tangok.Image")));
            this.btn_tangok.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btn_tangok.Location = new System.Drawing.Point(26, 110);
            this.btn_tangok.Margin = new System.Windows.Forms.Padding(4);
            this.btn_tangok.Name = "btn_tangok";
            this.btn_tangok.Size = new System.Drawing.Size(134, 74);
            this.btn_tangok.TabIndex = 3;
            this.btn_tangok.Text = "Lưu";
            this.btn_tangok.UseVisualStyleBackColor = true;
            this.btn_tangok.Click += new System.EventHandler(this.btn_tangok_Click);
            // 
            // GiamGia
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(950, 214);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(4);
            this.MaximizeBox = false;
            this.Name = "GiamGia";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Thay Đổi Giá";
            this.Load += new System.EventHandler(this.GiamGia_Load);
            this.groupBox1.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.panel5.ResumeLayout(false);
            this.panel5.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.TextBox txt_ptGiam;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel panel5;
        private System.Windows.Forms.ComboBox cb_loaihang;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Button btn_tangHuy;
        private System.Windows.Forms.Button btn_tangok;
    }
}