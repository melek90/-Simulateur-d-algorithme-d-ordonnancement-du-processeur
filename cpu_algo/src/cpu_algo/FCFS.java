package cpu_algo;

import java.util.Comparator;
import java.util.List;


public class FCFS implements Ordenenceur {
    
    @Override
    public String Ordenenceur(List<Processus> p) {
        p.sort(Comparator.comparingInt(t -> t.temps_arr));
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        
        StringBuilder result = new StringBuilder("========== FCFS (First Come First Served) ==========\n\n");
        StringBuilder gantt = new StringBuilder("Diagramme de Gantt:\n");
        
        for (Processus process : p) {
            
            if (currentTime < process.temps_arr) {
                gantt.append("| innactif(").append(currentTime).append("-").append(process.temps_arr).append(") ");
                currentTime = process.temps_arr;
            }
            
            int startTime = currentTime;
            int waitingTime = currentTime - process.temps_arr;
            int turnaroundTime = waitingTime + process.temp_ex;
            currentTime += process.temp_ex;
            
            gantt.append("| P").append(process.pid).append("(").append(startTime).append("-").append(currentTime).append(") ");
            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
        }
        
        gantt.append("|\n\n");
        result.append(gantt);
        
        currentTime = 0;
        for (Processus process : p) {
            
            if (currentTime < process.temps_arr) {
                currentTime = process.temps_arr;
            }
            
  
            int waitingTime = currentTime - process.temps_arr;
            
            
            int turnaroundTime = waitingTime + process.temp_ex;
            
            
            currentTime += process.temp_ex;
            
            
            result.append("P").append(process.pid)
                  .append(" | Temps d'attente = ").append(waitingTime)
                  .append(" | Temps de rotation = ").append(turnaroundTime)
                  .append(" | Temps de completion = ").append(currentTime)
                  .append("\n");
        }
        
       
        double avgWaitingTime = (double) totalWaitingTime / p.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / p.size();
        result.append("\n--- Moyennes ---\n");
        result.append(String.format("Temps d'attente moyen = %.2f\n", avgWaitingTime));
        result.append(String.format("Temps de rotation moyen = %.2f\n", avgTurnaroundTime));
        
        return result.toString();
    }
}