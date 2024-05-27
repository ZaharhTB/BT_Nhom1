-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 27, 2024 lúc 09:01 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlbh`
--
CREATE DATABASE IF NOT EXISTS `qlbh` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `qlbh`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hdn`
--

CREATE TABLE `hdn` (
  `ma_hdn` varchar(15) NOT NULL,
  `ma_sp` varchar(15) NOT NULL,
  `ngay_lp` date DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `dg` int(11) DEFAULT NULL,
  `thanh_tien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hdn`
--

INSERT INTO `hdn` (`ma_hdn`, `ma_sp`, `ngay_lp`, `sl`, `dg`, `thanh_tien`) VALUES
('HDN468', 'SP273', '2026-05-21', 3, 16000, 80000),
('HDN469', 'SP15', '2024-05-17', 8, 25000, 250000),
('HDN92', 'SP393', '2024-05-25', 0, 12, 16);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hdx`
--

CREATE TABLE `hdx` (
  `ma_hdx` varchar(15) NOT NULL,
  `ma_kh` varchar(15) NOT NULL,
  `ma_sp` varchar(15) NOT NULL,
  `ngay_lp` date DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `dg` int(11) DEFAULT NULL,
  `thanh_tien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hdx`
--

INSERT INTO `hdx` (`ma_hdx`, `ma_kh`, `ma_sp`, `ngay_lp`, `sl`, `dg`, `thanh_tien`) VALUES
('HDX175', 'KH111', 'SP273', '2024-05-25', 2, 16000, 32000),
('HDX321', 'KH168', 'SP15', '2024-06-13', 1, 25000, 25000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `ma_kh` varchar(15) NOT NULL,
  `ten_kh` varchar(70) DEFAULT NULL,
  `gioi_tinh` varchar(5) DEFAULT NULL,
  `dc` varchar(70) DEFAULT NULL,
  `sdt` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`ma_kh`, `ten_kh`, `gioi_tinh`, `dc`, `sdt`) VALUES
('KH111', 'Đàm Suy', 'Nam', '77 TDT', '1321321'),
('KH168', 'Lôi Đình Phát', 'Nam', '120 GP', '19009191'),
('KH324', 'Nam A', 'Nam', 'TP ND', '99999'),
('KH332', 'An', 'Nam', '350 THD', '0979791231');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `ma_ncc` varchar(15) NOT NULL,
  `ten_ncc` varchar(70) DEFAULT NULL,
  `dc` varchar(70) DEFAULT NULL,
  `sdt` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`ma_ncc`, `ten_ncc`, `dc`, `sdt`) VALUES
('ct1', 'Nam Á', 'Hà Nội', '099987123'),
('CT127', 'PhanTom', 'Nam Định', '12313'),
('CT280', 'Sài Thành', 'Văn Lang', '11455'),
('CT67', 'Em Tuấn CJay', 'Hải Phòng', '99999990');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `ma_sp` varchar(15) NOT NULL,
  `ten_sp` varchar(70) DEFAULT NULL,
  `ma_ncc` varchar(15) NOT NULL,
  `hang_sx` varchar(70) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`ma_sp`, `ten_sp`, `ma_ncc`, `hang_sx`) VALUES
('SP15', 'Kẹo Ke', 'CT280', 'Đình Phát'),
('SP273', 'Bi Lắc', 'CT280', 'Quang Ngọc'),
('SP393', 'Bánh Rán', 'ct1', 'Cô Hà Vlog');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hdn`
--
ALTER TABLE `hdn`
  ADD PRIMARY KEY (`ma_hdn`),
  ADD KEY `ma_sp` (`ma_sp`);

--
-- Chỉ mục cho bảng `hdx`
--
ALTER TABLE `hdx`
  ADD PRIMARY KEY (`ma_hdx`),
  ADD KEY `ma_kh` (`ma_kh`),
  ADD KEY `ma_sp` (`ma_sp`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`ma_kh`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`ma_ncc`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`ma_sp`),
  ADD KEY `ma_ncc` (`ma_ncc`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hdn`
--
ALTER TABLE `hdn`
  ADD CONSTRAINT `hdn_ibfk_1` FOREIGN KEY (`ma_sp`) REFERENCES `sanpham` (`ma_sp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hdx`
--
ALTER TABLE `hdx`
  ADD CONSTRAINT `hdx_ibfk_1` FOREIGN KEY (`ma_kh`) REFERENCES `khachhang` (`ma_kh`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hdx_ibfk_2` FOREIGN KEY (`ma_sp`) REFERENCES `sanpham` (`ma_sp`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`ma_ncc`) REFERENCES `nhacungcap` (`ma_ncc`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
