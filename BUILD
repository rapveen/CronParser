# BUILD file
java_library(
    name = "cronparser-lib",
    srcs = glob(["src/main/java/com/THA/cronparser/*.java"]),
    visibility = ["//visibility:public"],
)

java_binary(
    name = "cronparser",
    main_class = "com.THA.cronparser.CronParser",
    runtime_deps = [":cronparser-lib"],
)

java_test(
    name = "cronparser-tests",
    srcs = glob(["src/test/java/com/THA/cronparser/*.java"]),
    deps = [
        ":cronparser-lib",
        "@maven//:org_junit_jupiter_junit_jupiter_engine",
        "@maven//:org_junit_jupiter_junit_jupiter_api",
    ],
    test_class = "com.THA.cronparser.CronParserTest",
    runtime_deps = ["@maven//:org_junit_platform_junit_platform_launcher"],
)