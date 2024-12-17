package com.epam.qa.reportportal.runner;

import com.epam.qa.reportportal.steps.MainSteps;
import com.epam.qa.reportportal.utils.EnvironmentProviderHook;
import com.epam.qa.reportportal.utils.ResourceUtils;
import com.epam.qa.reportportal.utils.TestParameters;
import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import com.epam.reportportal.jbehave.ReportPortalScenarioFormat;
import com.epam.reportportal.jbehave.ReportPortalStepFormat;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.reporters.*;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.epam.qa.reportportal.utils.TestParameters.META_FILTERS;
import static java.util.stream.Collectors.toList;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ReportPortalStoryRunner extends JUnitStories {

    private static final Logger LOGGER;

    private static final int THREADS_COUNT = 1;
    private static final String COMMA_DELIMITER = ",";
    private static final String MAX_TIMEOUT_STORY_IN_SECS = "6000";

    private TestContextManager testContextManager = new TestContextManager(getClass());

    @Autowired
    private ApplicationContext context;
    @Autowired
    private MainSteps mainSteps;

    static {
        System.setProperty("rp.tags",
                TestParameters.SYSTEM_ENVIRONMENT + ";" + META_FILTERS.replaceAll("(,| )+", ";") + ";");
        EnvironmentProviderHook.ensureEnvironmentSet();
        LOGGER = LoggerFactoryProvider.getLogger();
        Locale.setDefault(Locale.ENGLISH);
    }

    public ReportPortalStoryRunner() {
        try {
            testContextManager.prepareTestInstance(this);
        } catch (Exception e) {
            throw new RuntimeException("Cannot init runner instance", e);
        }
    }

    @Override
    public void run() {
        mainSteps.setBrowser(TestParameters.BROWSER);
        super.run();
    }

    /**
     * Main.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        ReportPortalStoryRunner runner = new ReportPortalStoryRunner();
        runner.runTestsWithJUnitPlatform();
    }

    private void runTestsWithJUnitPlatform() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(ReportPortalStoryRunner.class))
                .build();

        Launcher launcher = LauncherFactory.create();
        launcher.execute(request);
    }


    @Override
    public Configuration configuration() {
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");
        viewResources.put("encoding", "UTF-8");
        TableTransformers transformers = new TableTransformers();
        return new MostUsefulConfiguration()
                .useParameterConverters(new ParameterConverters(transformers))
                .useParameterControls(getParameterControls())
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryLoader(new LoadFromClasspath(getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(getClass()))
                        .withViewResources(viewResources)
                        .withFormats(Format.CONSOLE, Format.HTML, new ReportPortalStepFormat()));
    }

    @Override
    public Embedder configuredEmbedder() {
        Embedder embedder = super.configuredEmbedder();
        embedder.useMetaFilters(getMetaFilters());
        embedder.embedderControls()
                .useStoryTimeouts(MAX_TIMEOUT_STORY_IN_SECS)
                .doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true)
                .useThreads(THREADS_COUNT)
                .generateViewAfterStories();

        return embedder;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), context);
    }


    @Override
    public List<String> storyPaths() {
        URL resourcesURL = ResourceUtils.getResourcesURL();
        return new StoryFinder().findPaths(resourcesURL, getStoriesPath(), null);
    }

    private String getStoriesPath() {
        String qaaStory = TestParameters.STORY_TO_RUN;
        if (!StringUtils.containsAny(qaaStory, '/', '\\')) {
            qaaStory = "**/" + qaaStory;
        }
        return "stories/" + qaaStory + ".story";
    }

    private ParameterControls getParameterControls() {
        return new ParameterControls().useDelimiterNamedParameters(true);
    }

    private List<String> getMetaFilters() {
        return BooleanUtils.toBoolean("false")
                ? Collections.singletonList("+make-screenshot")
                : Stream.of(META_FILTERS).flatMap(Pattern.compile(COMMA_DELIMITER)::splitAsStream)
                .collect(toList());
    }

}
