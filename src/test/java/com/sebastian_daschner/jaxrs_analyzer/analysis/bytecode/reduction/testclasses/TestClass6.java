/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sebastian_daschner.jaxrs_analyzer.analysis.bytecode.reduction.testclasses;

import com.sebastian_daschner.jaxrs_analyzer.model.instructions.*;
import com.sebastian_daschner.jaxrs_analyzer.model.methods.MethodIdentifier;

import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

public class TestClass6 {

    public Response method(final int number) {
        synchronized (this) {
            return Response.status(3 * 2 / number).build();
        }
    }

    public static List<Instruction> instructions() {
        final List<Instruction> instructions = new LinkedList<>();

        instructions.add(new LoadInstruction(0, TestClass6.class.getCanonicalName(), "this"));
        instructions.add(new DupInstruction());
        instructions.add(new StoreInstruction(2, "java.lang.Object", "variable$2"));
        instructions.add(new SizeChangingInstruction("monitorenter", 0, 1));
        instructions.add(new LoadInstruction(2, "java.lang.Object", "variable$2"));
        instructions.add(new SizeChangingInstruction("monitorexit", 0, 1));
        instructions.add(new PushInstruction(6));
        instructions.add(new LoadInstruction(1, "int", "number"));
        instructions.add(new SizeChangingInstruction("idiv", 1, 2));
        instructions.add(new InvokeInstruction(MethodIdentifier.ofStatic("javax.ws.rs.core.Response", "status", "javax.ws.rs.core.Response$ResponseBuilder", "int")));
        instructions.add(new InvokeInstruction(MethodIdentifier.ofNonStatic("javax.ws.rs.core.Response$ResponseBuilder", "build", "javax.ws.rs.core.Response")));
        instructions.add(new LoadInstruction(2, "java.lang.Object", "variable$2"));
        instructions.add(new SizeChangingInstruction("monitorexit", 0, 1));
        instructions.add(new ReturnInstruction());

        return instructions;
    }

}
