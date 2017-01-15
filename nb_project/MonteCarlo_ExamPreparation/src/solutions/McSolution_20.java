package solutions;

import OSPRNG.UniformContinuousRNG;
import montecarlo.IMcSolution;

public class McSolution_20 implements IMcSolution {

    private int numsCount_;

    public McSolution_20() {
        numsCount_ = 0;
    }

    @Override
    public void run(int replsNum) {
        for (int numsCount = 49600;; numsCount++) {
            UniformContinuousRNG rnd = new UniformContinuousRNG(0.0, 6.0);
            int count = 0;
            
            for (int i = 0; i < replsNum; i++) {
                double sum = 0.0;
                
                for (int j = 0; j < numsCount; j++) {
                    sum += rnd.sample();
                }
                
                double average = sum / (double) numsCount;
                if ((average >= 2.98) && (average <= 3.02)) {
                    count++;
                }
            }
            
            if (((double) count / (double) replsNum) >= 0.99) {
                numsCount_ = numsCount;
                break;
            }
        }
    }

    @Override
    public String getReport() {
        return String.format("Results are too deviated from the theoretical "
                + "values!%nNumbers count = %d.%n", numsCount_);
    }

}
