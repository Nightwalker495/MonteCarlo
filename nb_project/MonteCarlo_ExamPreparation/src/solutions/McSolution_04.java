package solutions;

import OSPRNG.UniformDiscreteRNG;
import montecarlo.IMcSolution;

public class McSolution_04 implements IMcSolution {

    private double collisionProb_;
    
    public McSolution_04() {
        collisionProb_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformDiscreteRNG firstRnd = new UniformDiscreteRNG(0, 101);
        UniformDiscreteRNG secondRnd = new UniformDiscreteRNG(0, 101);
        
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            int firstTime = firstRnd.sample();
            int secondTime = secondRnd.sample();
            
            if (Math.abs(firstTime - secondTime) <= 25) {
                count++;
            }
        }
        
        collisionProb_ = (double) count / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("Probability of collision = %.10f%n",
                collisionProb_);
    }
    
}
