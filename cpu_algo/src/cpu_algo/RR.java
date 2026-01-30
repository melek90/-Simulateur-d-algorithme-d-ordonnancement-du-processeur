package cpu_algo;

import java.util.*;


public class RR implements Ordenenceur {

    private int quantum;

    public RR(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public String Ordenenceur(List<Processus> p) {
        
        List<Processus> processes = new ArrayList<>();
        for (Processus proc : p) {
            processes.add(new Processus(proc));
        }
        
       
        processes.sort(Comparator.comparingInt(pr -> pr.temps_arr));
        
        Queue<Processus> readyQueue = new LinkedList<>();
        int currentTime = 0;
        int index = 0;
        int n = processes.size();
        
        
        Map<Integer, Integer> completionTime = new HashMap<>();
        Map<Integer, Integer> lastExecutionTime = new HashMap<>();
        
        StringBuilder gantt = new StringBuilder("========== Round Robin (Quantum = " + quantum + ") ==========\n\n");
        gantt.append("Diagramme de Gantt:\n");
        
        
        for (Processus proc : processes) {
            lastExecutionTime.put(proc.pid, proc.temps_arr);
        }
        
        while (!readyQueue.isEmpty() || index < n) {
            
            
            while (index < n && processes.get(index).temps_arr <= currentTime) {
                readyQueue.add(processes.get(index));
                index++;
            }
            
            
            if (readyQueue.isEmpty()) {
                if (index < n) {
                    currentTime = processes.get(index).temps_arr;
                }
                continue;
            }
            
            
            Processus current = readyQueue.poll();
            
           
            int executionTime = Math.min(quantum, current.temps_res);
            
            gantt.append("| P").append(current.pid).append("(").append(currentTime).append("-")
                 .append(currentTime + executionTime).append(") ");
            
            currentTime += executionTime;
            current.temps_res -= executionTime;
            
            
            while (index < n && processes.get(index).temps_arr <= currentTime) {
                readyQueue.add(processes.get(index));
                index++;
            }
            
            
            if (current.temps_res > 0) {
                readyQueue.add(current);
            } else {
                
                completionTime.put(current.pid, currentTime);
            }
        }
        
        gantt.append("|\n\n--- Resultats ---\n");
        
        
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        
        
        processes.sort(Comparator.comparingInt(pr -> pr.pid));
        
        for (Processus proc : processes) {
            int completion = completionTime.get(proc.pid);
            int turnaround = completion - proc.temps_arr;
            int waiting = turnaround - proc.temp_ex;
            
            totalWaitingTime += waiting;
            totalTurnaroundTime += turnaround;
            
            gantt.append("P").append(proc.pid)
                 .append(" | Temps d'attente = ").append(waiting)
                 .append(" | Temps de rotation = ").append(turnaround)
                 .append(" | Temps de completion = ").append(completion)
                 .append("\n");
        }
        
        
        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;
        
        gantt.append("\n--- Moyennes ---\n");
        gantt.append(String.format("Temps d'attente moyen = %.2f\n", avgWaitingTime));
        gantt.append(String.format("Temps de rotation moyen = %.2f\n", avgTurnaroundTime));
        gantt.append("Temps total d'execution = ").append(currentTime).append("\n");
        
        return gantt.toString();
    }
}