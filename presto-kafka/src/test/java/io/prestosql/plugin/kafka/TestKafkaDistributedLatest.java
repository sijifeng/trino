/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prestosql.plugin.kafka;

import io.prestosql.testing.AbstractTestQueries;
import io.prestosql.testing.QueryRunner;
import io.prestosql.testing.kafka.BasicTestingKafka;
import io.prestosql.tpch.TpchTable;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@Test
public class TestKafkaDistributedLatest
        extends AbstractTestQueries
{
    private BasicTestingKafka testingKafka;

    @Override
    protected QueryRunner createQueryRunner()
            throws Exception
    {
        testingKafka = new BasicTestingKafka("5.5.2");
        return KafkaQueryRunner.builder(testingKafka)
                .setTables(TpchTable.getTables())
                .build();
    }

    @AfterClass(alwaysRun = true)
    public void destroy()
    {
        if (testingKafka != null) {
            testingKafka.close();
            testingKafka = null;
        }
    }
}