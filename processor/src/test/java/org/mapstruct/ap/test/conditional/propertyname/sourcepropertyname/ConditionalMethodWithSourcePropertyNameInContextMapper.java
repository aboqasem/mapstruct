/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conditional.propertyname.sourcepropertyname;

import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Condition;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SourcePropertyName;
import org.mapstruct.ap.test.conditional.propertyname.Address;
import org.mapstruct.ap.test.conditional.propertyname.AddressDto;
import org.mapstruct.ap.test.conditional.propertyname.DomainModel;
import org.mapstruct.ap.test.conditional.propertyname.Employee;
import org.mapstruct.ap.test.conditional.propertyname.EmployeeDto;
import org.mapstruct.factory.Mappers;

/**
 * @author Nikola Ivačič
 * @author Mohammad Al Zouabi
 */
@Mapper
public interface ConditionalMethodWithSourcePropertyNameInContextMapper {

    ConditionalMethodWithSourcePropertyNameInContextMapper INSTANCE
        = Mappers.getMapper( ConditionalMethodWithSourcePropertyNameInContextMapper.class );

    @Mapping(target = "country", source = "originCountry")
    Employee map(EmployeeDto employee, @Context PresenceUtils utils);

    Address map(AddressDto addressDto, @Context PresenceUtils utils);

    class PresenceUtils {
        Set<String> visited = new LinkedHashSet<>();

        @Condition
        public boolean isNotBlank(String value, @SourcePropertyName String propName) {
            visited.add( propName );
            return value != null && !value.trim().isEmpty();
        }
    }

    @Mapping(target = "country", source = "originCountry")
    Employee map(EmployeeDto employee, @Context PresenceUtilsAllProps utils);

    Address map(AddressDto addressDto, @Context PresenceUtilsAllProps utils);

    class PresenceUtilsAllProps {
        Set<String> visited = new LinkedHashSet<>();

        @Condition
        public boolean collect(@SourcePropertyName String propName) {
            visited.add( propName );
            return true;
        }
    }

    @Mapping(target = "country", source = "originCountry")
    Employee map(EmployeeDto employee, @Context PresenceUtilsAllPropsWithSource utils);

    Address map(AddressDto addressDto, @Context PresenceUtilsAllPropsWithSource utils);

    @BeforeMapping
    default void before(DomainModel source, @Context PresenceUtilsAllPropsWithSource utils) {
        String lastProp = utils.visitedSegments.peekLast();
        if ( lastProp != null && source != null ) {
            utils.path.offerLast( lastProp );
        }
    }

    @AfterMapping
    default void after(@Context PresenceUtilsAllPropsWithSource utils) {
        utils.path.pollLast();
    }

    class PresenceUtilsAllPropsWithSource {
        Deque<String> visitedSegments = new LinkedList<>();
        Deque<String> visited = new LinkedList<>();
        Deque<String> path = new LinkedList<>();

        @Condition
        public boolean collect(@SourcePropertyName String propName) {
            visitedSegments.offerLast( propName );
            path.offerLast( propName );
            visited.offerLast( String.join( ".", path ) );
            path.pollLast();
            return true;
        }
    }
}
