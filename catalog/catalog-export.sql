-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 17, 2017 at 04:16 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.0.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `catalog`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `profesor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `name`, `profesor_id`) VALUES
(1, 'Fizica', 1),
(2, 'Mate', 2),
(3, 'Biologie', 3);

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `value` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`id`, `date`, `value`, `student_id`, `course_id`) VALUES
(1, '2017-08-16 00:00:00', 9, 1, 2),
(2, '2017-08-01 00:00:00', 8, 3, 2),
(3, '2017-08-01 00:00:00', 5, 5, 1),
(4, '2017-08-15 00:00:00', 4, 2, 2),
(5, '2017-08-07 00:00:00', 10, 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `profesor`
--

CREATE TABLE `profesor` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profesor`
--

INSERT INTO `profesor` (`id`, `name`) VALUES
(1, 'Cenusa'),
(2, 'Smarandoiu'),
(3, 'Popescu');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `student_group_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `first_name`, `last_name`, `student_group_id`) VALUES
(1, 'Ion', 'Ionescu', 1),
(2, 'Dan', 'Danulescu', 1),
(3, 'Elena', 'Elenescu', 1),
(4, 'Vasilica', 'Vasilescu', 1),
(5, 'Pop', 'Popescu', 1);

-- --------------------------------------------------------

--
-- Table structure for table `student_group`
--

CREATE TABLE `student_group` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_group`
--

INSERT INTO `student_group` (`id`, `name`) VALUES
(1, '12A'),
(2, '11C'),
(3, '10B');

-- --------------------------------------------------------

--
-- Table structure for table `student_group_course`
--

CREATE TABLE `student_group_course` (
  `student_group_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_group_course`
--

INSERT INTO `student_group_course` (`student_group_id`, `course_id`) VALUES
(1, 1),
(1, 2),
(1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlh538yur08ahuiblwc8c23rpj` (`profesor_id`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5secqnjjwgh9wxk4h1xwgj1n0` (`student_id`),
  ADD KEY `FK7e8ca7hfmrpruicqhocskjlf2` (`course_id`);

--
-- Indexes for table `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKovvvn9ce56xur7ws8bfdobr7d` (`student_group_id`);

--
-- Indexes for table `student_group`
--
ALTER TABLE `student_group`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_group_course`
--
ALTER TABLE `student_group_course`
  ADD KEY `FKpi8bjqv5lku8n8vs4jybijb8j` (`course_id`),
  ADD KEY `FK70lhxfadb5rlh1nmit7x5fjxo` (`student_group_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `FKlh538yur08ahuiblwc8c23rpj` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`);

--
-- Constraints for table `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `FK5secqnjjwgh9wxk4h1xwgj1n0` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `FK7e8ca7hfmrpruicqhocskjlf2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FKovvvn9ce56xur7ws8bfdobr7d` FOREIGN KEY (`student_group_id`) REFERENCES `student_group` (`id`);

--
-- Constraints for table `student_group_course`
--
ALTER TABLE `student_group_course`
  ADD CONSTRAINT `FK70lhxfadb5rlh1nmit7x5fjxo` FOREIGN KEY (`student_group_id`) REFERENCES `student_group` (`id`),
  ADD CONSTRAINT `FKpi8bjqv5lku8n8vs4jybijb8j` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
