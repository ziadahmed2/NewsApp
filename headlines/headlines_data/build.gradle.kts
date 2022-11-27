apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreData))
    "implementation"(project(Modules.coreDomain))
    "implementation"(project(Modules.headlinesDomain))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.gsonConverter)
    "implementation"(Paging.paging)
}