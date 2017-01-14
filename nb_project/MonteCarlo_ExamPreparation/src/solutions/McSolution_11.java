package solutions;

import OSPRNG.UniformDiscreteRNG;
import montecarlo.IMcSolution;

public class McSolution_11 implements IMcSolution {

    private static final int NUMS_COUNT = 10;
    
    private double[] vals_;
    
    public McSolution_11() {
        vals_ = new double[NUMS_COUNT];
    }
    
    @Override
    public void run(int replsNum) {
        UniformDiscreteRNG rnd = new UniformDiscreteRNG(0, vals_.length - 1);
        
        for (int i = 0; i < replsNum; i++) {
            vals_[rnd.sample()]++;
        }
        
        for (int i = 0; i < vals_.length; i++) {
            vals_[i] /= (double) replsNum;
        }
    }

    @Override
    public String getReport() {
        StringBuilder str = new StringBuilder(String.format("Occurrences of "
                + "values from 0 to 9%n"));
        for (int i = 0; i < vals_.length; i++) {
            str.append(String.format("%d = %.4f%%%n", i + 1, vals_[i] * 100.0));
        }
        
        return str.toString();
    }
    
}
