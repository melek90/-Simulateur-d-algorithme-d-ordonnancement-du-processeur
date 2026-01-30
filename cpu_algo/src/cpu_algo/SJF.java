package cpu_algo;

import java.util.*;


public class SJF implements Ordenenceur {

    @Override
    public String Ordenenceur(List<Processus> p) {
       
        List<Processus> processes = new ArrayList<>();
        for (Processus proc : p) {
            processes.add(new Processus(proc));
        }
        
        int currentTime = 0;
        int completed = 0;
        int n = processes.size();
        
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        
        boolean[] isCompleted = new boolean[n];
        
        StringBuilder result = new StringBuilder("========== SJF (Shortest Job First) ==========\n\n");
        StringBuilder gantt = new StringBuilder("Diagramme de Gantt:\n");
        
        while (completed < n) {
            
            int shortestIndex = -1;
            int shortestTime = Integer.MAX_VALUE;
            
            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && processes.get(i).temps_arr <= currentTime) {
                    if (processes.get(i).temp_ex < shortestTime) {
                        shortestTime = processes.get(i).temp_ex;
                        shortestIndex = i;
                    }
                    
                    else if (processes.get(i).temp_ex == shortestTime) {
                        if (processes.get(i).temps_arr < processes.get(shortestIndex).temps_arr) {
                            shortestIndex = i;
                        }
                    }
                }
            }
            
            
            if (shortestIndex == -1) {
                int nextArrival = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!isCompleted[i] && processes.get(i).temps_arr > currentTime) {
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
            
            Processus current = processes.get(shortestIndex);
            int startTime = currentTime;
            currentTime += current.temp_ex;
            
            gantt.append("| P").append(current.pid).append("(").append(startTime).append("-").append(currentTime).append(") ");
            
            isCompleted[shortestIndex] = true;
            completed++;
        }
        
        gantt.append("|\n\n");
        result.append(gantt);
        
        Arrays.fill(isCompleted, false);
        currentTime = 0;
        completed = 0;
        
        while (completed < n) {
            
            int shortestIndex = -1;
            int shortestTime = Integer.MAX_VALUE;
            
            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && processes.get(i).temps_arr <= currentTime) {
                    if (processes.get(i).temp_ex < shortestTime) {
                        shortestTime = processes.get(i).temp_ex;
                        shortestIndex = i;
                    }
                    
                    else if (processes.get(i).temp_ex == shortestTime) {
                        if (processes.get(i).temps_arr < processes.get(shortestIndex).temps_arr) {
                            shortestIndex = i;
                        }
                    }
                }
            }
            
            
            if (shortestIndex == -1) {
                currentTime++;
                continue;
            }
            
            Processus current = processes.get(shortestIndex);
            

            int waitingTime = currentTime - current.temps_arr;
            
            
            currentTime += current.temp_ex;
            
            
            int turnaroundTime = currentTime - current.temps_arr;
            
            
            isCompleted[shortestIndex] = true;
            completed++;
            
            
            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
            
            
            result.append("P").append(current.pid)
                  .append(" | Temps d'attente = ").append(waitingTime)
                  .append(" | Temps de rotation = ").append(turnaroundTime)
                  .append(" | Temps de completion = ").append(currentTime)
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