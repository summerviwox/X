package com.summer.javalib.annotation;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Completion;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * tangjie 2023/3/10 9:48
 **/
@AutoService(Processor.class)
public class TestAnnotationProcessor extends AbstractProcessor {

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }



    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(MyTestAnnotation.class.getCanonicalName());
        System.out.println("getSupportedAnnotationTypes");
        return set;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (TypeElement typeElement:set){
            for(Element element:roundEnvironment.getElementsAnnotatedWith(typeElement)){
                if(element.getKind()== ElementKind.CLASS){
                    MyTestAnnotation myTestAnnotation = element.getAnnotation(MyTestAnnotation.class);
                    String className = element.getSimpleName().toString();
                    String packageName = processingEnv.getElementUtils().getPackageOf(element).toString();
                    generateCode(packageName, className, myTestAnnotation.value());
                }
            }

        }
        return false;
    }

    private void generateCode(String packageName, String className, String value) {
        System.out.println("generateCode"+packageName+""+className+""+value);
        try {
            JavaFileObject fileObject = filer.createSourceFile(packageName + "." + className + "Helper");
            //JavaFileObject fileObject = filer.createSourceFile("com.summer.testHelper");
            Writer writer = fileObject.openWriter();
            writer.write("package " + packageName + ";\n\n");
            writer.write("public class " + className + "Helper {\n");
            writer.write("    public static String getValue() {\n");
            writer.write("        return \"" + value + "\";\n");
            writer.write("    }\n");
            writer.write("}\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }



    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
        return super.getCompletions(element, annotation, member, userText);
    }
}
