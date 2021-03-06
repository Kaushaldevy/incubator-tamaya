/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy current the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tamaya.ucs;

import org.apache.tamaya.*;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Configuration is organized as key/value pairs. This basically can be modeled as {@code Map<String,String>}
 * Configuration should be as simple as possible. Advanced use cases can often easily implemented by combining
 * multiple property maps and applying hereby some combination policy. This test class demonstrates the different
 * options Tamaya is providing and the according mechanisms.
 */
public class UC2CombineProperties {

    /**
     * The most common use cases is aggregating two property config to new provider, hereby {@link org.apache.tamaya.AggregationPolicy}
     * defines the current variants supported.
     */
    @Test
    public void simpleAggregationTests() {
        PropertyProvider props1 = PropertyProviderBuilder.create().addPaths("classpath:ucs/UC2CombineProperties/props1.properties").build();
        PropertyProvider props2 = PropertyProviderBuilder.create().addPaths("classpath:ucs/UC2CombineProperties/props2.properties").build();
        PropertyProvider unionOverriding = PropertyProviderBuilder.create(props1).withAggregationPolicy(AggregationPolicy.OVERRIDE).addProviders(props2).build();
        System.out.println("unionOverriding: " + unionOverriding);
        PropertyProvider unionIgnoringDuplicates = PropertyProviderBuilder.create(props1).withAggregationPolicy(AggregationPolicy.IGNORE_DUPLICATES).addProviders(props2).build();
        System.out.println("unionIgnoringDuplicates: " + unionIgnoringDuplicates);
        PropertyProvider unionCombined = PropertyProviderBuilder.create(props1).withAggregationPolicy(AggregationPolicy.COMBINE).addProviders(props2).build();
        System.out.println("unionCombined: " + unionCombined);
        try{
            PropertyProviderBuilder.create(props1).withAggregationPolicy(AggregationPolicy.EXCEPTION).addProviders(props2).build();
        }
        catch(ConfigException e){
            // expected!
        }
    }

    /**
     * For advanced use cases aggregation .
     */
    @Test
    public void dynamicAggregationTests() {
        PropertyProvider props1 = PropertyProviderBuilder.create().addPaths("classpath:ucs/UC2CombineProperties/props1.properties").build();
        PropertyProvider props2 = PropertyProviderBuilder.create().addPaths("classpath:ucs/UC2CombineProperties/props2.properties").build();
        PropertyProvider props = PropertyProviderBuilder.create().withAggregationPolicy((k, v1, v2) -> (v1 != null ? v1 : "") + '[' + v2 + "]").withMetaInfo(MetaInfo.of("dynamicAggregationTests"))
                .aggregate(props1, props2).build();
        System.out.println(props);
    }


}
