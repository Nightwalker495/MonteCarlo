package solutions;

import OSPRNG.ExponentialRNG;
import montecarlo.IMcSolution;

public class McSolution_14 implements IMcSolution {

    private static final double MEAN_TIME = 200.0;

    private double atLeast300HoursProb_;
    private double noLongerThanMeanProb_;
    private double maxWarrantyHours_;

    public McSolution_14() {
        atLeast300HoursProb_ = noLongerThanMeanProb_ = maxWarrantyHours_ = 0.0;
    }

    @Override
    public void run(int replsNum) {
        ExponentialRNG rnd = new ExponentialRNG(MEAN_TIME);
        int atLeast300Num = 0;
        int noLongerThanMeanNum = 0;

        for (int i = 0; i < replsNum; i++) {
            double time = rnd.sample();
            
            if (time >= 300.0) {
                atLeast300Num++;
            }
            else if (time <= MEAN_TIME) {
                noLongerThanMeanNum++;
            }
        }

        atLeast300HoursProb_ = (double) atLeast300Num / (double) replsNum;
        noLongerThanMeanProb_
                = (double) noLongerThanMeanNum / (double) replsNum;

        rnd = new ExponentialRNG(MEAN_TIME);
        for (int hours = 1;; hours++) {
            int brokenNum = 0;
            
            for (int i = 0; i < replsNum; i++) {
                if (rnd.sample() <= hours) {
                    brokenNum++;
                }
            }
            
            if (((double) brokenNum / (double) replsNum) > 0.05) {
                maxWarrantyHours_ = hours - 1;
                break;
            }
        }
    }

    @Override
    public String getReport() {
        StringBuilder str = new StringBuilder(String.format("Results%n"));

        str.append(String.format("\tP(at least 300h) = %.8f%%%n",
                atLeast300HoursProb_ * 100.0));
        str.append(String.format("\tP(working no more than mean) = %.8f%%%n",
                noLongerThanMeanProb_ * 100.0));
        str.append(String.format("\tMax. hours num (5%% returns) = %.0f%n",
                maxWarrantyHours_));

        return str.toString();
    }

}
