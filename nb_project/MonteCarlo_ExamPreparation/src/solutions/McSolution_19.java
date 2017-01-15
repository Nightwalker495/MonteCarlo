package solutions;

import OSPRNG.ExponentialRNG;
import java.util.ArrayList;
import montecarlo.IMcSolution;

public class McSolution_19 implements IMcSolution {

    private static final int VARS_NUM = 100;
    
    private double result_;
    
    public McSolution_19() {
        result_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        ArrayList<ExponentialRNG> vars = new ArrayList<>();
        for (int i = 0; i < VARS_NUM; i++) {
            vars.add(new ExponentialRNG(0.5));
        }
        
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            double sum = 0.0;
            
            for (ExponentialRNG rnd : vars) {
                sum += rnd.sample();
            }
            
            if (sum <= 60.0) {
                count++;
            }
        }
        
        result_ = (double) count / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("P(sum <= 60) = %.4f%%.%n", result_ * 100.0);
    }
    
}
