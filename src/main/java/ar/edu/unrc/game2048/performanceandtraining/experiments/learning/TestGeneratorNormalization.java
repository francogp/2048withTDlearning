/*
 * Copyright (C) 2016  Lucia Bressan <lucyluz333@gmial.com>,
 *                     Franco Pellegrini <francogpellegrini@gmail.com>,
 *                     Renzo Bianchini <renzobianchini85@gmail.com
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ar.edu.unrc.game2048.performanceandtraining.experiments.learning;

import ar.edu.unrc.game2048.performanceandtraining.configurations.ntuples.ConfigNTupleBasicTanHSimplified_512;
import ar.edu.unrc.game2048.performanceandtraining.experiments.learning.ntuple.NTupleBasicTanHSimplified_512;

import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.unrc.game2048.performanceandtraining.experiments.TestGenerator.NO_ANNEALING;
import static ar.edu.unrc.game2048.performanceandtraining.experiments.TestGenerator.runAllConfigs;

/**
 * Test específicamente diseñado para comparar eficiencia de funciones de activación.
 *
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class TestGeneratorNormalization {

    /**
     * @param ex error a tratar.
     *
     * @return traducción de la traza de errores a texto.
     */
    public static
    String getMsj(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter  pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * @param args
     *
     * @throws Exception
     */
    public static
    void main(String[] args)
            throws Exception {
        String        filePath                                       = ".." + File.separator + "Perceptrones ENTRENADOS" + File.separator;
        List<Double>  lambdaList                                     = new ArrayList<>();
        List<Double>  alphaList                                      = new ArrayList<>();
        List<Integer> annealingAlphaList                             = new ArrayList<>();
        List<Double>  gammaList                                      = new ArrayList<>();
        List<Double>  fixedExplorationRate                           = new ArrayList<>();
        List<Double>  interpolatedExplorationRateFinalValues         = new ArrayList<>();
        List<Integer> interpolatedExplorationRateFinishInterpolation = new ArrayList<>();
        List<Double>  interpolatedExplorationRateInitialValues       = new ArrayList<>();
        List<Integer> interpolatedExplorationRateStartInterpolation  = new ArrayList<>();

        //============================== configuraciones manuales ==================================
        String    experimentDirName      = "Normalization";
        int       repetitions            = 1;
        int       maxTrainingThreads     = 8;
        int       gamesToPlay            = 12_000;
        int       saveEvery              = 1_000;
        int       saveBackupEvery        = 300;
        int       tileToWinForStatistics = 512;
        boolean[] concurrentLayer        = {false, false};
        boolean   replaceTraces          = true;
        boolean   accumulatingTraces     = true;

        lambdaList.add(0.5d);
        int eligibilityTraceLengthList = -1;

        alphaList.add(0.0025d);
        annealingAlphaList.add(NO_ANNEALING); //Sin annealing
        gammaList.add(1d);
        fixedExplorationRate.add(0d);

        boolean createLogs = false;
        //============================== fin de configuraciones manuales ==================================

        boolean statisticsOnly;
        boolean runStatisticsForBackups;
        int     gamesToPlayPerThreadForStatistics;
        int     simulationsForStatistics;

        statisticsOnly = false;
        runStatisticsForBackups = false;
        gamesToPlayPerThreadForStatistics = 0;
        simulationsForStatistics = 0;

        runAll(
                experimentDirName,
                filePath, lambdaList, eligibilityTraceLengthList,
                alphaList,
                annealingAlphaList,
                gammaList,
                repetitions,
                maxTrainingThreads,
                gamesToPlay,
                saveEvery,
                saveBackupEvery, replaceTraces,
                createLogs,
                statisticsOnly,
                runStatisticsForBackups,
                gamesToPlayPerThreadForStatistics,
                tileToWinForStatistics,
                simulationsForStatistics,
                fixedExplorationRate,
                interpolatedExplorationRateInitialValues,
                interpolatedExplorationRateFinalValues,
                interpolatedExplorationRateStartInterpolation,
                interpolatedExplorationRateFinishInterpolation, accumulatingTraces,
                concurrentLayer
        );

        System.out.println("\n\n*======================= ESTADISTICAS =======================");

        statisticsOnly = true;
        runStatisticsForBackups = true;
        gamesToPlayPerThreadForStatistics = 100;
        simulationsForStatistics = 8;

        runAll(
                experimentDirName,
                filePath, lambdaList, eligibilityTraceLengthList,
                alphaList,
                annealingAlphaList,
                gammaList,
                repetitions,
                maxTrainingThreads,
                gamesToPlay,
                saveEvery,
                saveBackupEvery, replaceTraces,
                createLogs,
                statisticsOnly,
                runStatisticsForBackups,
                gamesToPlayPerThreadForStatistics,
                tileToWinForStatistics,
                simulationsForStatistics,
                fixedExplorationRate,
                interpolatedExplorationRateInitialValues,
                interpolatedExplorationRateFinalValues,
                interpolatedExplorationRateStartInterpolation,
                interpolatedExplorationRateFinishInterpolation, accumulatingTraces,
                concurrentLayer
        );

        Toolkit.getDefaultToolkit().beep();
    }

    private static
    void runAll(
            final String experimentDirName,
            final String filePath,
            final List<Double> lambdaList,
            final int eligibilityTraceLengthList,
            final List<Double> alphaList,
            final List<Integer> annealingAlphaList,
            final List<Double> gammaList,
            final int repetitions,
            final int maxTrainingThreads,
            final int gamesToPlay,
            final int saveEvery,
            final int saveBackupEvery,
            final boolean replacingTraces,
            final boolean createLogs,
            final boolean statisticsOnly,
            final boolean runStatisticsForBackups,
            final int gamesToPlayPerThreadForStatistics,
            final int tileToWinForStatistics,
            final int simulationsForStatistics,
            final List<Double> fixedExplorationRate,
            final List<Double> interpolatedExplorationRateInitialValues,
            final List<Double> interpolatedExplorationRateFinalValues,
            final List<Integer> interpolatedExplorationRateStartInterpolation,
            final List<Integer> interpolatedExplorationRateFinishInterpolation,
            final boolean accumulatingTraces,
            final boolean[] concurrentLayer
    )
            throws NoSuchMethodException {
        ConfigNTupleBasicTanHSimplified_512.maxReward = 100_000;
        ConfigNTupleBasicTanHSimplified_512.minReward = -100_000;

        runAllConfigs(
                repetitions,
                maxTrainingThreads, experimentDirName, "NBasicTanH_512_100k", NTupleBasicTanHSimplified_512.class.getConstructor(),
                null,
                alphaList,
                annealingAlphaList, lambdaList, eligibilityTraceLengthList,
                gammaList,
                statisticsOnly,
                runStatisticsForBackups,
                createLogs,
                gamesToPlay,
                saveEvery,
                saveBackupEvery,
                gamesToPlayPerThreadForStatistics,
                tileToWinForStatistics,
                simulationsForStatistics,
                fixedExplorationRate,
                interpolatedExplorationRateInitialValues,
                interpolatedExplorationRateFinalValues,
                interpolatedExplorationRateStartInterpolation,
                interpolatedExplorationRateFinishInterpolation, replacingTraces, accumulatingTraces,
                filePath,
                concurrentLayer
        );

        ConfigNTupleBasicTanHSimplified_512.maxReward = 40_000;
        ConfigNTupleBasicTanHSimplified_512.minReward = -40_000;

        runAllConfigs(
                repetitions,
                maxTrainingThreads, experimentDirName, "NBasicTanH_512_40k", NTupleBasicTanHSimplified_512.class.getConstructor(),
                null,
                alphaList,
                annealingAlphaList, lambdaList, eligibilityTraceLengthList,
                gammaList,
                statisticsOnly,
                runStatisticsForBackups,
                createLogs,
                gamesToPlay,
                saveEvery,
                saveBackupEvery,
                gamesToPlayPerThreadForStatistics,
                tileToWinForStatistics,
                simulationsForStatistics,
                fixedExplorationRate,
                interpolatedExplorationRateInitialValues,
                interpolatedExplorationRateFinalValues,
                interpolatedExplorationRateStartInterpolation,
                interpolatedExplorationRateFinishInterpolation, replacingTraces, accumulatingTraces,
                filePath,
                concurrentLayer
        );

        ConfigNTupleBasicTanHSimplified_512.maxReward = 20_000;
        ConfigNTupleBasicTanHSimplified_512.minReward = -20_000;

        runAllConfigs(
                repetitions,
                maxTrainingThreads, experimentDirName, "NBasicTanH_512_20k", NTupleBasicTanHSimplified_512.class.getConstructor(),
                null,
                alphaList,
                annealingAlphaList, lambdaList, eligibilityTraceLengthList,
                gammaList,
                statisticsOnly,
                runStatisticsForBackups,
                createLogs,
                gamesToPlay,
                saveEvery,
                saveBackupEvery,
                gamesToPlayPerThreadForStatistics,
                tileToWinForStatistics,
                simulationsForStatistics,
                fixedExplorationRate,
                interpolatedExplorationRateInitialValues,
                interpolatedExplorationRateFinalValues,
                interpolatedExplorationRateStartInterpolation,
                interpolatedExplorationRateFinishInterpolation, replacingTraces, accumulatingTraces,
                filePath,
                concurrentLayer
        );

        ConfigNTupleBasicTanHSimplified_512.maxReward = 7_000;
        ConfigNTupleBasicTanHSimplified_512.minReward = -7_000;

        runAllConfigs(
                repetitions,
                maxTrainingThreads, experimentDirName, "NBasicTanH_512_7k", NTupleBasicTanHSimplified_512.class.getConstructor(),
                null,
                alphaList,
                annealingAlphaList, lambdaList, eligibilityTraceLengthList,
                gammaList,
                statisticsOnly,
                runStatisticsForBackups,
                createLogs,
                gamesToPlay,
                saveEvery,
                saveBackupEvery,
                gamesToPlayPerThreadForStatistics,
                tileToWinForStatistics,
                simulationsForStatistics,
                fixedExplorationRate,
                interpolatedExplorationRateInitialValues,
                interpolatedExplorationRateFinalValues,
                interpolatedExplorationRateStartInterpolation,
                interpolatedExplorationRateFinishInterpolation, replacingTraces, accumulatingTraces,
                filePath,
                concurrentLayer
        );
    }

}