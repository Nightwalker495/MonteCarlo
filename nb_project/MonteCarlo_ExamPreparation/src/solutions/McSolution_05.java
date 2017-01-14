package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_05 implements IMcSolution {

    private static final double FAIL_ITEM_PROB[] = {
        0.4, 0.3, 0.1
    };
    private static final double FAIL_COUNT_PROB[] = {
        0.0, 0.2, 0.7, 1.0
    };
    
    private double failProb_;
    
    public McSolution_05() {
        failProb_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformContinuousRNG rnd = new UniformContinuousRNG();
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            int failCount = 0;
            
            for (int j = 0; j < FAIL_ITEM_PROB.length; j++) {
                if (rnd.sample() < FAIL_ITEM_PROB[j]) {
                    failCount++;
                }
            }
            
            if (rnd.sample() < FAIL_COUNT_PROB[failCount]) {
                count++;
            }
        }
        
        failProb_ = (double) count / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("Probaility of system failure = %.10f%n",
                failProb_);
    }
    
}
