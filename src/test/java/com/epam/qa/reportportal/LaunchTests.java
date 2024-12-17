package com.epam.qa.reportportal;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Execution(ExecutionMode.CONCURRENT)
public class LaunchTests extends BaseTest{

    private static final String PLATFORM_ATTRIBUTE = "platform";
    private static final String BUILD_ATTRIBUTE = "build";
    private static final String TOTAL_ATTRIBUTE = "total";
    private static final String PASSED_ATTRIBUTE = "passed";
    private static final String FAILED_ATTRIBUTE = "failed";
    private static final String SKIPPED_ATTRIBUTE = "skipped";
    private static final String CHECKED_STATE = "checked";
    private static final String UNCHECKED_STATE = "unchecked";

    @ParameterizedTest(name = "Test {index}: {1} {2}")
    @CsvFileSource(resources = "/testData/launch_results.csv", numLinesToSkip = 1)
    public void checkLaunchResults(int index, String launchNumber, String launchName,
                      String total,
                      String passed,
                      String failed,
                      String skipped) {
        launchSteps.verifyLaunchName(launchNumber, launchName);
        launchSteps.verifyLaunchCount(total, TOTAL_ATTRIBUTE, launchNumber, launchName);
        launchSteps.verifyLaunchCount(passed, PASSED_ATTRIBUTE, launchNumber, launchName);
        launchSteps.verifyLaunchCount(failed, FAILED_ATTRIBUTE, launchNumber, launchName);
        launchSteps.verifyLaunchCount(skipped, SKIPPED_ATTRIBUTE, launchNumber, launchName);
    }

    @ParameterizedTest(name = "Test {index}: {1} {2}")
    @CsvFileSource(resources = "/testData/launch_attribute.csv", numLinesToSkip = 1)
    public void checkLaunchAttributes(int index, String launchNumber, String launchName,
                       String platform,
                       String build) {
        launchSteps.verifySpecificAttributeForLaunch(platform, PLATFORM_ATTRIBUTE, launchNumber, launchName);
        launchSteps.verifySpecificAttributeForLaunch(build, BUILD_ATTRIBUTE, launchNumber, launchName);
    }

    @ParameterizedTest(name = "Test {index}: {1} {2}")
    @CsvFileSource(resources = "/testData/launch_name.csv", numLinesToSkip = 1)
    public void checkLaunchSelection(int index, String number, String name,
                       String totalLaunchName) {
        launchSteps.checkSpecificLaunch(number, name);
        launchSteps.verifyCheckboxStateForLaunch(number, name, CHECKED_STATE);
        launchSteps.verifySelectedLaunch(totalLaunchName);
        launchSteps.uncheckSpecificLaunch(number, name);
        launchSteps.verifyCheckboxStateForLaunch(number, name, UNCHECKED_STATE);
        launchSteps.verifySelectedItemsNotVisible();
    }
}
