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

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 5/9/16
 */
public class MarkerClassFileTransformer implements ClassFileTransformer, Opcodes {

    // Constants -------------------------------------------------------------------------------------------------------

    // Static ----------------------------------------------------------------------------------------------------------

    // Attributes ------------------------------------------------------------------------------------------------------

    // Constructors ----------------------------------------------------------------------------------------------------

    // ClassFileTransformer implementation -----------------------------------------------------------------------------

    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className.equals("io/novaordis/example/Example")) {

            ClassWriter cw = new ClassWriter(0);

            cw.visit(
                    V1_8,
                    ACC_PUBLIC + ACC_SUPER,
                    "io/novaordis/example/Example",
                    null,
                    "java/lang/Object",
                    null);

            cw.visitSource(null, null);

            MethodVisitor mv;

                              // access,    name, desc, signature, exceptions
            mv = cw.visitMethod(ACC_PUBLIC, "", "()V", null, null);
            mv.visitCode();

            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(3, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "", "()V");
            mv.visitInsn(RETURN);

            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lio/novaordis/example/Example;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();

            mv = cw.visitMethod(ACC_PUBLIC, "getName", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(6, l0);
            mv.visitLdcInsn("bar");
            mv.visitInsn(ARETURN);
            l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lio/novaordis/example/Example;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
            cw.visitEnd();

            return cw.toByteArray();
        }

        return classfileBuffer;
    }

    // Public ----------------------------------------------------------------------------------------------------------

    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

}
