/*
 * Copyright (c) 2016 Nova Ordis LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.novaordis.eventagent;

import java.lang.instrument.Instrumentation;

/**
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 5/9/16
 */
public class EventAgent {

    // Constants -------------------------------------------------------------------------------------------------------

    // Static ----------------------------------------------------------------------------------------------------------

    private static Instrumentation instrumentation;

    // Attributes ------------------------------------------------------------------------------------------------------

    // Constructors ----------------------------------------------------------------------------------------------------

    // Public ----------------------------------------------------------------------------------------------------------

    /**
     * JVM hook to statically load the javaagent at startup.
     *
     * After the Java Virtual Machine (JVM) has initialized, the premain method will be called *before* the
     * application's main() method.
     */
    public static void premain(String args, Instrumentation inst) throws Exception {

        System.out.println("event-agent premain method invoked with args: " + args + " and inst: " + inst);
        instrumentation = inst;
        instrumentation.addTransformer(new MarkerClassFileTransformer());
    }

    /**
     * JVM hook to statically load the javaagent at runtime.
     */
    public static void agentmain(String args, Instrumentation inst) throws Exception {

        instrumentation = inst;
        //instrumentation.addTransformer(new MyClassFileTransformer());
    }

    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

}
