package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_15 implements IMcSolution {

    private double waitingLongerProb_;
    
    public McSolution_15() {
        waitingLongerProb_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformContinuousRNG rnd = new UniformContinuousRNG(0.0, 10.0);
        
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            if (rnd.sample() > 3.0) {
                count++;
            }
        }
        
        waitingLongerProb_ = (double) count / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("P(waiting > 3 mins) = %.10f%%%n",
                waitingLongerProb_ * 100.0);
    }
    
}
