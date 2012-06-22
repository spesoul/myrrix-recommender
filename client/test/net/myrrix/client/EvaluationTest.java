/*
 * Copyright Myrrix Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.myrrix.client;

import java.io.File;

import com.google.common.base.Preconditions;
import com.google.common.io.PatternFilenameFilter;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.myrrix.common.MyrrixTest;

public final class EvaluationTest extends MyrrixTest {

  private static final Logger log = LoggerFactory.getLogger(EvaluationTest.class);

  @Test
  public void testEval() throws Exception {
    File originalDataFileDir = new File("testdata/grouplens1M");
    File[] originalDataFiles = originalDataFileDir.listFiles(new PatternFilenameFilter(".+\\.csv(\\.(zip|gz))?"));
    Preconditions.checkState(originalDataFiles.length == 1);
    Evaluator evaluator = new Evaluator(originalDataFiles[0], getTestTempDir(), 0.9, 0.1);
    IRStatistics stats = evaluator.evaluate();
    log.info(stats.toString());
    assertTrue(stats.getPrecision() > 0.11);
  }

}
