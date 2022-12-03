apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreDomain))
    "implementation"(project(Modules.articleDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Google.gson)
}