package montecarlo;

public class McTimedSolutionDecorator implements IMcSolution {

    private IMcSolution solver_;
    private long timeElapsed_;

    public McTimedSolutionDecorator(IMcSolution solver) {
        solver_ = solver;
        timeElapsed_ = 0L;
    }
    
    @Override
    public void run(int replsNum) {
        long startTime = System.nanoTime();
        solver_.run(replsNum);
        timeElapsed_ = System.nanoTime() - startTime;
    }

    @Override
    public String getReport() {
        StringBuilder str = new StringBuilder("Monte Carlo simulation took ");
        str.append(getElapsedTimeMs()).append(" ms.");
        str.append(String.format("%n--------------------------------------%n"));
        str.append(solver_.getReport());
        
        return str.toString();
    }
    
    private String getElapsedTimeMs() {
        return String.format("%.4f", ((double) timeElapsed_) / 1_000_000.0);
    }
}
