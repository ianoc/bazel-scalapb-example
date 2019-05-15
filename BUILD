load("@io_bazel_rules_scala//scala_proto:scala_proto_toolchain.bzl", "scala_proto_toolchain")

scala_proto_toolchain(
    name = "scala_proto_toolchain_configuration",
    with_grpc = True,
    with_flat_package = True,
    visibility = [ "//visibility:public" ],
    implicit_compile_deps = [
        "//scala-app:messages_proto_support_lib"
    ]
)

toolchain(
    name = "scalapb_toolchain",
    toolchain = ":scala_proto_toolchain_configuration",
    toolchain_type = "@io_bazel_rules_scala//scala_proto:toolchain_type",
    visibility = ["//visibility:public"],
)