apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreDomain))
    "implementation"(Coil.coilCompose)
}