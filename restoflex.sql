-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 24 fév. 2022 à 21:05
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `restoflex`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id_categorie` int(11) NOT NULL,
  `nom_categorie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_cmd` int(11) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `date_cmd` date NOT NULL,
  `date_livraison` date NOT NULL,
  `quantite` float NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `id_produit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `conge`
--

CREATE TABLE `conge` (
  `id_conge` int(11) NOT NULL,
  `date_deb` date NOT NULL,
  `date_fin` date NOT NULL,
  `solde_restant` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `id_type_conge` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `num_facture` int(11) NOT NULL,
  `total` float NOT NULL,
  `statut` varchar(255) NOT NULL,
  `id_fournisseur` int(11) NOT NULL,
  `id_rest` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id_fournisseur` int(11) NOT NULL,
  `nom_fournisseur` varchar(255) NOT NULL,
  `matricule_fiscale` varchar(255) NOT NULL,
  `domaine_fournisseur` varchar(255) NOT NULL,
  `num_tel_fournisseur` varchar(255) NOT NULL,
  `email_fournisseur` varchar(255) NOT NULL,
  `adresse_fournisseur` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur_restaurant`
--

CREATE TABLE `fournisseur_restaurant` (
  `id_fournisseur_restaurant` int(11) NOT NULL,
  `id_rest` int(11) NOT NULL,
  `id_fournisseur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `produit_restaurant`
--

CREATE TABLE `produit_restaurant` (
  `id_pdtrest` int(11) NOT NULL,
  `nom_pdt` varchar(255) NOT NULL,
  `quantite_pdt` float NOT NULL,
  `id_rest` int(11) NOT NULL,
  `id_sctg` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `num_reclamation` int(11) NOT NULL,
  `destinataire` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `statut_reclamation` varchar(255) NOT NULL,
  `date_reclamation` date NOT NULL,
  `id_type_reclamation` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `restaurant`
--

CREATE TABLE `restaurant` (
  `id_rest` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `specialite` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `num_tel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `restaurant`
--

INSERT INTO `restaurant` (`id_rest`, `nom`, `specialite`, `adresse`, `email`, `num_tel`) VALUES
(2, 'baila', 'pizzaria', 'ariana', 'bail@gmail.com', '95379411');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `nom_role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id_role`, `nom_role`) VALUES
(1, 'Gérant'),
(2, 'Employée'),
(3, 'Utilisateur_fournisseur');

-- --------------------------------------------------------

--
-- Structure de la table `sous_ctg`
--

CREATE TABLE `sous_ctg` (
  `id_sctg` int(11) NOT NULL,
  `nom_sctg` varchar(255) NOT NULL,
  `id_categorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `id_stock` int(11) NOT NULL,
  `nom_stock` varchar(255) NOT NULL,
  `prix_unitaire` float NOT NULL,
  `quantite` float NOT NULL,
  `id_fournisseur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `type_conge`
--

CREATE TABLE `type_conge` (
  `id_type_conge` int(11) NOT NULL,
  `nom_type_conge` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `type_reclamation`
--

CREATE TABLE `type_reclamation` (
  `id_type_reclamation` int(11) NOT NULL,
  `nom_type_reclamation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `cin` varchar(8) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `num_tel` varchar(8) NOT NULL,
  `date_naissance` date NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `salaire` int(11) DEFAULT NULL,
  `solde_conge` int(11) DEFAULT NULL,
  `poste_employe` varchar(255) DEFAULT NULL,
  `id_role` int(11) NOT NULL,
  `id_rest` int(11) NOT NULL,
  `id_fournisseur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom`, `prenom`, `cin`, `mdp`, `email`, `num_tel`, `date_naissance`, `adresse`, `salaire`, `solde_conge`, `poste_employe`, `id_role`, `id_rest`, `id_fournisseur`) VALUES
(3, 'anis', 'benhmida', '00258421', 'anisbenhmida', 'anis@gmail.com', '55376927', '1998-05-02', 'zahra', 1500, 25, 'terrase', 2, 2, NULL),
(4, 'amir', 'bouras', '07477323', 'amirbouras', 'amir@gmail.com', '95379411', '1998-05-02', 'hedi noira', 1200, 15, 'cuisine', 2, 2, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_categorie`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_cmd`),
  ADD KEY `id_utilisateur_fk` (`id_utilisateur`),
  ADD KEY `id_prod_cmd_fk` (`id_produit`);

--
-- Index pour la table `conge`
--
ALTER TABLE `conge`
  ADD PRIMARY KEY (`id_conge`),
  ADD KEY `id_utilisateur_cong_fk` (`id_utilisateur`),
  ADD KEY `id_conge_fk` (`id_type_conge`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`num_facture`),
  ADD KEY `id_fournisseur_fk` (`id_fournisseur`),
  ADD KEY `id_rest_fk` (`id_rest`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id_fournisseur`);

--
-- Index pour la table `fournisseur_restaurant`
--
ALTER TABLE `fournisseur_restaurant`
  ADD PRIMARY KEY (`id_fournisseur_restaurant`),
  ADD KEY `id_fournisseur_res_fk` (`id_fournisseur`),
  ADD KEY `id_restaurant_four_fk` (`id_rest`);

--
-- Index pour la table `produit_restaurant`
--
ALTER TABLE `produit_restaurant`
  ADD PRIMARY KEY (`id_pdtrest`),
  ADD KEY `id_ss_categ_fk` (`id_sctg`),
  ADD KEY `id_res_produit_fk` (`id_rest`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`num_reclamation`),
  ADD KEY `id_type_reclamation_FK` (`id_type_reclamation`),
  ADD KEY `id_uti_recl_fk` (`id_utilisateur`);

--
-- Index pour la table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`id_rest`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Index pour la table `sous_ctg`
--
ALTER TABLE `sous_ctg`
  ADD PRIMARY KEY (`id_sctg`),
  ADD KEY `id_categ_fk` (`id_categorie`);

--
-- Index pour la table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id_stock`),
  ADD KEY `id_stock_fk` (`id_fournisseur`);

--
-- Index pour la table `type_conge`
--
ALTER TABLE `type_conge`
  ADD PRIMARY KEY (`id_type_conge`);

--
-- Index pour la table `type_reclamation`
--
ALTER TABLE `type_reclamation`
  ADD PRIMARY KEY (`id_type_reclamation`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`),
  ADD KEY `id_role_util_fk` (`id_role`),
  ADD KEY `id_rest_util_fk` (`id_rest`),
  ADD KEY `id_four_util_fk` (`id_fournisseur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_categorie` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_cmd` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `conge`
--
ALTER TABLE `conge`
  MODIFY `id_conge` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `num_facture` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id_fournisseur` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `fournisseur_restaurant`
--
ALTER TABLE `fournisseur_restaurant`
  MODIFY `id_fournisseur_restaurant` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit_restaurant`
--
ALTER TABLE `produit_restaurant`
  MODIFY `id_pdtrest` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `num_reclamation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `id_rest` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `sous_ctg`
--
ALTER TABLE `sous_ctg`
  MODIFY `id_sctg` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `stock`
--
ALTER TABLE `stock`
  MODIFY `id_stock` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `type_conge`
--
ALTER TABLE `type_conge`
  MODIFY `id_type_conge` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `type_reclamation`
--
ALTER TABLE `type_reclamation`
  MODIFY `id_type_reclamation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `id_prod_cmd_fk` FOREIGN KEY (`id_produit`) REFERENCES `produit_restaurant` (`id_pdtrest`),
  ADD CONSTRAINT `id_utilisateur_fk` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `conge`
--
ALTER TABLE `conge`
  ADD CONSTRAINT `id_conge_fk` FOREIGN KEY (`id_type_conge`) REFERENCES `type_conge` (`id_type_conge`),
  ADD CONSTRAINT `id_utilisateur_cong_fk` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `id_fournisseur_fk` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`),
  ADD CONSTRAINT `id_rest_fk` FOREIGN KEY (`id_rest`) REFERENCES `restaurant` (`id_rest`);

--
-- Contraintes pour la table `fournisseur_restaurant`
--
ALTER TABLE `fournisseur_restaurant`
  ADD CONSTRAINT `id_fournisseur_res_fk` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`),
  ADD CONSTRAINT `id_restaurant_four_fk` FOREIGN KEY (`id_rest`) REFERENCES `restaurant` (`id_rest`);

--
-- Contraintes pour la table `produit_restaurant`
--
ALTER TABLE `produit_restaurant`
  ADD CONSTRAINT `id_res_produit_fk` FOREIGN KEY (`id_rest`) REFERENCES `restaurant` (`id_rest`),
  ADD CONSTRAINT `id_ss_categ_fk` FOREIGN KEY (`id_sctg`) REFERENCES `sous_ctg` (`id_sctg`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `id_type_reclamation_FK` FOREIGN KEY (`id_type_reclamation`) REFERENCES `type_reclamation` (`id_type_reclamation`),
  ADD CONSTRAINT `id_uti_recl_fk` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `sous_ctg`
--
ALTER TABLE `sous_ctg`
  ADD CONSTRAINT `id_categ_fk` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`);

--
-- Contraintes pour la table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `id_stock_fk` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `id_four_util_fk` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`),
  ADD CONSTRAINT `id_rest_util_fk` FOREIGN KEY (`id_rest`) REFERENCES `restaurant` (`id_rest`),
  ADD CONSTRAINT `id_role_util_fk` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
