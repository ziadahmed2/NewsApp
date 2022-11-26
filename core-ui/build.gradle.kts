apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(Coil.coilCompose)
}