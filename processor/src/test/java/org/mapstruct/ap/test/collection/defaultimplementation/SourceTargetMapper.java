/**
 *  Copyright 2012-2013 Gunnar Morling (http://www.gunnarmorling.de/)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.test.collection.defaultimplementation;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mappers;

@Mapper
public interface SourceTargetMapper {

    public static SourceTargetMapper INSTANCE = Mappers.getMapper( SourceTargetMapper.class );

    Target sourceToTarget(Source source);

    TargetFoo sourceFooToTargetFoo(SourceFoo sourceFoo);

    List<TargetFoo> sourceFoosToTargetFoos(List<SourceFoo> foos);

    Set<TargetFoo> sourceFoosToTargetFoos(Set<SourceFoo> foos);

    Collection<TargetFoo> sourceFoosToTargetFoos(Collection<SourceFoo> foos);

    Iterable<TargetFoo> sourceFoosToTargetFoos(Iterable<SourceFoo> foos);
}
