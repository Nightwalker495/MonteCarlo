package solutions;

import OSPRNG.NormalRNG;
import montecarlo.IMcSolution;

public class McSolution_17 implements IMcSolution {

    private static final double MAX_LOAD = 550.0;
    
    private double overloadProb_;
    
    public McSolution_17() {
        overloadProb_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        NormalRNG rnd = new NormalRNG(5.0, 1.5);
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            double load = 0.0;
            
            for (int j = 0; j < 100; j++) {
                load += Math.abs(rnd.sample());
            }
            
            if (load > MAX_LOAD) {
                count++;
            }
        }
        
        overloadProb_ = (double) count / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("P(overload) = %.10f%%%n", overloadProb_);
    }
    
}
