package solutions;

import OSPRNG.UniformDiscreteRNG;
import montecarlo.IMcSolution;

public class McSolution_12 implements IMcSolution {

    private static final int ROCK_POS = 0;
    private static final int PAPER_POS = 1;
    private static final int SCISSORS_POS = 2;

    private double[] results_;

    public McSolution_12() {
        results_ = new double[3];
    }

    @Override
    public void run(int replsNum) {
        UniformDiscreteRNG playerOneRnd = new UniformDiscreteRNG(0, 2);
        UniformDiscreteRNG playerTwoRnd = new UniformDiscreteRNG(0, 2);

        for (int i = 0; i < replsNum; i++) {
            results_[gameRound(playerOneRnd, playerTwoRnd)]++;
        }

        for (int i = 0; i < results_.length; i++) {
            results_[i] /= (double) replsNum;
        }
    }

    @Override
    public String getReport() {
        StringBuilder str = new StringBuilder(
                String.format("Statistics of wins%n"));

        str.append(String.format("\tRock = %.6f%%%n",
                results_[ROCK_POS] * 100.0));
        str.append(String.format("\tPaper = %.6f%%%n",
                results_[PAPER_POS] * 100.0));
        str.append(String.format("\tScissors = %.6f%%%n",
                results_[SCISSORS_POS] * 100.0));

        return str.toString();
    }

    private int gameRound(UniformDiscreteRNG playerOneRnd,
            UniformDiscreteRNG playerTwoRnd) {
        int playerOneChoice = playerOneRnd.sample();
        int playerTwoChoice = playerTwoRnd.sample();

        switch (playerOneChoice) {
            case ROCK_POS:
                switch (playerTwoChoice) {
                    case SCISSORS_POS:
                        return ROCK_POS;
                    case PAPER_POS:
                        return PAPER_POS;
                    default:
                        return gameRound(playerOneRnd, playerTwoRnd);
                }
            case PAPER_POS:
                switch (playerTwoChoice) {
                    case ROCK_POS:
                        return PAPER_POS;
                    case SCISSORS_POS:
                        return SCISSORS_POS;
                    default:
                        return gameRound(playerOneRnd, playerTwoRnd);
                }
            default:
                switch (playerTwoChoice) {
                    case ROCK_POS:
                        return ROCK_POS;
                    case PAPER_POS:
                        return SCISSORS_POS;
                    default:
                        return gameRound(playerOneRnd, playerTwoRnd);
                }
        }
    }

}
