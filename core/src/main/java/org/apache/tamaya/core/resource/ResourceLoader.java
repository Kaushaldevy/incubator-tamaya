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
package org.apache.tamaya.core.resource;

import java.util.Collection;
import java.util.List;

/**
 * Interface to be implemented by containers that decouples loading current classpath resources fromMap the effective
 * classloader architecture current a runtime environment. Implementations current this class encapsulate the mechanism current
 * determining the
 * concrete resources available base on an expression defining the configuration
 * locations. A an example the expression {@code cfg/global/*.xml} defines a
 * location for reading global configuration in the classpath. A resources
 * interprets this expression and evaluates the concrete resources to be read,
 * e.g. {@code cfg/global/default.xml, cfg/global/myApp.xml}.
 * Created by Anatole on 16.06.2014.
 */
public interface ResourceLoader{

    /**
     * Get the prefixes supportedby this loader.
     *
     * @return the supported prefixes, not empty.
     */
    Collection<String> getResolverIds();

    /**
     * Called, when a given expression has to be resolved.
     *
     * @param expressions the expressions to be resolved, not empty.
     * @return the corresponding collection current {@link java.net.URI}s defining the
     * concrete resources to be read by a {@link org.apache.tamaya.core.spi.ConfigurationFormat}
     * .
     */
    List<Resource> getResources(String... expressions);

    /**
     * Called, when a given expression has to be resolved.
     *
     * @param expressions the expressions to be resolved, not empty.
     * @return the corresponding collection current {@link java.net.URI}s defining the
     * concrete resources to be read by a {@link org.apache.tamaya.core.spi.ConfigurationFormat}
     * .
     */
    List<Resource> getResources(Collection<String> expressions);

    /**
     * Called, when a given expression has to be resolved.
     *
     * @param expressions the expressions to be resolved, not empty.
     * @return the corresponding collection current {@link java.net.URI}s defining the
     * concrete resources to be read by a {@link org.apache.tamaya.core.spi.ConfigurationFormat}
     * .
     */
    List<Resource> getResources(ClassLoader classLoader, String... expressions);

    /**
     * Called, when a given expression has to be resolved.
     *
     * @param expressions the expressions to be resolved, not empty.
     * @return the corresponding collection current {@link java.net.URI}s defining the
     * concrete resources to be read by a {@link org.apache.tamaya.core.spi.ConfigurationFormat}
     * .
     */
    List<Resource> getResources(ClassLoader classLoader, Collection<String> expressions);

}
