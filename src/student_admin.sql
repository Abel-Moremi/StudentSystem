-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 28, 2019 at 08:45 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_admin`
--

-- --------------------------------------------------------

--
-- Table structure for table `am_admin`
--

CREATE TABLE `am_admin` (
  `admin_id` int(15) NOT NULL,
  `admin_name` varchar(25) DEFAULT NULL,
  `admin_email` varchar(25) NOT NULL,
  `admin_password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `am_admin`
--

INSERT INTO `am_admin` (`admin_id`, `admin_name`, `admin_email`, `admin_password`) VALUES
(201902021, 'Rudi Admin', 'morake@admin.com', 'admin'),
(20192020, 'Abel Admin', 'moremi@admin.com', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `am_assessment`
--

CREATE TABLE `am_assessment` (
  `ass_stu_id` int(11) NOT NULL,
  `ass_mod_id` varchar(6) NOT NULL,
  `ass_name` varchar(25) DEFAULT NULL,
  `ass_mak` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `am_assessment`
--

INSERT INTO `am_assessment` (`ass_stu_id`, `ass_mod_id`, `ass_name`, `ass_mak`) VALUES
(20190001, 'CSI131', 'Test 1', 45),
(20190001, 'CSI141', 'Test 1', 65);

-- --------------------------------------------------------

--
-- Table structure for table `am_course`
--

CREATE TABLE `am_course` (
  `crs_id` varchar(6) NOT NULL,
  `crs_name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `am_course`
--

INSERT INTO `am_course` (`crs_id`, `crs_name`) VALUES
('BSc205', 'Computing With Finance'),
('BSc270', 'Computer Information Syst'),
('BSc280', 'Computer Science'),
('BSc281', 'Information Technology');

-- --------------------------------------------------------

--
-- Table structure for table `am_courseregistration`
--

CREATE TABLE `am_courseregistration` (
  `crg_year` int(11) NOT NULL,
  `crg_crs_id` varchar(6) NOT NULL,
  `crg_stu_id` int(11) NOT NULL,
  `crg_date_registered` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `am_courseregistration`
--

INSERT INTO `am_courseregistration` (`crg_year`, `crg_crs_id`, `crg_stu_id`, `crg_date_registered`) VALUES
(0, 'BSc205', 20190001, '2019-10-26'),
(1, 'BSc280', 20190002, '2019-10-27'),
(1, 'BSc205', 20190003, '2019-10-28');

-- --------------------------------------------------------

--
-- Table structure for table `am_delivers`
--

CREATE TABLE `am_delivers` (
  `dev_semester` varchar(25) NOT NULL,
  `dev_lec_id` int(11) NOT NULL,
  `dev_mod_id` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `am_lecture`
--

CREATE TABLE `am_lecture` (
  `lec_id` int(11) NOT NULL,
  `lec_name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `am_module`
--

CREATE TABLE `am_module` (
  `mod_id` varchar(6) NOT NULL,
  `mod_name` varchar(25) DEFAULT NULL,
  `mod_pre` varchar(6) DEFAULT NULL,
  `mod_course` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `am_module`
--

INSERT INTO `am_module` (`mod_id`, `mod_name`, `mod_pre`, `mod_course`) VALUES
('ACC100', 'Accounting', 'none', 'BSc205'),
('COM141', 'Communication', 'none', 'BSc205'),
('CSI131', 'Discrete Structures', 'none', 'BSc205'),
('CSI131', 'Discrete Structures', 'none', 'BSc280'),
('CSI141', 'Intro to Programming', 'none', 'BSc205'),
('CSI141', 'intro to programming', 'none', 'BSc280'),
('CSI142', 'object programming', 'none', 'BSc280'),
('CSI161', 'Intro to Computing', 'none', 'BSc205'),
('CSI243', 'Functional Programming', 'none', 'BSc280'),
('CSI315', 'web technologies', 'none', 'BSc205'),
('CSI345', 'Artificial Intelligence', 'none', 'BSc280'),
('MAT111', 'Intro Mathematics', 'none', 'BSc205');

-- --------------------------------------------------------

--
-- Table structure for table `am_moduleregistration`
--

CREATE TABLE `am_moduleregistration` (
  `mrg_semester` varchar(25) NOT NULL,
  `mrg_stu_id` int(11) NOT NULL,
  `mrg_mod_id` varchar(6) NOT NULL,
  `mrg_date_registered` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `am_moduleregistration`
--

INSERT INTO `am_moduleregistration` (`mrg_semester`, `mrg_stu_id`, `mrg_mod_id`, `mrg_date_registered`) VALUES
('Semester 1', 20190001, 'CSI131', '2019-10-27'),
('Semester 1', 20190001, 'CSI141', '2019-10-27');

-- --------------------------------------------------------

--
-- Table structure for table `am_student`
--

CREATE TABLE `am_student` (
  `stu_id` int(11) NOT NULL,
  `stu_name` varchar(25) DEFAULT NULL,
  `stu_surname` varchar(25) NOT NULL,
  `stu_type` varchar(25) DEFAULT NULL,
  `stu_password` varchar(191) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `am_student`
--

INSERT INTO `am_student` (`stu_id`, `stu_name`, `stu_surname`, `stu_type`, `stu_password`) VALUES
(20190001, 'Tumelo', 'Mosepele', 'undergraduate', 'tumos'),
(20190002, 'Rudisang', 'Morake', 'undergraduate', 'mogey'),
(20190003, 'Abel', 'Moremi', 'undergraduate', 'abel');

-- --------------------------------------------------------

--
-- Table structure for table `am_university`
--

CREATE TABLE `am_university` (
  `uni_no` int(11) NOT NULL,
  `uni_name` varchar(25) DEFAULT NULL,
  `uni_city` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `am_admin`
--
ALTER TABLE `am_admin`
  ADD PRIMARY KEY (`admin_email`),
  ADD UNIQUE KEY `admin_id` (`admin_id`);

--
-- Indexes for table `am_assessment`
--
ALTER TABLE `am_assessment`
  ADD PRIMARY KEY (`ass_stu_id`,`ass_mod_id`),
  ADD KEY `FK_Ass_Module_id` (`ass_mod_id`);

--
-- Indexes for table `am_course`
--
ALTER TABLE `am_course`
  ADD PRIMARY KEY (`crs_id`);

--
-- Indexes for table `am_courseregistration`
--
ALTER TABLE `am_courseregistration`
  ADD PRIMARY KEY (`crg_stu_id`),
  ADD KEY `FK_Crg_Course_id` (`crg_crs_id`);

--
-- Indexes for table `am_delivers`
--
ALTER TABLE `am_delivers`
  ADD PRIMARY KEY (`dev_semester`,`dev_lec_id`,`dev_mod_id`),
  ADD KEY `FK_Dev_Lec_id` (`dev_lec_id`),
  ADD KEY `FK_Dev_Mod_id` (`dev_mod_id`);

--
-- Indexes for table `am_lecture`
--
ALTER TABLE `am_lecture`
  ADD PRIMARY KEY (`lec_id`);

--
-- Indexes for table `am_module`
--
ALTER TABLE `am_module`
  ADD PRIMARY KEY (`mod_id`,`mod_course`),
  ADD KEY `FK_Module_Course` (`mod_course`);

--
-- Indexes for table `am_moduleregistration`
--
ALTER TABLE `am_moduleregistration`
  ADD PRIMARY KEY (`mrg_stu_id`,`mrg_mod_id`),
  ADD KEY `FK_Mrg_Module_id` (`mrg_mod_id`);

--
-- Indexes for table `am_student`
--
ALTER TABLE `am_student`
  ADD PRIMARY KEY (`stu_id`);

--
-- Indexes for table `am_university`
--
ALTER TABLE `am_university`
  ADD PRIMARY KEY (`uni_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `am_admin`
--
ALTER TABLE `am_admin`
  MODIFY `admin_id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201902022;

--
-- AUTO_INCREMENT for table `am_student`
--
ALTER TABLE `am_student`
  MODIFY `stu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20190004;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `am_assessment`
--
ALTER TABLE `am_assessment`
  ADD CONSTRAINT `FK_Ass_Module_id` FOREIGN KEY (`ass_mod_id`) REFERENCES `am_module` (`mod_id`),
  ADD CONSTRAINT `FK_Ass_Student_id` FOREIGN KEY (`ass_stu_id`) REFERENCES `am_student` (`stu_id`);

--
-- Constraints for table `am_courseregistration`
--
ALTER TABLE `am_courseregistration`
  ADD CONSTRAINT `FK_Crg_Course_id` FOREIGN KEY (`crg_crs_id`) REFERENCES `am_course` (`crs_id`),
  ADD CONSTRAINT `FK_Crg_Student_id` FOREIGN KEY (`crg_stu_id`) REFERENCES `am_student` (`stu_id`);

--
-- Constraints for table `am_delivers`
--
ALTER TABLE `am_delivers`
  ADD CONSTRAINT `FK_Dev_Lec_id` FOREIGN KEY (`dev_lec_id`) REFERENCES `am_lecture` (`lec_id`),
  ADD CONSTRAINT `FK_Dev_Mod_id` FOREIGN KEY (`dev_mod_id`) REFERENCES `am_module` (`mod_id`);

--
-- Constraints for table `am_module`
--
ALTER TABLE `am_module`
  ADD CONSTRAINT `FK_Module_Course` FOREIGN KEY (`mod_course`) REFERENCES `am_course` (`crs_id`);

--
-- Constraints for table `am_moduleregistration`
--
ALTER TABLE `am_moduleregistration`
  ADD CONSTRAINT `FK_Mrg_Module_id` FOREIGN KEY (`mrg_mod_id`) REFERENCES `am_module` (`mod_id`),
  ADD CONSTRAINT `FK_Mrg_Student_id` FOREIGN KEY (`mrg_stu_id`) REFERENCES `am_student` (`stu_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
