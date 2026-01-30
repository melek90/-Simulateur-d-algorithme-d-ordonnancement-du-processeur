package cpu_algo;


public class Processus {
    public int pid;           
    public int temps_arr;     
    public int temp_ex;       
    public int temps_res;     
    

    public Processus(int pid, int temps_arr, int temp_ex) {
        this.pid = pid;
        this.temps_arr = temps_arr;
        this.temp_ex = temp_ex;
        this.temps_res = temp_ex; 
    }
    

    public Processus(Processus p) {
        this.pid = p.pid;
        this.temps_arr = p.temps_arr;
        this.temp_ex = p.temp_ex;
        this.temps_res = p.temps_res;
    }
}