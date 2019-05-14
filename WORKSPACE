workspace(name = "scalapb_bug_example")

rules_scala_version="b2e12827240fc488bbe5ebbbbcbc7a4f12ca02f2"
scala_version = "2.12.8"

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository", "new_git_repository")

http_archive(
    name = "io_bazel_rules_scala",
    url = "https://github.com/bazelbuild/rules_scala/archive/%s.zip" % rules_scala_version,
    type = "zip",
    strip_prefix= "rules_scala-%s" % rules_scala_version
)

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories((scala_version, {
   "scala_compiler": "f34e9119f45abd41e85b9e121ba19dd9288b3b4af7f7047e86dc70236708d170",
   "scala_library": "321fb55685635c931eba4bc0d7668349da3f2c09aee2de93a70566066ff25c28",
   "scala_reflect": "4d6405395c4599ce04cea08ba082339e3e42135de9aae2923c9f5367e957315a"
}))

load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains")
scala_register_toolchains()

load("@io_bazel_rules_scala//scala_proto:scala_proto.bzl", "scala_proto_repositories")
scala_proto_repositories(scala_version = scala_version)

register_toolchains("//:scalapb_toolchain")

load("//3rdparty:workspace.bzl", "maven_dependencies")
maven_dependencies()

http_archive(
    name = "com_google_protobuf",
    sha256 = "9510dd2afc29e7245e9e884336f848c8a6600a14ae726adb6befdb4f786f0be2",
    urls = ["https://github.com/protocolbuffers/protobuf/archive/v3.6.1.3.zip"],
    strip_prefix = "protobuf-3.6.1.3",
)

new_git_repository(
    name = "com_thesamet_scalapb",
    commit = "9c322d6f76f7063b5c937a94a15095b3182f1867",
    remote = "https://github.com/scalapb/ScalaPB.git",
    build_file_content = """
proto_library(
    name = "scalapb_runtime_proto",
    srcs = [ "protobuf/scalapb/scalapb.proto" ],
    visibility = [ "//visibility:public" ],
    deps = [ "@com_google_protobuf//:descriptor_proto" ]
)
    """
)