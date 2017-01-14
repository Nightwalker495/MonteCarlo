package solutions;

import OSPRNG.UniformDiscreteRNG;
import montecarlo.IMcSolution;

public class McSolution_03 implements IMcSolution {

    private double meetingProb_;
    
    public McSolution_03() {
        meetingProb_ = 0.0;
    }
    
    @Override
    public void run(int replsNum) {
        UniformDiscreteRNG firstRnd = new UniformDiscreteRNG(0, 7200);
        UniformDiscreteRNG secondRnd = new UniformDiscreteRNG(0, 7200);
        
        int count = 0;
        
        for (int i = 0; i < replsNum; i++) {
            int firstTime = firstRnd.sample();
            int secondTime = secondRnd.sample();
            
            if (Math.abs(firstTime - secondTime) <= 1800) {
                count++;
            }
        }
        
        meetingProb_ = (double) count / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("Probability of meeting = %.10f%n", meetingProb_);
    }
    
}
