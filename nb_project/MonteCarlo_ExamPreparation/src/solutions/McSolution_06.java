package solutions;

import OSPRNG.UniformDiscreteRNG;
import montecarlo.IMcSolution;

public class McSolution_06 implements IMcSolution {

    private static final int SWEETS_COUNT = 15;
    private static final int WHITE_COUNT = 9;
    
    private double whiteAndLickedProb_;
    
    public McSolution_06() {
        whiteAndLickedProb_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformDiscreteRNG rndColor = new UniformDiscreteRNG(0,
                SWEETS_COUNT);
        UniformDiscreteRNG rndWhite = new UniformDiscreteRNG(0,
                WHITE_COUNT);
        
        int lickedNum = 0;
        int whiteNum = 0;
        
        for (int i = 0; i < replsNum; i++) {
            if (rndColor.sample() < WHITE_COUNT) {
                whiteNum++;
                if (rndWhite.sample() < 1) {
                    lickedNum++;
                }
            }
        }
        
        whiteAndLickedProb_ = (double) lickedNum / (double) whiteNum;
    }

    @Override
    public String getReport() {
        return String.format("Probability of taking white and licked = %.10f%n",
                whiteAndLickedProb_);
    }
    
}
