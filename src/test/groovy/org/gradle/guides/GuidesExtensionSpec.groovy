/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.guides

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class GuidesExtensionSpec extends Specification {

    Project project = ProjectBuilder.builder().build()

    def 'Declarative style works'() {
        when:
        project.apply plugin : 'org.gradle.guides.base'

        project.allprojects {
            guide {
                repoPath   'foo/bar'
                mainAuthor 'John Doe'
            }
        }
        project.evaluate()

        then:
        project.guide.repoPath == 'foo/bar'
        project.guide.mainAuthor == 'John Doe'
    }

    def 'can change repositoryPath property using repoPath property'() {
        when:
        project.apply plugin : 'org.gradle.guides.base'

        project.allprojects {
            guide {
                repoPath   'foo/bar'
            }
        }
        project.evaluate()

        then:
        project.guide.repoPath == 'foo/bar'
        project.guide.repositoryPath.get() == 'foo/bar'
    }

    def 'can change repoPath property using repositoryPath property'() {
        when:
        project.apply plugin : 'org.gradle.guides.base'

        project.allprojects {
            guide {
                repositoryPath.set('foo/bar')
            }
        }
        project.evaluate()

        then:
        project.guide.repoPath == 'foo/bar'
        project.guide.repositoryPath.get() == 'foo/bar'
    }
}
