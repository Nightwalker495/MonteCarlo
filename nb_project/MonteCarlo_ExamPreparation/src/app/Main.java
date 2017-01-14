package app;

import java.util.HashMap;
import java.util.Scanner;
import montecarlo.IMcSolution;
import montecarlo.McTimedSolutionDecorator;
import solutions.McSolution_01;
import solutions.McSolution_02;
import solutions.McSolution_03;

public class Main {

    private HashMap<Integer, IMcSolution> solvers_;

    public static void main(String[] args) {
        (new Main()).run();
    }

    public Main() {
        solvers_ = new HashMap<>();

        solvers_.put(1, new McTimedSolutionDecorator(new McSolution_01()));
        solvers_.put(2, new McTimedSolutionDecorator(new McSolution_02()));
        solvers_.put(3, new McTimedSolutionDecorator(new McSolution_03()));
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        boolean done = false;

        System.out.println("Number of available problems = " + solvers_.size());
        do {
            System.out.print("Enter problem number (negative for exit) >\t");
            int solNum = scan.nextInt();
            if (solNum < 0) {
                done = true;
            } else if ((solNum < 1) || (solNum > solvers_.size())) {
                System.err.println("Error: problem number out of range");
            } else {
                System.out.print("Enter number of replications >\t");
                int replsNum = scan.nextInt();
                System.out.println("Executing solution for problem #" + solNum);
                IMcSolution solver = solvers_.get(solNum);
                solver.run(replsNum);
                System.out.println(solver.getReport());
            }
        } while (!done);
    }
}
