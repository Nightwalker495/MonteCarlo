package solutions;

import java.util.ArrayList;
import montecarlo.IMcSolution;

public class McSolution_22 implements IMcSolution {

    private static final int ITEMS_NUM = 32;
    
    private double worstCaseRatio_;
    private double bestCaseRatio_;
    private double aveageStepsNum_;
    private int minStepsNum_;
    private int maxStepsNum_;
    
    public McSolution_22() {
        worstCaseRatio_ = bestCaseRatio_ = aveageStepsNum_ = 0.0;
        minStepsNum_ = maxStepsNum_ = 0;
    }
    
    @Override
    public void run(int replsNum) {
        ArrayList<Integer> vals = new ArrayList<>();
        
        for (int i = 0; i < replsNum; i++) {
            int maxValGenerated = 0;
            
            vals.clear();
        }
    }

    @Override
    public String getReport() {
        String str = String.format("Binary Search Statistics%n");
        str += String.format("\tArrya item no. = %d.%n", ITEMS_NUM);
        str += String.format("\tBest case ratio = %.4f%%.%n", bestCaseRatio_);
        str += String.format("\tWorst case ratio = %.4f%%.%n",
                worstCaseRatio_);
        str += String.format("\tAverage steps no. = %.2f.%n",
                aveageStepsNum_);
        str += String.format("\tMin. steps no. = %d.%n", minStepsNum_);
        str += String.format("\tMax. steps no. = %d.%n", maxStepsNum_);
        
        return str;
    }
    
}
