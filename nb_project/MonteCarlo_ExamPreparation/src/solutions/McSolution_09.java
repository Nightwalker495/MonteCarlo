package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_09 implements IMcSolution {

    private static final double VOLUME = 800.0; // 2 * 5 * 80
    
    private double integralRes_;

    public McSolution_09() {
        integralRes_ = 0.0;
    }

    @Override
    public void run(int replsNum) {
        UniformContinuousRNG rndX = new UniformContinuousRNG(0.0, 5.0);
        UniformContinuousRNG rndY = new UniformContinuousRNG(2.0, 4.0);
        UniformContinuousRNG rndFuncVal = new UniformContinuousRNG(0.0, 80.0);
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            if (rndFuncVal.sample() <= evalFunc(rndX.sample(), rndY.sample())) {
                count++;
            }
        }
        
        integralRes_ = ((double) count / (double) replsNum) * VOLUME;
    }

    @Override
    public String getReport() {
        return String.format("Integral result = %.10f%n", integralRes_);
    }

    private static double evalFunc(double x, double y) {
        return (x * x) * y;
    }
}
