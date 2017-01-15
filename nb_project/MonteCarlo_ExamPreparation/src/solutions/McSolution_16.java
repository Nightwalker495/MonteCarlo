package solutions;

import OSPRNG.ExponentialRNG;
import montecarlo.IMcSolution;

public class McSolution_16 implements IMcSolution {

    private double waitingProb_;
    
    public McSolution_16() {
        waitingProb_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        ExponentialRNG rnd = new ExponentialRNG(100.0);
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            if (rnd.sample() > 180.0) {
                count++;
            }
        }
        
        waitingProb_ = (double) count / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("P(waiting > 3 min) = %.6f%%%n",
                waitingProb_ * 100.0);
    }
    
}
