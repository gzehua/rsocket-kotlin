/*
 * Copyright 2015-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

plugins {
    //this version can not be moved to version catalog, because it's in settings.gradle.kts
    id("com.gradle.enterprise") version "3.7.2"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

rootProject.name = "rsocket-kotlin"

include("benchmarks")

include("rsocket-core")
include("rsocket-test")

include("rsocket-transport-tests")
include("rsocket-transport-local")

//ktor transport modules
include(
    "rsocket-transport-ktor",
    "rsocket-transport-ktor:rsocket-transport-ktor-tcp",
    "rsocket-transport-ktor:rsocket-transport-ktor-websocket",
    "rsocket-transport-ktor:rsocket-transport-ktor-websocket-client",
    "rsocket-transport-ktor:rsocket-transport-ktor-websocket-server",
)

include("rsocket-transport-nodejs-tcp")
