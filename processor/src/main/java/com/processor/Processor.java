package com.processor;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.inject.Singleton;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CJJ on 2017/3/15.
 */
@AutoService(javax.annotation.processing.Processor.class)
public class Processor extends AbstractProcessor {

    public static final boolean DEBUG = true;
    private static final String MODULE_PACKAGE = "com.jiqu.domain.entity";
    private Filer mFiler;
    //in this situation,includes String type
    private static final String[] BASE_TYPES = {"int", "java.lang.String", "long", "char", "short", "byte", "boolean", "double", "float"};
    private static final ClassName LIST = ClassName.get("java.util", "List");
    private static final ClassName ARRAYLIST = ClassName.get("java.util", "ArrayList");
    private Elements elementUtils;
    private Set<String> mapperClass = new HashSet<>();
    private static boolean created = false;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Mapper.class);
        if (!created)
            parseElements(elementsAnnotatedWith);
        return created = true;
    }

    private void parseElements(Set<? extends Element> annotationElements) {
        Iterator<? extends Element> iterator = annotationElements.iterator();
        while (iterator.hasNext()) {
            Element ele = iterator.next();
            TypeMirror typeMirror = ele.asType();
            String copyName = createName("copy");
            String sourceName = ele.getSimpleName().toString().toLowerCase();
            ParameterSpec.Builder parameter = ParameterSpec.builder(TypeName.get(typeMirror), sourceName);
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("map")
                    .returns(TypeName.get(typeMirror))
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(parameter.build());
            createMapper(ele, typeMirror, copyName, sourceName, methodBuilder);
            methodBuilder.addStatement("return " + copyName);
            MethodSpec mapMethod = methodBuilder.build();
            //if need "List<T> map(List<T> list)"
            ParameterizedTypeName listRaw = ParameterizedTypeName.get(LIST, ClassName.get(typeMirror));
            ParameterizedTypeName arrayListRaw = ParameterizedTypeName.get(ARRAYLIST, ClassName.get(typeMirror));
            MethodSpec listMapMethod = MethodSpec.methodBuilder("map")
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(listRaw, "sources")
                    .addStatement("$T copies = new $T()", listRaw, arrayListRaw)
                    .beginControlFlow("for(int i=0;i<sources.size();i++)")
                    .addStatement("$T copy = null;", TypeName.get(typeMirror))
                    .addStatement("$T source = sources.get(i)", TypeName.get(typeMirror))
                    .addStatement("copy = map(source)")
                    .addStatement("copies.add(copy)")
                    .endControlFlow()
                    .addStatement("return copies")
                    .returns(listRaw)
                    .build();

            String clazzName = ele.getSimpleName() + "Mapper";

            TypeSpec typeBuilder = TypeSpec.classBuilder(clazzName)
                    .addMethod(mapMethod)
                    .addMethod(listMapMethod)
                    .addAnnotation(Singleton.class)
                    .addModifiers(Modifier.PUBLIC)
                    .build();
            String packageName = getPackageName(ele);
            JavaFile javaFile = JavaFile.builder(packageName, typeBuilder).build();
            System.out.println("==========Adding====" + (packageName + "." + clazzName));
            mapperClass.add(packageName + "." + clazzName);
            try {
                javaFile.writeTo(mFiler);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        createMapperModule();
    }

    private void createMapperModule() {
        System.out.println("=================CreatingMapperModule==========================");
        Iterator<String> iterator = mapperClass.iterator();
        List<MethodSpec> methodSpecs = new ArrayList<>();
        while (iterator.hasNext()) {
            String mapperClassName = iterator.next();
            System.out.println("=====================================ModuleElements===========" + mapperClassName);
            ClassName className = ClassName.bestGuess(mapperClassName);
            int beginIndex = mapperClassName.lastIndexOf(".") > 0 ? mapperClassName.lastIndexOf(".") + 1 : 0;
            String suffix = mapperClassName.substring(beginIndex);
            MethodSpec methodSpec = MethodSpec.methodBuilder("provide" + suffix)
                    .addAnnotation(Provides.class)
                    .addAnnotation(Singleton.class)
                    .returns(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("return new $T()", className).build();
            methodSpecs.add(methodSpec);
        }

        TypeSpec.Builder typeSpec = TypeSpec.classBuilder("MapperModule").addModifiers(Modifier.PUBLIC);
        for (int i = 0; i < methodSpecs.size(); i++) {
            typeSpec.addMethod(methodSpecs.get(i));
        }
        TypeSpec spec = typeSpec.addAnnotation(Module.class)
                .build();
        JavaFile javaFile = JavaFile.builder(MODULE_PACKAGE, spec).build();
        try {

            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getPackageName(Element ele) {
        if (ele instanceof TypeElement) {
            PackageElement packageElement = elementUtils.getPackageOf(ele);
            if (!packageElement.isUnnamed()) {
                String pkgName = packageElement.getQualifiedName().toString();
                System.out.println(pkgName);
                return pkgName;
            }
        }

        return null;
    }

    private void createMapper(Element ele, TypeMirror typeMirror, String copyName, String sourceName, MethodSpec.Builder methodBuilder) {
        methodBuilder.addStatement("$T " + copyName + " = new $T()", TypeName.get(typeMirror), TypeName.get(typeMirror));
        List<? extends Element> enclosedElements = ele.getEnclosedElements();
        ExecutableElement getter = null;
        ExecutableElement setter;
        List<? extends ExecutableElement> methods = findMethods(enclosedElements, true);
        for (ExecutableElement me :
                methods) {
            if (me.getReturnType().getKind() != TypeKind.VOID) {
                //getter now
                getter = me;
            } else {
                setter = me;
                //method-concat procedure
                methodBuilder.addStatement(copyName + "." + setter.getSimpleName() + "(" + sourceName + "." + getter.getSimpleName() + "())");
            }
        }
        List<? extends ExecutableElement> noPrimitiveMethods = findNoPrimitiveMethods(enclosedElements);
        List<? extends ExecutableElement> collectionMethods = findCollectionMethods(enclosedElements);
        nestCreate(copyName, sourceName, methodBuilder, noPrimitiveMethods);
        nestListCreate(copyName, sourceName, methodBuilder, collectionMethods);
    }

    private void nestListCreate(String c, String s, MethodSpec.Builder methodBuilder, List<? extends ExecutableElement> collectionMethods) {
        ExecutableElement setter;
        ExecutableElement getter = null;
        for (ExecutableElement me :
                collectionMethods) {
            String copyName = createName("copy");
            String copySourceName = createName("source");
            TypeMirror returnType = me.getReturnType();

            if (returnType.getKind() == TypeKind.VOID) {
                assert getter != null;
                TypeMirror getterReturnType = getter.getReturnType();
                setter = me;
                List<? extends TypeMirror> typeArguments = ((DeclaredType) getterReturnType).getTypeArguments();
                if (typeArguments.size() == 1) {
                    TypeMirror type = typeArguments.get(0);
                    TypeName rawType = ClassName.get(type);
                    ParameterizedTypeName listRaw = ParameterizedTypeName.get(LIST, rawType);
                    ParameterizedTypeName arrayListRaw = ParameterizedTypeName.get(ARRAYLIST, rawType);
                    String sourceListName = createName("sourceList");
                    String copyListName = createName("copyList");
                    String intIndex = createName("index");
                    methodBuilder.addStatement("$T " + sourceListName + " =" + s + "." + getter.getSimpleName() + "()", listRaw)
                            .addStatement("$T " + copyListName + " = new $T()", listRaw, arrayListRaw)
                            .beginControlFlow("for(int " + intIndex + " = 0;" + intIndex + "< " + sourceListName + ".size();" + intIndex + "++)")
                            .addStatement("$T " + copySourceName + " = " + sourceListName + ".get(" + intIndex + ")", rawType);
                    createMapper(((DeclaredType) type).asElement(), type, copyName, copySourceName, methodBuilder);
                    methodBuilder.addStatement(copyListName + ".add(" + copyName + ")");
                    methodBuilder.endControlFlow();
                    methodBuilder.addStatement(c + "." + setter.getSimpleName() + "(" + copyListName + ")");
                } else {
                    throw new IllegalStateException("nested type parameter is not supported,like this:List<List<T>>,only 'List<T>' pattern is ok");
                }

            } else {
                getter = me;
            }
        }
    }

    private void nestCreate(String source, String s, MethodSpec.Builder methodBuilder, List<? extends ExecutableElement> methodElements) {

        ExecutableElement getter = null;
        ExecutableElement setter;
        TypeMirror typeMirror = null;

        for (ExecutableElement me :
                methodElements) {
            if (me.getReturnType().getKind() == TypeKind.VOID) {//setter
                setter = me;
                String copyName = createName("copy");
                String copySourceName = createName("source");
                TypeName typeName = TypeName.get(typeMirror);
                methodBuilder.addStatement("$T " + copyName + " = new $T()", typeName, typeName);
                methodBuilder.addStatement("$T " + copySourceName + " = " + s + "." + getter.getSimpleName() + "()", typeName);
                assert typeMirror != null;
                List<? extends ExecutableElement> methods = findMethods((((DeclaredType) typeMirror).asElement()).getEnclosedElements(), true);
                glue(methodBuilder, copyName, copySourceName, methods);
                nestCreate(copyName, s, methodBuilder, findNoPrimitiveMethods((((DeclaredType) typeMirror).asElement()).getEnclosedElements()));
                methodBuilder.addStatement(source + "." + setter.getSimpleName() + "(" + copyName + ")");
            } else {//getter
                typeMirror = me.getReturnType();
                getter = me;
            }
        }
    }

    private void glue(MethodSpec.Builder methodBuilder, String copy, String source, List<? extends ExecutableElement> methods) {
        ExecutableElement getter = null;
        ExecutableElement setter;
        for (ExecutableElement me :
                methods) {
            if (me.getReturnType().getKind() == TypeKind.VOID) {
                setter = me;
                methodBuilder.addStatement(copy + "." + setter.getSimpleName() + "(" + source + "." + getter.getSimpleName() + "())");
            } else {
                getter = me;
            }
        }
    }

    private String createName(String suffix) {
        long l = System.nanoTime() % 10000;
        return suffix + ("_" + l);
    }

    /**
     * find all no-primitive methods that parameters are not all primitive type or return
     * type is not primitive
     *
     * @param enclosedElements ..
     * @return ..
     */
    private List<? extends ExecutableElement> findNoPrimitiveMethods(List<? extends Element> enclosedElements) {
        List<ExecutableElement> executableElements = new ArrayList<>();
        for (Element ele :
                enclosedElements) {

            if (ele.getKind() == ElementKind.METHOD && ele.getKind() != ElementKind.CONSTRUCTOR) {
                ExecutableElement executableElement = (ExecutableElement) ele;
                if (!isPrimitiveMethod(executableElement) && !isCollectionMethod(executableElement)) {
                    executableElements.add(executableElement);
                }

            }
        }
        return executableElements;
    }

    /**
     * @param enclosedElements ..
     * @return ..
     */
    private List<? extends ExecutableElement> findCollectionMethods(List<? extends Element> enclosedElements) {
        List<ExecutableElement> executableElements = new ArrayList<>();
        for (Element ele :
                enclosedElements) {

            if (ele.getKind() == ElementKind.METHOD && ele.getKind() != ElementKind.CONSTRUCTOR) {
                ExecutableElement executableElement = (ExecutableElement) ele;
                if (isCollectionMethod(executableElement)) {
                    executableElements.add(executableElement);
                }

            }
        }
        return executableElements;
    }

    /**
     * @param enclosedElements ..
     * @param filterPrimitive  find all the method that parameter is primitive if set true,otherwise
     *                         find all methods
     * @return ...
     */
    private List<? extends ExecutableElement> findMethods(List<? extends Element> enclosedElements, boolean filterPrimitive) {
        List<ExecutableElement> executableElements = new ArrayList<>();
        for (Element ele :
                enclosedElements) {
            if (ele.getKind() == ElementKind.METHOD && ele.getKind() != ElementKind.CONSTRUCTOR) {
                if (filterPrimitive) {
                    if (isPrimitiveMethod((ExecutableElement) ele))
                        executableElements.add((ExecutableElement) ele);
                } else executableElements.add((ExecutableElement) ele);
            }
        }
        return executableElements;
    }


    /**
     * @param element ...
     * @return ..
     */
    private boolean isPrimitiveMethod(ExecutableElement element) {
        TypeMirror typeMirror = element.getReturnType();
        TypeKind kind = typeMirror.getKind();
        if (kind.isPrimitive())
            return true;
        else if (kind == TypeKind.DECLARED) {
            return typeMirror.toString().equals("java.lang.String");
        } else {
            return isPrimitiveParameters(element.getParameters());
        }

    }

    private boolean isCollectionMethod(ExecutableElement element) {
        TypeMirror returnType = element.getReturnType();
        if (returnType.getKind() != TypeKind.VOID && returnType.toString().startsWith("java.util.List"))
            return true;
        else if (returnType.getKind() == TypeKind.VOID) {
            List<? extends VariableElement> parameters = element.getParameters();
            if (parameters.size() == 1) {
                VariableElement typeParameterElement = parameters.get(0);
                TypeMirror typeMirror = typeParameterElement.asType();
                if (typeMirror.toString().startsWith("java.util.List")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * check if the method's parameter contains no-primitive type,
     * if so this method is not  primitive field's getter or setter
     *
     * @param parameters ..
     * @return ..
     */
    private boolean isPrimitiveParameters(List<? extends VariableElement> parameters) {
        for (VariableElement parameterEle :
                parameters) {
            if (!isPrimitiveType(parameterEle.asType())) return false;
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(Mapper.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private boolean isPrimitiveType(TypeMirror typeMirror) {
        for (String BASE_TYPE : BASE_TYPES) {
            if (BASE_TYPE.equals(typeMirror.toString()))
                return true;
        }
        return false;
    }
}
