package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_08 implements IMcSolution {

    private static final double ZERO_POINT = Math.PI / 2.0;
    
    private double integralRes_;
    
    public McSolution_08() {
        integralRes_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformContinuousRNG rndX = new UniformContinuousRNG(1.0, 2.0);
        UniformContinuousRNG rndY = new UniformContinuousRNG();
        
        int countAboveZeroValid = 0;
        int countAboveZeroTotal = 0;
        int countBelowZeroValid = 0;
        int countBelowZeroTotal = 0;
        
        for (int i = 0; i < replsNum; i++) {
            double val = evalFunc(rndX.sample());
            double y = rndY.sample();
            
            if (val >= 0.0) {
                countAboveZeroTotal++;
                if (isUnderCurve(y, val)) {
                    countAboveZeroValid++;
                }
            } else {
                countBelowZeroTotal++;
                if (isUnderCurve(y, -val)) {
                    countBelowZeroValid++;
                }
            }
        }
        
        double aboveArea = calcArea(countAboveZeroValid, countAboveZeroTotal,
                1.0, ZERO_POINT);
        double belowArea = calcArea(countBelowZeroValid, countBelowZeroTotal,
                ZERO_POINT, 2.0);
        
        integralRes_ = aboveArea - belowArea;
    }

    @Override
    public String getReport() {
        return String.format("Integral result = %.10f%n", integralRes_);
    }
    
    private static double evalFunc(double x) {
        return Math.sin(x) * Math.cos(x);
    }
    
    private static boolean isUnderCurve(double y, double funcY) {
        return y <= funcY;
    }
    
    private static double calcArea(double valid, double total,
            double xFrom, double xTo) {
        return (valid / total) * (xTo - xFrom);
    }
}
