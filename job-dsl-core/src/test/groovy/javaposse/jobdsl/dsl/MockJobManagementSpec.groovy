package javaposse.jobdsl.dsl

import hudson.util.VersionNumber
import spock.lang.Specification

class MockJobManagementSpec extends Specification {
    MockJobManagement mockJobManagement = Spy(MockJobManagement)

    def 'job permissions are pre-set'() {
        when:
        Set<String> permissions = mockJobManagement.getPermissions('hudson.security.AuthorizationMatrixProperty')

        then:
        permissions.size() == 12
        'hudson.model.Item.Delete' in permissions
        'hudson.model.Item.Configure' in permissions
        'hudson.model.Item.Read' in permissions
        'hudson.model.Item.Discover' in permissions
        'hudson.model.Item.Build' in permissions
        'hudson.model.Item.Workspace' in permissions
        'hudson.model.Item.Cancel' in permissions
        'hudson.model.Item.Release' in permissions
        'hudson.model.Item.ExtendedRead' in permissions
        'hudson.model.Run.Delete' in permissions
        'hudson.model.Run.Update' in permissions
        'hudson.scm.SCM.Tag' in permissions
    }

    def 'queueJob validates name argument'() {
        when:
        mockJobManagement.queueJob(name)

        then:
        thrown(NameNotProvidedException)

        where:
        name << ['', null]
    }

    def 'getJenkinsVersion returns a version'() {
        when:
        VersionNumber versionNumber = mockJobManagement.jenkinsVersion

        then:
        versionNumber != null
    }
}
