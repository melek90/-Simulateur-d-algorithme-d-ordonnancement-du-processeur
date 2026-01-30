package cpu_algo;

import java.util.*;


public class SRT implements Ordenenceur {
    
    @Override
    public String Ordenenceur(List<Processus> p) {
        
        List<Processus> processes = new ArrayList<>();
        for (Processus proc : p) {
            processes.add(new Processus(proc));
        }
        
        int currentTime = 0;
        int completed = 0;
        int n = processes.size();
        
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        
        StringBuilder result = new StringBuilder("========== SRT (Shortest Remaining Time) ==========\n\n");
        StringBuilder gantt = new StringBuilder("Diagramme de Gantt:\n");
        
        int lastProcess = -1;
        int lastStartTime = 0;
        
        while (completed < n) {
            
            Processus shortest = null;
            int shortestIndex = -1;
            
            for (int i = 0; i < n; i++) {
                Processus proc = processes.get(i);
                if (proc.temps_arr <= currentTime && proc.temps_res > 0) {
                    if (shortest == null || proc.temps_res < shortest.temps_res) {
                        shortest = proc;
                        shortestIndex = i;
                    }
                    
                    else if (proc.temps_res == shortest.temps_res && proc.temps_arr < shortest.temps_arr) {
                        shortest = proc;
                        shortestIndex = i;
                    }
                }
            }
            
            
            if (shortest == null) {
                if (lastProcess != -1) {
                    gantt.append("| P").append(processes.get(lastProcess).pid)
                         .append("(").append(lastStartTime).append("-").append(currentTime).append(") ");
                    lastProcess = -1;
                }
                
                int nextArrival = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (processes.get(i).temps_res > 0 && processes.get(i).temps_arr > currentTime) {
                        nextArrival = Math.min(nextArrival, processes.get(i).temps_arr);
                    }
                }
                
                if (nextArrival != Integer.MAX_VALUE) {
                    gantt.append("| Idle(").append(currentTime).append("-").append(nextArrival).append(") ");
                    currentTime = nextArrival;
                } else {
                    currentTime++;
                }
                continue;
            }
            
            if (lastProcess != -1 && lastProcess != shortestIndex) {
                gantt.append("| P").append(processes.get(lastProcess).pid)
                     .append("(").append(lastStartTime).append("-").append(currentTime).append(") ");
                lastStartTime = currentTime;
            } else if (lastProcess == -1) {
                lastStartTime = currentTime;
            }
            
            lastProcess = shortestIndex;
            currentTime++;
            shortest.temps_res--;
            
            
            if (shortest.temps_res == 0) {
                gantt.append("| P").append(shortest.pid)
                     .append("(").append(lastStartTime).append("-").append(currentTime).append(") ");
                lastProcess = -1;
                
                completed++;
                completionTime[shortestIndex] = currentTime;
                turnaroundTime[shortestIndex] = currentTime - shortest.temps_arr;
                waitingTime[shortestIndex] = turnaroundTime[shortestIndex] - shortest.temp_ex;
            }
        }
        
        gantt.append("|\n\n");
        result.append(gantt);
        
        
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        
        
        List<Integer> sortedIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sortedIndices.add(i);
        }
        sortedIndices.sort(Comparator.comparingInt(i -> processes.get(i).pid));
        
        for (int i : sortedIndices) {
            Processus proc = processes.get(i);
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
            
            result.append("P").append(proc.pid)
                  .append(" | Temps d'attente = ").append(waitingTime[i])
                  .append(" | Temps de rotation = ").append(turnaroundTime[i])
                  .append(" | Temps de completion = ").append(completionTime[i])
                  .append("\n");
        }
        
        
        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;
        
        result.append("\n--- Moyennes ---\n");
        result.append(String.format("Temps d'attente moyen = %.2f\n", avgWaitingTime));
        result.append(String.format("Temps de rotation moyen = %.2f\n", avgTurnaroundTime));
        
        return result.toString();
    }
}