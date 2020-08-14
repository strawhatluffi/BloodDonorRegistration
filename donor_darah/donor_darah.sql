-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 05, 2017 at 09:13 AM
-- Server version: 5.7.17-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `donor_darah`
--

-- --------------------------------------------------------

--
-- Table structure for table `daftar_donor`
--

CREATE TABLE `daftar_donor` (
  `id_daftar` char(5) NOT NULL,
  `id_pendonor` char(5) NOT NULL,
  `id_reg` char(10) NOT NULL,
  `id_kebutuhan` char(10) NOT NULL,
  `tgl_daftar` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftar_donor`
--

INSERT INTO `daftar_donor` (`id_daftar`, `id_pendonor`, `id_reg`, `id_kebutuhan`, `tgl_daftar`) VALUES
('00001', '00001', '2107170001', '0000001', '2017-07-21 09:40:35'),
('00002', '00002', '2107170002', '0000002', '2017-07-21 09:52:44'),
('00003', '00002', '2107170003', '0000002', '2017-07-21 09:52:56'),
('00004', '00001', '2410170001', '0000001', '2017-10-24 07:44:47'),
('00005', '00001', '2410170002', '0000001', '2017-10-24 08:02:04'),
('00006', '00004', '2510170001', '0000004', '2017-10-25 00:03:30'),
('00007', '00002', '2510170002', '0000002', '2017-10-25 00:06:05'),
('00008', '00002', '2510170003', '0000002', '2017-10-25 00:49:54'),
('00009', '00005', '2510170004', '0000003', '2017-10-25 04:15:00'),
('00010', '00006', '0411170001', '0000003', '2017-11-04 21:14:54'),
('00011', '00008', '0411170002', '0000006', '2017-11-04 23:58:10');

-- --------------------------------------------------------

--
-- Table structure for table `jawaban`
--

CREATE TABLE `jawaban` (
  `id_daftar` char(5) NOT NULL,
  `id_kuisioner` int(11) NOT NULL,
  `jawaban` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jawaban`
--

INSERT INTO `jawaban` (`id_daftar`, `id_kuisioner`, `jawaban`) VALUES
('00001', 4, 'Y'),
('00001', 5, 'Y'),
('00001', 6, 'Y'),
('00001', 7, 'T'),
('00001', 8, 'T'),
('00001', 9, 'T'),
('00001', 10, 'T'),
('00001', 11, 'T'),
('00001', 12, 'T'),
('00001', 13, 'T'),
('00001', 14, 'T'),
('00001', 15, 'T'),
('00001', 16, 'T'),
('00001', 17, 'T'),
('00001', 18, 'T'),
('00001', 19, 'T'),
('00001', 20, 'T'),
('00002', 1, 'T'),
('00002', 2, 'T'),
('00002', 3, 'T'),
('00002', 4, 'Y'),
('00002', 5, 'Y'),
('00002', 6, 'Y'),
('00002', 7, 'T'),
('00002', 8, 'T'),
('00002', 9, 'T'),
('00002', 10, 'T'),
('00002', 11, 'T'),
('00002', 12, 'T'),
('00002', 13, 'T'),
('00002', 14, 'T'),
('00002', 15, 'T'),
('00002', 16, 'T'),
('00002', 17, 'T'),
('00002', 18, 'T'),
('00002', 19, 'T'),
('00002', 20, 'T'),
('00003', 1, 'T'),
('00003', 2, 'T'),
('00003', 3, 'T'),
('00003', 4, 'Y'),
('00003', 5, 'Y'),
('00003', 6, 'Y'),
('00003', 7, 'T'),
('00003', 8, 'T'),
('00003', 9, 'T'),
('00003', 10, 'T'),
('00003', 11, 'T'),
('00003', 12, 'T'),
('00003', 13, 'T'),
('00003', 14, 'T'),
('00003', 15, 'T'),
('00003', 16, 'T'),
('00003', 17, 'T'),
('00003', 18, 'T'),
('00003', 19, 'T'),
('00003', 20, 'T'),
('00004', 4, 'T'),
('00004', 5, 'Y'),
('00004', 6, 'Y'),
('00004', 7, 'T'),
('00004', 8, 'Y'),
('00004', 9, 'Y'),
('00004', 10, 'T'),
('00004', 11, 'Y'),
('00004', 12, 'T'),
('00004', 13, 'Y'),
('00004', 14, 'Y'),
('00004', 15, 'Y'),
('00004', 16, 'T'),
('00004', 17, 'Y'),
('00004', 18, 'Y'),
('00004', 19, 'Y'),
('00004', 20, 'T'),
('00005', 4, 'Y'),
('00005', 5, 'Y'),
('00005', 6, 'Y'),
('00005', 7, 'T'),
('00005', 8, 'T'),
('00005', 9, 'T'),
('00005', 10, 'T'),
('00005', 11, 'T'),
('00005', 12, 'T'),
('00005', 13, 'T'),
('00005', 14, 'T'),
('00005', 15, 'T'),
('00005', 16, 'T'),
('00005', 17, 'T'),
('00005', 18, 'T'),
('00005', 19, 'T'),
('00005', 20, 'T'),
('00006', 1, 'Y'),
('00006', 2, ''),
('00006', 3, ''),
('00006', 4, ''),
('00006', 5, ''),
('00006', 6, ''),
('00006', 7, ''),
('00006', 8, ''),
('00006', 9, ''),
('00006', 10, ''),
('00006', 11, ''),
('00006', 12, ''),
('00006', 13, ''),
('00006', 14, ''),
('00006', 15, ''),
('00006', 16, ''),
('00006', 17, ''),
('00006', 18, ''),
('00006', 19, ''),
('00006', 20, ''),
('00007', 1, ''),
('00007', 2, ''),
('00007', 3, ''),
('00007', 4, ''),
('00007', 5, ''),
('00007', 6, ''),
('00007', 7, ''),
('00007', 8, ''),
('00007', 9, ''),
('00007', 10, ''),
('00007', 11, ''),
('00007', 12, ''),
('00007', 13, ''),
('00007', 14, ''),
('00007', 15, ''),
('00007', 16, ''),
('00007', 17, ''),
('00007', 18, ''),
('00007', 19, ''),
('00007', 20, ''),
('00008', 1, ''),
('00008', 2, ''),
('00008', 3, 'T'),
('00008', 4, 'T'),
('00008', 5, ''),
('00008', 6, ''),
('00008', 7, ''),
('00008', 8, ''),
('00008', 9, ''),
('00008', 10, ''),
('00008', 11, ''),
('00008', 12, ''),
('00008', 13, ''),
('00008', 14, ''),
('00008', 15, ''),
('00008', 16, ''),
('00008', 17, ''),
('00008', 18, ''),
('00008', 19, ''),
('00008', 20, ''),
('00009', 1, 'T'),
('00009', 2, 'T'),
('00009', 3, 'T'),
('00009', 4, 'Y'),
('00009', 5, 'Y'),
('00009', 6, 'Y'),
('00009', 7, 'T'),
('00009', 8, 'T'),
('00009', 9, 'T'),
('00009', 10, 'T'),
('00009', 11, 'T'),
('00009', 12, 'T'),
('00009', 13, 'T'),
('00009', 14, 'T'),
('00009', 15, 'T'),
('00009', 16, 'T'),
('00009', 17, 'T'),
('00009', 18, 'T'),
('00009', 19, 'T'),
('00009', 20, 'T'),
('00010', 1, ''),
('00010', 2, ''),
('00010', 3, ''),
('00010', 4, ''),
('00010', 5, ''),
('00010', 6, ''),
('00010', 7, ''),
('00010', 8, ''),
('00010', 9, ''),
('00010', 10, ''),
('00010', 11, ''),
('00010', 12, ''),
('00010', 13, ''),
('00010', 14, ''),
('00010', 15, ''),
('00010', 16, ''),
('00010', 17, ''),
('00010', 18, ''),
('00010', 19, ''),
('00010', 20, ''),
('00011', 1, ''),
('00011', 2, ''),
('00011', 3, ''),
('00011', 4, ''),
('00011', 5, ''),
('00011', 6, ''),
('00011', 7, ''),
('00011', 8, ''),
('00011', 9, ''),
('00011', 10, ''),
('00011', 11, ''),
('00011', 12, ''),
('00011', 13, ''),
('00011', 14, ''),
('00011', 15, ''),
('00011', 16, ''),
('00011', 17, ''),
('00011', 18, ''),
('00011', 19, ''),
('00011', 20, '');

-- --------------------------------------------------------

--
-- Table structure for table `kebutuhan_darah`
--

CREATE TABLE `kebutuhan_darah` (
  `id_kebutuhan` char(7) NOT NULL,
  `nama_resipien` varchar(30) NOT NULL,
  `jenis_kel` char(6) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `goldar` varchar(2) NOT NULL,
  `kebutuhan_kantung` int(2) NOT NULL,
  `kontak` varchar(15) NOT NULL,
  `wali_pasien` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kebutuhan_darah`
--

INSERT INTO `kebutuhan_darah` (`id_kebutuhan`, `nama_resipien`, `jenis_kel`, `tgl_lahir`, `goldar`, `kebutuhan_kantung`, `kontak`, `wali_pasien`) VALUES
('0000001', 'Pasien A', 'Pria', '1989-10-10', 'B', 4, '0856411', 'Wali A'),
('0000002', 'Si B', 'Wanita', '1987-06-16', 'A', 2, '086542311', 'Wali B'),
('0000003', 'James Bond', 'Pria', '1990-06-19', 'O', 8, '0867896757', 'Bondaries'),
('0000004', 'asad', 'Pria', '1999-02-08', 'O', 22, '085712345', 'Baba'),
('0000005', 'Boggy', 'Wanita', '0000-00-00', 'B', 12, '0858297234', 'Bogy'),
('0000006', 'Baba', 'Wanita', '2017-11-08', 'B', 11, '085827384', 'Berw');

-- --------------------------------------------------------

--
-- Table structure for table `kusioner`
--

CREATE TABLE `kusioner` (
  `id_kuisioner` int(11) NOT NULL,
  `soal` varchar(300) NOT NULL,
  `jawaban` char(1) NOT NULL,
  `jenis` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kusioner`
--

INSERT INTO `kusioner` (`id_kuisioner`, `soal`, `jawaban`, `jenis`) VALUES
(1, 'apakah anda sedang menstruasi sekarang?', 'T', 'khusus'),
(2, 'apakah anda sehabis melahirkan 6 bulan dari sekarang?', 'T', 'khusus'),
(3, 'apakah anda sedang menyusui sekarang?', 'T', 'khusus'),
(4, 'apakah umur anda antara 17 - 60 tahun dan merasa sehat untuk mendonorkan darah sekarang?', 'Y', 'umum'),
(5, 'apakah anda tidur semalam?', 'Y', 'umum'),
(6, 'apakah anda telah makan sebelum donasi(30-60) menit pada hari ini?', 'Y', 'umum'),
(7, 'apakah anda diare dalam 7 hari terakhir?', 'T', 'umum'),
(8, 'apakah anda mengalami penurunan berat badan tanpa sebab yang jelas dalam 3 bulan terakhir?', 'T', 'umum'),
(9, 'apakah anda minum aspirin atau obat nyeri lain dalam 3 hari terakhir?', 'T', 'umum'),
(10, 'apakah anda minum antibiotik atau obat lain dalam 7 hari terakhir?', 'T', 'umum'),
(11, 'apakah anda penderita asma, epilepsi, dermatitis khronis, batuk khronis, tuberkolosis, alergi, tekanan darah tinggi / hipertensi, diabetes / kencing manis, penyakit jantung / ginjal / tiroid, kanker atau kelainan darah?', 'T', 'umum'),
(12, 'apakah anda atau seorang dalam keluarga anda pernah menderita hepatitis?', 'T', 'umum'),
(13, 'apakah anda melakukan pengobatan gigi dalam 3 hari terakhir?', 'T', 'umum'),
(14, 'apakah anda atau pasangan anda beresiko atau sedang dalam pengobatan penyakit menular seksual, termasuk didalamnya beresiko terhadap HIV?', 'T', 'umum'),
(15, 'apakah anda dioperasi besar dalam 6 bulan terakhir atau dioperasi kecil dalam 1 bulan terakhir?', 'T', 'umum'),
(16, 'apakah anda pernah tindik / tatto / akupuntur dalam 12 bulan terakhir?', 'T', 'umum'),
(17, 'apakah anda mempunyai riwayat sebagai pengguna obat - obatan atau pernah dipenjara dalam 3 tahun terakhir?', 'T', 'umum'),
(18, 'apakah anda pernah ditransfusi darah dalam 1 tahun terakhir?', 'T', 'umum'),
(19, 'apakah anda mengunjungi daerah malaria dalam 1 tahun terakhir atau pernah menderita malaria dalam 3 tahun terakhir?', 'T', 'umum'),
(20, 'apakah anda mengalami masalah saat donasi terakhir?', 'T', 'umum');

-- --------------------------------------------------------

--
-- Table structure for table `pendonor`
--

CREATE TABLE `pendonor` (
  `id_pendonor` char(5) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jk` char(6) NOT NULL,
  `tgl_lhr` date NOT NULL,
  `berat_badan` int(11) NOT NULL,
  `pekerjaan` varchar(30) NOT NULL,
  `gol_dar` varchar(2) NOT NULL,
  `donasi_terakhir` date DEFAULT NULL,
  `donasi_selanjutnya` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pendonor`
--

INSERT INTO `pendonor` (`id_pendonor`, `no_hp`, `email`, `password`, `nama`, `jk`, `tgl_lhr`, `berat_badan`, `pekerjaan`, `gol_dar`, `donasi_terakhir`, `donasi_selanjutnya`) VALUES
('00001', '0878556656', 'alwi89@gmail.com', 'alwi', 'M. Alwi', 'Pria', '1989-03-18', 67, 'programmer', 'B', '2017-10-24', '2018-01-02'),
('00002', '0856988', 'lita@gmail.com', 'lita', 'Lita', 'Wanita', '1992-10-22', 50, 'mahasiswi', 'A', NULL, NULL),
('00003', '0856555', 'baru@gmail.com', 'baru', 'si baru', 'Wanita', '1993-03-22', 60, 'wiraswasta', 'A', NULL, NULL),
('00004', '085729537099', 'good.bassis@gmail.com', '1234', 'bogy', 'P', '1998-07-14', 65, 'mahasiswa', 'O', '2017-10-25', '2018-01-03'),
('00005', '08572834', 'aaa@gmail.com', '1234', 'aaa', 'Wanita', '1991-09-11', 65, 'Mahasiswa', 'O', '2017-10-25', '2018-01-03'),
('00006', '085729537099', 'good.bassis1@gmail.com', '1234', 'booo', 'Pria', '1991-11-12', 65, 'bbb', 'O', '2017-11-05', '2018-01-14'),
('00007', '08575258', 'good.bassis2@gmail.com', '123', 'ere', 'Wanita', '1982-11-24', 65, 'bbb', 'B', NULL, NULL),
('00008', '0857565', 'good.bassis3@gmail.com', '1234', 'rghyc', 'Pria', '1997-07-16', 60, 'bbhy', 'B', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` int(4) NOT NULL,
  `nama_petugas` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `nama_petugas`, `username`, `password`) VALUES
(1, 'M. Alwi', 'alwi', 'alwi'),
(2, 'boggy', 'boggy', '125410019');

-- --------------------------------------------------------

--
-- Table structure for table `proses_donor`
--

CREATE TABLE `proses_donor` (
  `id_reg` char(10) NOT NULL,
  `id_pendonor` char(5) NOT NULL,
  `id_petugas` int(7) NOT NULL,
  `hemoglobin` varchar(7) NOT NULL,
  `tekanan_darah` varchar(7) NOT NULL,
  `tgl_donor` date NOT NULL,
  `status_proses` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proses_donor`
--

INSERT INTO `proses_donor` (`id_reg`, `id_pendonor`, `id_petugas`, `hemoglobin`, `tekanan_darah`, `tgl_donor`, `status_proses`) VALUES
('2107170001', '00001', 1, '12.5', '100/90', '2017-07-25', 'ok'),
('2410170002', '00001', 1, '13', '120/100', '2017-10-24', 'ok'),
('2510170001', '00004', 1, '13', '120/90', '2017-10-25', 'ok'),
('2510170004', '00005', 2, '13', '120/85', '2017-10-25', 'ok'),
('0411170001', '00006', 2, '13', '120/90', '2017-11-05', 'ok');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `daftar_donor`
--
ALTER TABLE `daftar_donor`
  ADD PRIMARY KEY (`id_daftar`),
  ADD UNIQUE KEY `id_reg` (`id_reg`),
  ADD KEY `id_pendonor` (`id_pendonor`),
  ADD KEY `id_kebutuhan` (`id_kebutuhan`);

--
-- Indexes for table `jawaban`
--
ALTER TABLE `jawaban`
  ADD KEY `id_daftar` (`id_daftar`),
  ADD KEY `id_kuisioner` (`id_kuisioner`);

--
-- Indexes for table `kebutuhan_darah`
--
ALTER TABLE `kebutuhan_darah`
  ADD PRIMARY KEY (`id_kebutuhan`);

--
-- Indexes for table `kusioner`
--
ALTER TABLE `kusioner`
  ADD PRIMARY KEY (`id_kuisioner`);

--
-- Indexes for table `pendonor`
--
ALTER TABLE `pendonor`
  ADD PRIMARY KEY (`id_pendonor`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indexes for table `proses_donor`
--
ALTER TABLE `proses_donor`
  ADD KEY `id_pendonor` (`id_pendonor`),
  ADD KEY `id_petugas` (`id_petugas`),
  ADD KEY `id_reg` (`id_reg`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `daftar_donor`
--
ALTER TABLE `daftar_donor`
  ADD CONSTRAINT `daftar_donor_ibfk_1` FOREIGN KEY (`id_pendonor`) REFERENCES `pendonor` (`id_pendonor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `daftar_donor_ibfk_3` FOREIGN KEY (`id_kebutuhan`) REFERENCES `kebutuhan_darah` (`id_kebutuhan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `jawaban`
--
ALTER TABLE `jawaban`
  ADD CONSTRAINT `jawaban_ibfk_1` FOREIGN KEY (`id_daftar`) REFERENCES `daftar_donor` (`id_daftar`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `jawaban_ibfk_2` FOREIGN KEY (`id_kuisioner`) REFERENCES `kusioner` (`id_kuisioner`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `proses_donor`
--
ALTER TABLE `proses_donor`
  ADD CONSTRAINT `proses_donor_ibfk_1` FOREIGN KEY (`id_pendonor`) REFERENCES `pendonor` (`id_pendonor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proses_donor_ibfk_2` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proses_donor_ibfk_3` FOREIGN KEY (`id_reg`) REFERENCES `daftar_donor` (`id_reg`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
