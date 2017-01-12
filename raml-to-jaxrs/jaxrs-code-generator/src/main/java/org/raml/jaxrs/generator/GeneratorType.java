package org.raml.jaxrs.generator;

import org.raml.jaxrs.generator.ramltypes.GType;

import static org.raml.jaxrs.generator.GObjectType.ENUMERATION_TYPE;
import static org.raml.jaxrs.generator.GObjectType.JSON_OBJECT_TYPE;
import static org.raml.jaxrs.generator.GObjectType.PLAIN_OBJECT_TYPE;
import static org.raml.jaxrs.generator.GObjectType.SCALAR;
import static org.raml.jaxrs.generator.GObjectType.UNION_TYPE;
import static org.raml.jaxrs.generator.GObjectType.XML_OBJECT_TYPE;

/**
 * Created by Jean-Philippe Belanger on 12/7/16.
 * Just potential zeroes and ones
 */
public class GeneratorType {

    private final GObjectType objectType;
    private final GType declaredType;

    public static GeneratorType generatorFrom(GType typeDeclaration) {

        // Just a plain type we found.
        if (typeDeclaration.isJson()) {
            return  new GeneratorType(JSON_OBJECT_TYPE,
                    typeDeclaration);
        }

        if ( typeDeclaration.isUnion()) {

            return new GeneratorType(UNION_TYPE, typeDeclaration);
        }

        if (typeDeclaration.isXml()) {

            return new GeneratorType(XML_OBJECT_TYPE, typeDeclaration);
        }

        if (typeDeclaration.isObject()) {

            return new GeneratorType(PLAIN_OBJECT_TYPE, typeDeclaration);
        }

        if (typeDeclaration.isEnum()) {

            return new GeneratorType(ENUMERATION_TYPE, typeDeclaration);
        }


        return new GeneratorType(SCALAR, typeDeclaration);
    }

    public GeneratorType(GObjectType objectType,  GType declaredType) {

        this.objectType = objectType;
        this.declaredType = declaredType;
    }

    public GType getDeclaredType() {
        return declaredType;
    }

    public GObjectType getObjectType() {
        return objectType;
    }

    public void construct(CurrentBuild currentBuild) {

        if ( getObjectType() != GObjectType.SCALAR) {

            declaredType.construct(currentBuild, objectType);
        }

    }
}
