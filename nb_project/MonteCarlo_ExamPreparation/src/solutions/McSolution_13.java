package solutions;

import OSPRNG.NormalRNG;
import montecarlo.IMcSolution;

public class McSolution_13 implements IMcSolution {

    private double finishUnderOneHourProb_;
    private double minTimeNinePercentFinish_;

    public McSolution_13() {
        finishUnderOneHourProb_ = minTimeNinePercentFinish_ = 0.0;
    }

    @Override
    public void run(int replsNum) {
        NormalRNG rnd = new NormalRNG(50.0, 10.0);
        int underOneHourNum = 0;

        for (int i = 0; i < replsNum; i++) {
            if (rnd.sample() < 60.0) {
                underOneHourNum++;
            }
        }
        
        finishUnderOneHourProb_ = (double) underOneHourNum / (double) replsNum;
        
        int startTime = (finishUnderOneHourProb_ < 0.9) ? 60 : 50;
        rnd = new NormalRNG(50.0, 10.0);
        
        for (int time = startTime;; time++) {
            int underSpecTimeNum = 0;
            
            for (int i = 0; i < replsNum; i++) {
                if (rnd.sample() < time) {
                    underSpecTimeNum++;
                }
            }
            
            double ratio = (double) underSpecTimeNum / (double) replsNum;
            if (ratio >= 0.90) {
                minTimeNinePercentFinish_ = time;
                break;
            }
        }
    }

    @Override
    public String getReport() {
        StringBuilder str = new StringBuilder("Results");
        str.append(String.format("Probability that student finished in under "
                + "60m = %.8f%%%n", finishUnderOneHourProb_ * 100.0));
        str.append(String.format("Min. time such that 90%% of students "
                + "finishes = %.0fm%n", minTimeNinePercentFinish_));

        return str.toString();
    }

}
