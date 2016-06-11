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
package ar.edu.unrc.game2048.performanceandtraining.configurations.ntuples;

import ar.edu.unrc.coeus.tdlearning.training.ntuple.SamplePointState;
import ar.edu.unrc.coeus.tdlearning.utils.FunctionUtils;
import ar.edu.unrc.game2048.GameBoard;
import ar.edu.unrc.game2048.NTupleConfiguration2048;
import ar.edu.unrc.game2048.Tile;
import java.util.ArrayList;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public class NIndividualTanH extends NTupleConfiguration2048 {

    int maxReward = 500_000;
    int minReward = -500_000;

    /**
     *
     */
    public NIndividualTanH() {
        this.activationFunction = FunctionUtils.TANH;
        this.derivatedActivationFunction = FunctionUtils.TANH_DERIVATED;
        this.concurrency = false;
        double activationFunctionMax = 1;
        double activationFunctionMin = -1;

        normOutput = new NormalizedField(NormalizationAction.Normalize,
                null, maxReward, minReward, activationFunctionMax, activationFunctionMin);

        nTuplesLenght = new int[16];
        for ( int i = 0; i < 16; i++ ) {
            nTuplesLenght[i] = 1;
        }

        int maxTile = 8;
        this.allSamplePointStates = new ArrayList<>();
        for ( int i = 0; i <= maxTile; i++ ) {
            allSamplePointStates.add(new Tile(i));
        }
    }

    /**
     *
     * @param value <p>
     * @return
     */
    @Override
    public double denormalizeValueFromPerceptronOutput(Object value) {
        return normOutput.deNormalize((double) value);
    }

    /**
     *
     * @param board
     * @param outputNeuron <p>
     * @return
     */
    @Override
    public double getBoardReward(GameBoard board, int outputNeuron) {
        return board.getPartialScore();
    }

    @Override
    public double getFinalReward(GameBoard board, int outputNeuron) {
        return board.getGame().getScore();
    }

    /**
     *
     * @param board
     * @param nTupleIndex <p>
     * @return
     */
    @Override
    public SamplePointState[] getNTuple(GameBoard board, int nTupleIndex) {
        switch ( nTupleIndex ) {
            // individuales
            case 0: {
                SamplePointState[] sample
                        = {board.tileAt(0, 0)};
                return sample;
            }
            case 1: {
                SamplePointState[] sample
                        = {board.tileAt(0, 1)};
                return sample;
            }
            case 2: {
                SamplePointState[] sample
                        = {board.tileAt(0, 2)};
                return sample;
            }
            case 3: {
                SamplePointState[] sample
                        = {board.tileAt(0, 3)};
                return sample;
            }
            case 4: {
                SamplePointState[] sample
                        = {board.tileAt(1, 0)};
                return sample;
            }
            case 5: {
                SamplePointState[] sample
                        = {board.tileAt(1, 1)};
                return sample;
            }
            case 6: {
                SamplePointState[] sample
                        = {board.tileAt(1, 2),};
                return sample;
            }
            case 7: {
                SamplePointState[] sample
                        = {board.tileAt(1, 3)};
                return sample;
            }
            case 8: {
                SamplePointState[] sample
                        = {board.tileAt(2, 0)};
                return sample;
            }
            case 9: {
                SamplePointState[] sample
                        = {board.tileAt(2, 1)};
                return sample;
            }
            case 10: {
                SamplePointState[] sample
                        = {board.tileAt(2, 2)};
                return sample;
            }
            case 11: {
                SamplePointState[] sample
                        = {board.tileAt(2, 3)};
                return sample;
            }
            case 12: {
                SamplePointState[] sample
                        = {board.tileAt(3, 0)};
                return sample;
            }
            case 13: {
                SamplePointState[] sample
                        = {board.tileAt(3, 1)};
                return sample;
            }
            case 14: {
                SamplePointState[] sample
                        = {board.tileAt(3, 2),};
                return sample;
            }
            case 15: {
                SamplePointState[] sample
                        = {board.tileAt(3, 3)};
                return sample;
            }
            default: {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
    }

    /**
     *
     * @param value <p>
     * @return
     */
    @Override
    public double normalizeValueToPerceptronOutput(Object value) {
        if ( (Double) value > maxReward ) {
            throw new IllegalArgumentException("value no puede ser mayor a maxReward=" + maxReward);
        }
        return normOutput.normalize((Double) value);
    }
}
