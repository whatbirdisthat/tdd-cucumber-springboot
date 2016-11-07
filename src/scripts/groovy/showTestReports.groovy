import groovy.io.FileType
import net.tqxr.testframework.categories.TestDescription
import net.tqxr.testframework.reporting.TestNameTransformer

import static net.tqxr.testframework.reporting.AsciiColourHelper.*

def testNameTransformer = new TestNameTransformer()

final String CUTE_GRAPE = '\uD83C\uDF47'

println "\n" + GREEN + CUTE_GRAPE + '  TEST RESULTS ' + CUTE_GRAPE + RESET + ""

def list = []

def dir = new File("target/surefire-reports/")
dir.eachFileRecurse(FileType.FILES) { file ->
    if (file.isFile() && file.absoluteFile.name.endsWith(".xml")) {
        list << file
    }
}

def failureCount = 0

list.each {

    def testsuite = new XmlParser().parseText(it.text)

    def clz = Class.forName(testsuite.@name).getDeclaredAnnotation(TestDescription)
    String val = clz ? clz.value() : ""
    println val

    if (testsuite.@failures != "0") {
        failureCount ++;
        print RED.underline()
    } else {
        print CYAN.underline()
    }

    print '' + testsuite.@name
    println RESET

    RED.reset()
    BLUE.reset()

    testsuite.testcase.each {

        boolean isSkipped = it.skipped || false
        boolean isFailed = it.failure || false

        print "${testNameTransformer.passFailSkip(isFailed, isSkipped)}"
        if (isSkipped) {
            print BLACK.bright()
        }
        print "  " + testNameTransformer.transformTestName(it.@name as String)
        if (isSkipped) {
            print " (${it.skipped.@message.join(', ')})"
        }

        println "${RESET}"
    }

}

println "\n\n"

if (failureCount > 0) {
    exit(1)
} else {
    println "\n\n${GREEN.underline()}SUCCESS!${RESET}\n"
    GREEN.reset()
}