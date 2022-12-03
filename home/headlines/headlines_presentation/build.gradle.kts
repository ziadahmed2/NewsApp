apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreDomain))
    "implementation"(project(Modules.headlinesDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Paging.pagingCompose)
    "implementation"(Google.gson)
}