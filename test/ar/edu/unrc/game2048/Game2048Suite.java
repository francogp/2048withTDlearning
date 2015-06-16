/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.game2048;

import ar.edu.unrc.game2048.performanceandtraining.PerformanceandtrainingSuite;
import ar.edu.unrc.game2048.resources.ResourcesSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author franco
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( {PerformanceandtrainingSuite.class, ResourcesSuite.class, IConfiguration2048Test.class, GameBoardTest.class, ActionTest.class, IGameTest.class, NTupleConfiguration2048Test.class, Game2048Test.class, TileContainerTest.class, TileTest.class, PerceptronConfiguration2048Test.class})
public class Game2048Suite {

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

}
