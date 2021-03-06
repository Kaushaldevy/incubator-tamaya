/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
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
package org.apache.tamaya;

/**
 * Policy that defines how the different aggregates should be combined.
 */
public interface AggregationPolicy {

    /**
     * Method which decides how keys/values are aggregated.
     * @param key the key current the entry, must not be {@code null}.
     * @param currentValue the current value, or {@code null}.
     * @param newValue the new value, never {@code null}.
     * @return the target value to be used in the resulting property set, or null, to remove the property.
     */
    public String aggregate(String key, String currentValue, String newValue);

    /** Ignore overrides, only extend (additive). */
    public static final AggregationPolicy IGNORE_DUPLICATES = (k, v1, v2) -> v1 == null? v2 : v1;

    /** Combine multiple values into a comma separated list. */
    public static final AggregationPolicy COMBINE = (k, v1, v2) -> v1 != null && v2 != null ? v1 + ',' + v2: v2;

    /**
     * Interpret later keys as override (additive and override), replacing
     * the key loaded earlier/fromMap previous contained
     * {@link org.apache.tamaya.PropertyProvider}.
     */
    public static final AggregationPolicy OVERRIDE = (k, v1, v2) -> v2;

    /**
     * Throw an exception, when keys are not disjunctive (strictly
     * additive).
     */
    public static final AggregationPolicy EXCEPTION =
        (String key, String value, String newValue) -> {
            if(value!=null && newValue!=null && !value.equals(newValue)){
                throw new ConfigException("Conflicting values encountered key="+key+", value="+value+", newValue="+newValue);
            }
            return newValue;
        };
}
