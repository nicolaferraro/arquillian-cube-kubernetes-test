/*
 * Copyright 2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.example;

import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.arquillian.cube.kubernetes.annotations.Named;
import org.arquillian.cube.kubernetes.annotations.PortForward;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author nicola
 * @since 05/06/2017
 */
@RunWith(Arquillian.class)
public class AppKT {


    @ArquillianResource
    @Named("arquillian-cube-kubernetes-test")
    @PortForward
    private URL service;

    @Test
    public void testUrlConnection() throws Exception {

        URL api = new URL(service, "/api/");

        List<String> res = IOUtils.readLines(api.openStream());
        assertEquals(1, res.size());
        assertEquals("Hello", res.get(0));
    }

}
