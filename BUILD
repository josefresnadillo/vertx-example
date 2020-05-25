load("@rules_java//java:defs.bzl", "java_binary")
load("@rules_java//java:defs.bzl", "java_library")
load("@rules_java//java:defs.bzl", "java_plugin")
load("@rules_java//java:defs.bzl", "java_test")

java_plugin(
    name = "dagger_plugin",
    processor_class = "dagger.internal.codegen.ComponentProcessor",
    deps = [
        "@maven//:com_google_dagger_dagger_compiler",
        "@maven//:com_google_dagger_dagger",
        "@maven//:javax_inject_javax_inject",
        "@maven//:com_google_dagger_dagger_producers",
        "@maven//:com_google_guava_guava",
    ],
    generates_api = 1
)

java_plugin(
    name = "lombok_plugin",
    processor_class = "lombok.launch.AnnotationProcessorHider$AnnotationProcessor",
    deps = [
        "@maven//:org_projectlombok_lombok",
    ],
    generates_api = 1,
)

java_plugin(
    name = "vertx_plugin",
    processor_class = "io.vertx.codegen.CodeGenProcessor",
    deps = [
        "@maven//:io_vertx_vertx_core",
        "@maven//:io_vertx_vertx_codegen"
    ],
    generates_api = 1
)

java_plugin(
    name = "vertx_proxy_plugin",
    processor_class = "io.vertx.serviceproxy.generator.ServiceProxyProcessor",
    deps = [
        "@maven//:io_vertx_vertx_core",
        "@maven//:io_vertx_vertx_service_proxy",
        "@maven//:io_vertx_vertx_codegen",
        "@maven//:io_vertx_vertx_web_client",
        "@maven//:io_vertx_vertx_config_yaml",
        "@maven//:io_vertx_vertx_config",
        "@maven//:io_vertx_vertx_rx_java2",
        "@maven//:io_vertx_vertx_web",
        "@maven//:io_vertx_vertx_junit5",
        "@maven//:io_vertx_vertx_unit",
        "@maven//:io_vertx_vertx_config_kubernetes_configmap"
    ],
    generates_api = 1
)

java_binary(
    name = "compile",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    main_class = "com.example.starter.Main",
    deps = ["@maven//:org_apache_commons_commons_lang3",
            "@maven//:io_vertx_vertx_web_client",
            "@maven//:io_vertx_vertx_config_yaml",
            "@maven//:io_vertx_vertx_config",
            "@maven//:io_vertx_vertx_rx_java2",
            "@maven//:io_vertx_vertx_web",
            "@maven//:io_vertx_vertx_junit5",
            "@maven//:io_vertx_vertx_core",
            "@maven//:io_vertx_vertx_service_proxy",
            "@maven//:io_vertx_vertx_codegen",
            "@maven//:io_vertx_vertx_unit",
            "@maven//:io_vertx_vertx_config_kubernetes_configmap",
            "@maven//:org_junit_jupiter_junit_jupiter_api",
            "@maven//:org_junit_jupiter_junit_jupiter_engine",
            "@maven//:com_google_dagger_dagger",
            "@maven//:com_google_dagger_dagger_producers",
            "@maven//:com_google_guava_guava",
            "@maven//:org_projectlombok_lombok",
            "@maven//:com_fasterxml_jackson_datatype_jackson_datatype_jsr310",
            "@maven//:io_swagger_core_v3_swagger_annotations",
            "@maven//:io_swagger_core_v3_swagger_jaxrs2",
            "@maven//:javax_ws_rs_javax_ws_rs_api",
            "@maven//:javax_servlet_javax_servlet_api",
            "@maven//:org_mockito_mockito_core",
            "@maven//:junit_junit",
            "@maven//:org_assertj_assertj_core",
            "@maven//:com_fasterxml_jackson_core_jackson_annotations",
            "@maven//:com_fasterxml_jackson_core_jackson_core",
            "@maven//:com_fasterxml_jackson_core_jackson_databind",
            "@maven//:com_fasterxml_jackson_dataformat_jackson_dataformat_yaml",
            "@maven//:org_slf4j_slf4j_api",
            "@maven//:javax_inject_javax_inject",
            "@maven//:io_reactivex_rxjava2_rxjava",
            "@maven//:io_reactivex_rxjava",
            ],
    plugins = [":lombok_plugin", ":dagger_plugin", ":vertx_plugin", ":vertx_proxy_plugin",],
)


java_test(
    name = "AllTests",
    srcs = glob(['src/test/java/**/*.java']),
    size = "small",
    runtime_deps = [
        ":compile"
    ],
)