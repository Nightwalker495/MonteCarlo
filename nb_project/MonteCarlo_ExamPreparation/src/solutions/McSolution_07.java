package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_07 implements IMcSolution {
    
    private static final double BROKEN_CHIP_PROB = 0.003;
    private static final double DISCARD_IF_BROKEN_PROB = 0.98;
    private static final double DISCARD_IF_OK_PROB = 0.001;
    
    private double brokenAndDicardedProb_;
    private double averageDiscardPart_;
    
    public McSolution_07() {
        brokenAndDicardedProb_ = averageDiscardPart_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformContinuousRNG rnd = new UniformContinuousRNG();
        
        int discardedNum = 0;
        int discardedAndBrokenNum = 0;
        
        for (int i = 0; i < replsNum; i++) {
            if (rnd.sample() < BROKEN_CHIP_PROB) {
                if (rnd.sample() < DISCARD_IF_BROKEN_PROB) {
                    discardedAndBrokenNum++;
                    discardedNum++;
                }
            } else {
                if (rnd.sample() < DISCARD_IF_OK_PROB) {
                    discardedNum++;
                }
            }
        }
        
        brokenAndDicardedProb_ = (double) discardedAndBrokenNum /
                (double) discardedNum;
        averageDiscardPart_ = (double) discardedNum / (double) replsNum;
    }

    @Override
    public String getReport() {
        StringBuilder str = new StringBuilder(String.format("Probiability("
                + "broken | discarded) = %.10f%n", brokenAndDicardedProb_));
        str.append(String.format("Average # of broken products in "
                + "10 000 = %.2f%n", averageDiscardPart_ * 10_000));
        
        return str.toString();
    }
    
}
