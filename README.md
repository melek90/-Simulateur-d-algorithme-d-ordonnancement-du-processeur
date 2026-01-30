# Simulateur-d-algorithme-d-ordonnancement-du-processeur

Algorithmes d’Ordonnancement : FCFS, SJF, SRT et Round Robin
 Fonctionnalités principales

Mesures complètes : Calcul du temps d’attente, du temps de rotation (turnaround time) et des moyennes

Interface conviviale : Saisie interactive via la console

Mode comparaison : Exécution simultanée de tous les algorithmes pour comparaison

Gestion des erreurs : Validation robuste des entrées utilisateur

Sortie détaillée : Visualisation claire de l’exécution des processus

Algorithmes implémentés
1. FCFS (First Come First Served)

Type : Non préemptif

Description : Les processus sont exécutés dans l’ordre de leur arrivée

Avantages :

Simple à implémenter

Équitable selon l’ordre d’arrivée

Inconvénients :

Mauvais temps d’attente moyen

Effet de convoi (un long processus bloque les suivants)

2. SJF (Shortest Job First)

Type : Non préemptif

Description : Le processus ayant le temps d’exécution le plus court est exécuté en premier

Avantages :

Minimise le temps d’attente moyen

Inconvénients :

Risque de famine des processus longs

Nécessite de connaître à l’avance les temps d’exécution

3. SRT (Shortest Remaining Time)

Type : Préemptif

Description : Version préemptive de SJF — le processeur bascule vers un processus plus court s’il arrive

Avantages :

Temps d’attente moyen meilleur que SJF

Inconvénients :

Plus de commutations de contexte

Possibilité de famine

4. Round Robin (RR)

Type : Préemptif

Description : Chaque processus reçoit un quantum de temps fixe, en ordre circulaire

Avantages :

Équitable

Pas de famine

Adapté aux systèmes à temps partagé

Inconvénients :

Les performances dépendent fortement de la taille du quantum

Augmentation des commutations de contexte

# Remarques

Toutes les valeurs de temps sont exprimées en unités de temps arbitraires

Les identifiants des processus (PID) doivent être uniques pour plus de clarté

Le quantum de l’algorithme Round Robin doit être choisi avec soin :

Trop petit : commutations de contexte excessives

Trop grand : comportement proche de l’algorithme FCFS

 # Objectifs pédagogiques

Ce projet permet de mieux comprendre :

Les concepts d’ordonnancement du processeur (CPU scheduling)

La différence entre l’ordonnancement préemptif et non préemptif

Les mesures de performance :

Temps d’attente

Temps de rotation (turnaround time)

Les compromis entre les différents algorithmes d’ordonnancement

La programmation en Java en utilisant les concepts de la programmation orientée objet (POO)
