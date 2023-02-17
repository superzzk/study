package jacksonStudy.inheritance.ignoring_properties_from_supertype.introspection;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import jacksonStudy.inheritance.ignoring_properties_from_supertype.mix_ins.Car;
import jacksonStudy.inheritance.ignoring_properties_from_supertype.mix_ins.Vehicle;

/**
 * ignore Vehicle.model, Crossover.towingCapacity and all properties declared in the Car class
 **/
public class IgnoranceIntrospector extends JacksonAnnotationIntrospector {
    public boolean hasIgnoreMarker(AnnotatedMember m) {
        return m.getDeclaringClass() == Vehicle.class && m.getName() == "model"
                || m.getDeclaringClass() == Car.class
                || m.getName() == "towingCapacity"
                || super.hasIgnoreMarker(m);
    }
}