-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 27 Jul 2016 pada 20.49
-- Versi Server: 5.7.13-0ubuntu0.16.04.2
-- PHP Version: 7.0.4-7ubuntu2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `customer`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `customers`
--

CREATE TABLE `customers` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `birth_date` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data untuk tabel `customers`
--

INSERT INTO `customers` (`id`, `name`, `birth_date`, `gender`, `phone_number`, `created_at`, `updated_at`) VALUES
(1, 'Arne Gorczany', '19-05-1999', 'Male', '425.599.6256', '2016-07-27 06:46:53', '2016-07-27 06:46:53'),
(2, 'Otilia Jacobson', '19-05-1999', 'Male', '427.843.7422 x8104', '2016-07-27 06:46:53', '2016-07-27 06:46:53'),
(3, 'Monique Leffler', '19-05-1999', 'Male', '883.674.0539 x61169', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(4, 'Prof. Wade Shanahan', '19-05-1999', 'Male', '+1-364-731-5808', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(5, 'Laverne Stokes I', '19-05-1999', 'Male', '271-791-5903', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(6, 'Delilah Greenholt', '19-05-1999', 'Male', '(890) 630-2541 x2543', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(7, 'Hilma Wyman', '19-05-1999', 'Male', '408.875.1537 x045', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(8, 'Rocky Schumm', '19-05-1999', 'Male', '(615) 390-1027', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(9, 'Jeanie Wintheiser', '19-05-1999', 'Male', '1-548-552-9650', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(10, 'Mr. Hadley Lemke I', '19-05-1999', 'Male', '873-971-8921', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(11, 'Magnus Monahan', '19-05-1999', 'Male', '+1.501.999.1375', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(12, 'Kailee Conroy', '19-05-1999', 'Male', '379-478-8295 x83265', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(13, 'Katlyn Koelpin V', '19-05-1999', 'Male', '(785) 930-1988 x4187', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(14, 'Hanna Kuphal', '19-05-1999', 'Male', '(582) 478-6733', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(15, 'Bell Maggio', '19-05-1999', 'Male', '+1.520.948.2685', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(16, 'Prof. Maxie Schinner', '19-05-1999', 'Male', '975.727.8124 x195', '2016-07-27 06:46:54', '2016-07-27 06:46:54'),
(17, 'Dereck Berge Jr.', '19-05-1999', 'Male', '930.401.2952 x353', '2016-07-27 06:46:55', '2016-07-27 06:46:55'),
(18, 'Foster Heathcote', '19-05-1999', 'Male', '1-835-867-6636', '2016-07-27 06:46:55', '2016-07-27 06:46:55'),
(19, 'Dandre White', '19-05-1999', 'Male', '546-714-9733 x9871', '2016-07-27 06:46:55', '2016-07-27 06:46:55'),
(20, 'Alfreda Rice', '19-05-1999', 'Male', '372.729.2276 x5534', '2016-07-27 06:46:55', '2016-07-27 06:46:55');

-- --------------------------------------------------------

--
-- Struktur dari tabel `migrations`
--

CREATE TABLE `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data untuk tabel `migrations`
--

INSERT INTO `migrations` (`migration`, `batch`) VALUES
('2014_10_12_000000_create_users_table', 1),
('2014_10_12_100000_create_password_resets_table', 1),
('2016_07_25_040822_create_customers_table', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`),
  ADD KEY `password_resets_token_index` (`token`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
