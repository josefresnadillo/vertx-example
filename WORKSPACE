load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "2.0.1"
RULES_JVM_EXTERNAL_SHA = "55e8d3951647ae3dffde22b4f7f8dee11b3f70f3f89424713debd7076197eaca"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "org.apache.commons:commons-lang3:3.9",
        "junit:junit:4.13",
        "io.vertx:vertx-web-client:3.8.5",
        "io.vertx:vertx-config-yaml:3.8.5",
        "io.vertx:vertx-config:3.8.5",
        "io.vertx:vertx-rx-java2:3.8.5",
        "io.vertx:vertx-core:3.8.5",
        "io.vertx:vertx-web:3.8.5",
        "io.vertx:vertx-junit5:3.8.5",
        "io.vertx:vertx-service-proxy:3.8.5",
        "io.vertx:vertx-codegen:3.8.5",
        "io.vertx:vertx-unit:3.8.5",
        "io.vertx:vertx-config-kubernetes-configmap:3.8.5",

        "org.junit.jupiter:junit-jupiter-api:5.4.0",
        "org.junit.jupiter:junit-jupiter-engine:5.4.0",
        "org.junit.platform:junit-platform-console:1.5.2",
        "org.mockito:mockito-junit-jupiter:3.3.3",

        "com.google.dagger:dagger:2.22.1",
        "com.google.dagger:dagger-compiler:2.22.1",
        "com.google.dagger:dagger-producers:2.22.1",
        "com.google.guava:guava:27.1-jre",

        "org.projectlombok:lombok:1.18.12",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.4.0",
        "io.swagger.core.v3:swagger-annotations:2.1.1",
        "io.swagger.core.v3:swagger-jaxrs2:2.1.0",
        "javax.ws.rs:javax.ws.rs-api:2.1",
        "javax.servlet:javax.servlet-api:3.1.0",
        "org.mockito:mockito-core:2.24.5",
        "org.assertj:assertj-core:3.8.0",
        "com.fasterxml.jackson.core:jackson-annotations:2.9.9",
        "com.fasterxml.jackson.core:jackson-core:2.9.9",
        "com.fasterxml.jackson.core:jackson-databind:2.9.9.1",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.9",
        "javax.inject:javax.inject:1",
        "org.slf4j:slf4j-api:1.7.25",
        "io.reactivex.rxjava2:rxjava:2.2.12",
        "io.reactivex:rxjava:1.3.8",
    ],
    repositories = [
        "https://jcenter.bintray.com/",
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ]
)