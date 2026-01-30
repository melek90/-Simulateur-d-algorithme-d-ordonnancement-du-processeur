package cpu_algo;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("SIMULATEUR D'ORDONNANCEMENT CPU");
        System.out.println();
        int nbre = 0;
        while (nbre <= 0) {
            System.out.print("Nombre de processus : ");
            try {
                nbre = in.nextInt();
                if (nbre <= 0) {
                    System.out.println("Erreur : Veuillez entrer un nombre positif.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                in.next(); 
            }
        }
        List<Processus> processes = new ArrayList<>();
        for (int i = 0; i < nbre; i++) {
            System.out.println("--- Processus " + (i + 1) + " ---");   
            int pid = 0;
            while (pid <= 0) {
                System.out.print("ID du processus : ");
                try {
                    pid = in.nextInt();
                    if (pid <= 0) {
                        System.out.println("Erreur : L'ID doit être positif.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez entrer un nombre valide.");
                    in.next();
                }
            }

            int temps_arr = -1;
            while (temps_arr < 0) {
                System.out.print("Temps d'arrivée : ");
                try {
                    temps_arr = in.nextInt();
                    if (temps_arr < 0) {
                        System.out.println("Erreur : Le temps d'arrivée ne peut pas être négatif.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez entrer un nombre valide.");
                    in.next();
                }
            }

            int temp_ex = 0;
            while (temp_ex <= 0) {
                System.out.print("Temps d'exécution : ");
                try {
                    temp_ex = in.nextInt();
                    if (temp_ex <= 0) {
                        System.out.println("Erreur : Le temps d'exécution doit être positif.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez entrer un nombre valide.");
                    in.next();
                }
            }
            processes.add(new Processus(pid, temps_arr, temp_ex));
        }

        System.out.println("CHOISIR UN ALGORITHME D'ORDONNANCEMENT");
        System.out.println("1. FCFS (First Come First Served)");
        System.out.println("2. SJF  (Shortest Job First)");
        System.out.println("3. SRT  (Shortest Remaining Time)");
        System.out.println("4. RR   (Round Robin)");
        System.out.println("5. TOUS LES ALGORITHMES (Comparaison)");
        
        int choix = 0;
        while (choix < 1 || choix > 5) {
            System.out.print("Votre choix (1-5) : ");
            try {
                choix = in.nextInt();
                if (choix < 1 || choix > 5) {
                    System.out.println("Erreur : Veuillez choisir entre 1 et 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                in.next();
            }
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println();

        Ordenenceur scheduler;

        switch (choix) {
            case 1:
                scheduler = new FCFS();
                System.out.println(scheduler.Ordenenceur(new ArrayList<>(processes)));
                break;

            case 2:
                scheduler = new SJF();
                System.out.println(scheduler.Ordenenceur(new ArrayList<>(processes)));
                break;

            case 3:
                scheduler = new SRT();
                System.out.println(scheduler.Ordenenceur(new ArrayList<>(processes)));
                break;
            case 4:
                int quantum = 0;
                while (quantum <= 0) {
                    System.out.print("Quantum de temps : ");
                    try {
                        quantum = in.nextInt();
                        if (quantum <= 0) {
                            System.out.println("Erreur : Le quantum doit être positif.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer un nombre valide.");
                        in.next();
                    }
                }
                System.out.println();
                scheduler = new RR(quantum);
                System.out.println(scheduler.Ordenenceur(new ArrayList<>(processes)));
                break;
            case 5: 
                System.out.println("COMPARAISON DE TOUS LES ALGORITHMES");
                
                System.out.println();
                
                scheduler = new FCFS();
                System.out.println(scheduler.Ordenenceur(new ArrayList<>(processes)));
                System.out.println("\n" + "=".repeat(50) + "\n");
                
                scheduler = new SJF();
                System.out.println(scheduler.Ordenenceur(new ArrayList<>(processes)));
                System.out.println("\n" + "=".repeat(50) + "\n");
                scheduler = new SRT();
                System.out.println(scheduler.Ordenenceur(new ArrayList<>(processes)));
                System.out.println("\n" + "=".repeat(50) + "\n");
                int defaultQuantum = 2;
                System.out.println("(Utilisation d'un quantum par défaut de " + defaultQuantum + " pour Round Robin)");
                scheduler = new RR(defaultQuantum);
                System.out.println(scheduler.Ordenenceur(new ArrayList<>(processes)));
                break;

            default:
                System.out.println("Erreur : Choix invalide.");
        }

        in.close();
        
        System.out.println("SIMULATION TERMINÉE");
        
    }
}