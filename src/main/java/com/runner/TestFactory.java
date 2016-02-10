package com.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.AbsolutePathCalculator;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepFinder;
import org.junit.Test;

import com.automation.framework.core.TestSetup;
import com.site.steps.NavigationSteps;
import com.thoughtworks.paranamer.NullParanamer;
/*

import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.TestSuiteFinishedEvent;
import ru.yandex.qatools.allure.events.TestSuiteStartedEvent;*/

public class TestFactory extends JUnitStories {
    public static final String UUID = java.util.UUID.randomUUID().toString();
    private static boolean dryRun;
   // private Allure allure = Allure.LIFECYCLE;
	private Configuration configuration;
	  //private final CrossReference xref = new CrossReference();
	  
	public TestFactory() {
		super();
		configuration = new Configuration() {
		};
        
		Properties viewResources = new Properties();
		viewResources.setProperty("decorateNonHtml", "true");
		/*Class<? extends Embeddable> embeddableClass = this.getClass();		
        ParameterConverters parameterConverters = new ParameterConverters();
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(),
                new LoadFromClasspath(embeddableClass), parameterConverters);*/

		configuration.useFailureStrategy(new RethrowingFailure());
		configuration.useKeywords(new LocalizedKeywords(Locale.ENGLISH));
		configuration.usePathCalculator(new AbsolutePathCalculator());
		configuration.useParameterControls(new ParameterControls());
		configuration.useParameterConverters(new ParameterConverters());
		configuration.useParanamer(new NullParanamer());
		configuration.usePendingStepStrategy(new PassingUponPendingStep());
		configuration.useStepCollector(new MarkUnmatchedStepsAsPending());
		configuration.useStepdocReporter(new PrintStreamStepdocReporter());
		configuration.useStepFinder(new StepFinder());
		configuration.useStepMonitor(new SilentStepMonitor());
		configuration
				.useStepPatternParser(new RegexPrefixCapturingPatternParser());
		configuration.useStoryControls(new StoryControls());
		configuration.useStoryLoader(new LoadFromClasspath());
//		configuration.useStoryLoader(new LoadFromRelativeFile(CodeLocations.codeLocationFromClass(OMSOrderSteps.class)));
		configuration.useStoryParser(new RegexStoryParser(configuration
				.keywords()));
		configuration.useStoryPathResolver(new UnderscoredCamelCaseResolver());
		configuration.useStoryReporterBuilder(new StoryReporterBuilder()
                 .withDefaultFormats().withPathResolver(new FilePrintStreamFactory.ResolveToPackagedName())
                 .withFailureTrace(true).withFailureTraceCompression(true)
                // .withReporters(new AllureReporter())
                 .withDefaultFormats()
                 .withFormats(Format.CONSOLE, Format.HTML, Format.XML)
                 .withFailureTrace(false));
                 //.withCrossReference(xref)
                 //.withViewResources(viewResources).withFormats(Format.HTML, Format.STATS, Format.CONSOLE));
        //.useStepMonitor(xref.getStepMonitor());     
                 //to use CustomHtmlOutputfrom “renderTable” located in ftl/jbehave-html-output.ftl 
                 //.withViewResources(viewResources).withFormats(CustomHtmlOutput.FORMAT, Format.STATS, Format.CONSOLE));
                 //.withFailureTrace(true).withFailureTraceCompression(true).withCrossReference(xref));
		//configuration.useParameterConverters(parameterConverters)
        // use '%' instead of '$' to identify parameters
        //.useStepPatternParser(new RegexPrefixCapturingPatternParser("%"));
		//configuration.useViewGenerator(new FreemarkerViewGenerator())
				
		
 
		EmbedderControls embedderControls = configuredEmbedder()
				.embedderControls();
		embedderControls.doBatch(false);
		embedderControls.doGenerateViewAfterStories(true);
		embedderControls.doIgnoreFailureInStories(false);
		embedderControls.doIgnoreFailureInView(false);
		embedderControls.doSkip(false);
		embedderControls.doVerboseFailures(false);
		embedderControls.doVerboseFiltering(false);
		embedderControls.useStoryTimeoutInSecs(6000);
		embedderControls.useThreads(1);
		configuredEmbedder().useMetaFilters(metaFilters());

	}
 
	public static boolean isDryRun() {
        return dryRun;
    }

	@Override
	@Test
    public void run() throws Throwable{
        //allure.fire(new TestSuiteStartedEvent(UUID, "oms"));
        try {
            super.run();
        } catch (Throwable ignored) {
        }
      //  allure.fire(new TestSuiteFinishedEvent(UUID));
    }
    
	@Override
	public Configuration configuration() {
		
		return configuration;
	}

	@Override
	public List<CandidateSteps> candidateSteps() {
		/*return new InstanceStepsFactory(configuration(), new JBehaveTest())
				.createCandidateSteps();*/
		return new InstanceStepsFactory(configuration(),new TestSetup(), new NavigationSteps())
				.createCandidateSteps();
	}
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(),new TestSetup(), new NavigationSteps());
	}

	protected List<String> metaFilters() {
        return  Arrays.asList("+author *", "+theme *","-skip");
    }
	
	@Override
	protected List<String> storyPaths() {
		return Arrays
				.asList("Registration.story");
	}

	
}