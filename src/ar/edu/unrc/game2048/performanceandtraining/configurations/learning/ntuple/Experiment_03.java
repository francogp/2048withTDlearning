/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.game2048.performanceandtraining.configurations.learning.ntuple;

import ar.edu.unrc.game2048.NTupleConfiguration2048;
import ar.edu.unrc.game2048.performanceandtraining.configurations.LearningExperiment;
import ar.edu.unrc.game2048.performanceandtraining.configurations.libraries.NTupleExperimentInterface;
import ar.edu.unrc.game2048.performanceandtraining.configurations.ntuples.BasicMaxTileLineal;
import ar.edu.unrc.tdlearning.perceptron.interfaces.IPerceptronInterface;
import ar.edu.unrc.tdlearning.perceptron.learning.TDLambdaLearning;
import ar.edu.unrc.tdlearning.perceptron.learning.TDLambdaLearningAfterstate;
import ar.edu.unrc.tdlearning.perceptron.ntuple.NTupleSystem;
import java.io.File;
import org.encog.neural.networks.BasicNetwork;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public class Experiment_03 extends LearningExperiment<BasicNetwork> {

    /**
     *
     * @param args <p>
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String filePath;
        if ( args.length == 0 ) {
            filePath
                    = ".." + File.separator
                    + "Perceptrones ENTRENADOS" + File.separator;
        } else {
            filePath = args[0];
        }
        LearningExperiment experiment = new Experiment_03();

//        boolean statistics = true;
        boolean statistics = false;
        //  double[] alphas = {0.01};
        //    experiment.setAlpha(alphas);
        experiment.setLearningRateAdaptationToAnnealing(400_000);
        experiment.setLambda(0);
        experiment.setGamma(1);
        experiment.setMomentum(0);
        experiment.setExplorationRateToFixed(0.1);
        experiment.setReplaceEligibilitiTraces(false);
        experiment.setResetEligibilitiTraces(false);
        experiment.setGamesToPlay(1_000_000);
        experiment.setLastGamePlayedNumber(0); //recordar AJUSTAR ESTE VALOR
        experiment.setSaveEvery(10_000);
        experiment.setInitializePerceptronRandomized(true);

        experiment.createLogs(false);
        //para calcualar estadisticas
        if ( statistics ) {
            experiment.setStatisticsOnly(true);
            experiment.setRunStatisticForRandom(true);
            experiment.setRunStatisticsForBackups(true);
            experiment.setGamesToPlayPerThreadForStatistics(10_000);
            experiment.setSimulationsForStatistics(8);
        } else {
            experiment.setStatisticsOnly(false);
            experiment.setRunStatisticForRandom(false);
            experiment.setRunStatisticsForBackups(false);
            experiment.setGamesToPlayPerThreadForStatistics(0);
            experiment.setSimulationsForStatistics(0);
        }

        experiment.start(filePath, 0);
    }

    @Override
    public void initialize() throws Exception {
        this.setTileToWin(2_048);
        if ( this.getExperimentName() == null ) {
            this.setExperimentName("Experiment_03");
        }
        this.setPerceptronName(this.getExperimentName());
        NTupleConfiguration2048 config = new BasicMaxTileLineal();
        this.setNeuralNetworkInterfaceFor2048(new NTupleExperimentInterface(config));
    }

    @Override
    public TDLambdaLearning instanceOfTdLearninrgImplementation(IPerceptronInterface perceptronInterface) {
        return null;
    }

    @Override
    public TDLambdaLearning instanceOfTdLearninrgImplementation(NTupleSystem nTupleSystem) {
        return new TDLambdaLearningAfterstate(nTupleSystem, (getAlpha() != null) ? getAlpha()[0] : null, getLambda(), false, getGamma(), getMomentum(), isResetEligibilitiTraces(), isReplaceEligibilitiTraces());
    }

}
