import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

apply<DetektPlugin>()

configure<DetektExtension> {
    allRules = true
//    source.setFrom(projectDir)
    config.setFrom("$rootDir/config/detekt/detekt.yml")
    baseline = file("$rootDir/config/detekt/baseline.xml")
}

tasks.register<Detekt>("detektFormat") {
    autoCorrect = true
}

tasks.withType<Detekt> {
    // Disable task caching
    outputs.upToDateWhen { false }

    val filesToCheck: List<String>? = System.getProperty("detekt.filesToCheck")
        ?.split(",")
        ?.filter { it.isNotBlank() }
    if (filesToCheck != null) {
        println("Formatting: $filesToCheck")
    } else {
        println("Formatting all files...")
    }
    setSource(filesToCheck ?: projectDir)

    reports {
        html.required.set(true)
        sarif.required.set(false)
        md.required.set(false)
        xml.required.set(false)
        txt.required.set(false)
    }

    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")

    parallel = true

    buildUponDefaultConfig = true

    allRules = true

    // Target version of the generated JVM bytecode. It is used for type resolution.
    jvmTarget = "1.8"
}

dependencies {
    add("detektPlugins", libs.findLibrary("detekt.ruleset.compiler").get())
    add("detektPlugins", libs.findLibrary("detekt.ruleset.ktlint").get())
    add("detektPlugins", libs.findLibrary("detekt.ruleset.compose").get())

    /*
    Adds the detekt API dependency. You need this for writing the custom rules.
    Adds test dependencies. To test your rules you need detekt-test.
    It also requires assertj-core as a dependency.
     */

    /*

    // 1
    compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.23.1")
    // 2
    testImplementation("io.gitlab.arturbosch.detekt:detekt-api:1.23.1")
    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:1.17.1")
    testImplementation("org.assertj:assertj-core:3.19.0")
    testImplementation("junit:junit:4.13.2")

     */
}
