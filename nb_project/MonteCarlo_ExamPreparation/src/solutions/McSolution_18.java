package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_18 implements IMcSolution {

    private static int COMMUTES_IN_SEM = 104; // 13 * 4 * 2
    
    private double waitingProb_;
    
    public McSolution_18() {
        waitingProb_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformContinuousRNG rnd = new UniformContinuousRNG(0.0, 8.0);
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            double sumMins = 0.0;
            
            for (int j = 0; j < COMMUTES_IN_SEM; j++) {
                sumMins += rnd.sample();
            }
            
            if (sumMins <= 360.0) {
                count++;
            }
        }
        
        waitingProb_ = (double) count / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("P(waiting <= 6 hours in a semester) = %.8f%%.%n",
                waitingProb_ * 100.0);
    }
    
}
