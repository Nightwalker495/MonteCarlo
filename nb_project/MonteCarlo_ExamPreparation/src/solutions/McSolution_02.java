package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_02 implements IMcSolution {

    private static final double FAIL_PROC_PROB = 0.01;

    private double allThreeFaultProb_;

    public McSolution_02() {
        allThreeFaultProb_ = 0.0;
    }

    @Override
    public void run(int replsNum) {
        int total = 0;
        UniformContinuousRNG rnd = new UniformContinuousRNG();

        for (int i = 0; i < replsNum; i++) {
            int failedProcCount = 0;

            for (int j = 0; j < 3; j++) {
                if (rnd.sample() < FAIL_PROC_PROB) {
                    failedProcCount++;
                }
            }
            
            if (failedProcCount == 3) {
                total++;
            }
        }

        allThreeFaultProb_ = (double) total / (double) replsNum;
    }

    @Override
    public String getReport() {
        return String.format("Probability of having 3 processors faulty = "
                + "%.10f%n", allThreeFaultProb_);
    }

}
