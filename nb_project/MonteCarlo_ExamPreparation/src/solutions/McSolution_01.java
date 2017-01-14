package solutions;

import OSPRNG.UniformDiscreteRNG;
import montecarlo.IMcSolution;

public class McSolution_01 implements IMcSolution {

    private static final int HEAD = 1;

    private double firstPlayerWin_;
    private double noWinIn8Trials_;

    public McSolution_01() {
        firstPlayerWin_ = noWinIn8Trials_ = 0.0;
    }

    @Override
    public void run(int replsNum) {
        UniformDiscreteRNG rnd = new UniformDiscreteRNG(0, 1);
        int firstPlayerWinsNum = 0;
        
        for (int i = 0; i < replsNum; i++) {
            boolean firstPlayerTurn = true;
            
            while (true) {
                if (rnd.sample() == HEAD) {
                    if (firstPlayerTurn) {
                        firstPlayerWinsNum++;
                    }
                    break;
                }
                
                firstPlayerTurn = !firstPlayerTurn;
            }
        }
        
        firstPlayerWin_ = (double) firstPlayerWinsNum / (double) replsNum;

        rnd = new UniformDiscreteRNG(0, 1);
        int noWinIn8trialsNum = 0;
        
        for (int i = 0; i < replsNum; i++) {
            boolean noWin = true;
            
            for (int j = 0; j < 8; j++) {
                if (rnd.sample() == HEAD) {
                    noWin = false;
                    break;
                }
            }
            
            if (noWin) {
                noWinIn8trialsNum++;
            }
        }
        
        noWinIn8Trials_ = (double) noWinIn8trialsNum / (double) replsNum;
    }

    @Override
    public String getReport() {
        StringBuilder str = new StringBuilder(
                "Probability that first player wins = ");
        str.append(String.format("%.8f%n", firstPlayerWin_));
        str.append("Probability that no one wins in 8 trials = ");
        str.append(String.format("%.8f%n", noWinIn8Trials_));
        
        return str.toString();
    }

}
