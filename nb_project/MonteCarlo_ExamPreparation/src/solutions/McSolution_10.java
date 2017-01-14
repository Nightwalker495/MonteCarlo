package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_10 implements IMcSolution {

    private static final double VOLUME = 60.0; //9 * 2 * 2 * (10 / 6);
    
    private double integralRes_;
    
    public McSolution_10() {
        integralRes_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformContinuousRNG rndX = new UniformContinuousRNG(3.0, 5.0);
        UniformContinuousRNG rndY = new UniformContinuousRNG(2.0, 4.0);
        UniformContinuousRNG rndZ = new UniformContinuousRNG(1.0, 10.0);
        UniformContinuousRNG rndFuncVal = new UniformContinuousRNG(1.0 / 20.0,
                10.0 / 6.0);
        
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            if (rndFuncVal.sample() <= evalFunc(rndX.sample(), rndY.sample(),
                    rndZ.sample())) {
                count++;
            }
        }
        
        integralRes_ = ((double) count / (double) replsNum) * VOLUME;
    }

    @Override
    public String getReport() {
        return String.format("Integral result = %.10f%n", integralRes_);
    }
    
    private static double evalFunc(double x, double y, double z) {
        return x / (y * z);
    }
}
