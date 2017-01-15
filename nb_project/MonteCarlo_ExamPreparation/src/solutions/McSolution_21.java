package solutions;

import OSPRNG.UniformDiscreteRNG;
import montecarlo.IMcSolution;

public class McSolution_21 implements IMcSolution {

    private static final int ITEMS_NUM = 10;
    private static final int WORST_CASE_STEPS = ITEMS_NUM * ITEMS_NUM;
    private static final int BEST_CASE_STEPS = ITEMS_NUM;
    
    private double worstCaseRatio_;
    private double bestCaseRatio_;
    private double averageStepsNum_;
    private int maxStepsNum_;
    private int minStepsNum_;
    
    public McSolution_21() {
        worstCaseRatio_ = bestCaseRatio_ = averageStepsNum_ = 0.0;
        maxStepsNum_ = minStepsNum_ = 0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformDiscreteRNG rnd = new UniformDiscreteRNG(0, ITEMS_NUM << 1);
        int[] vals = new int[ITEMS_NUM];
        
        int worstCaseNum = 0;
        int bestCaseNum = 0;
        int stepsTotal = 0;
        minStepsNum_ = WORST_CASE_STEPS;
        maxStepsNum_ = BEST_CASE_STEPS;
        
        for (int i = 0; i < replsNum; i++) {
            for (int j = 0; j < vals.length; j++) {
                vals[j] = rnd.sample();
            }
            
            int steps = 1;
            
            boolean swapped;
            do {
                swapped = false;
                
                for (int j = 1; j < vals.length; j++) {
                    steps++;
                    
                    int pos = j - 1;
                    if (vals[pos] > vals[j]) {
                        int tmp = vals[pos];
                        vals[pos] = vals[j];
                        vals[j] = tmp;
                        
                        swapped = true;
                    }
                }
            } while (swapped);
            
            stepsTotal += steps;
            if (steps == WORST_CASE_STEPS) {
                worstCaseNum++;
            } else if (steps == BEST_CASE_STEPS) {
                bestCaseNum++;
            }
            
            if (steps < minStepsNum_) {
                minStepsNum_ = steps;
            }
            if (steps > maxStepsNum_) {
                maxStepsNum_ = steps;
            }
        }
        
        worstCaseRatio_ = (double) worstCaseNum / (double) replsNum;
        bestCaseRatio_ = (double) bestCaseNum / (double) replsNum;
        averageStepsNum_ = (double) stepsTotal / (double) replsNum;
    }

    @Override
    public String getReport() {
        String res = String.format("Bubble Sort Statistics%n");
        res += String.format("\tNumber of items = %d.%n", ITEMS_NUM);
        res += String.format("\tWorst case ratio = %.4f%%.%n",
                worstCaseRatio_ * 100.0);
        res += String.format("\tBest case ratio = %.4f%%.%n",
                bestCaseRatio_ * 100.0);
        res += String.format("\tAverage steps no. = %.2f.%n",
                averageStepsNum_);
        res += String.format("\tMax. steps no. = %d.%n",
                maxStepsNum_);
        res += String.format("\tMin. steps no. = %d.%n",
                minStepsNum_);
        
        return res;
    }
    
}
