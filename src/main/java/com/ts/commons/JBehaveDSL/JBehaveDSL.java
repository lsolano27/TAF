package com.ts.commons.JBehaveDSL;

import static java.util.Arrays.asList;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.testng.annotations.Test;

public class JBehaveDSL extends JUnitStory  {
	
	private String storyName;
	
	public JBehaveDSL(String storyName)
	{
		this.storyName = storyName;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public List candidateSteps() 
	{
		InstanceStepsFactory stepsFactory = new InstanceStepsFactory(configuration(), this);
		return stepsFactory.createCandidateSteps();
	}
	
	@Override
    public Configuration configuration() 
	{
        Configuration result = super.configuration();
        result.useStoryReporterBuilder(getStoryReporterBuilder());        
        return result;
    }
	
	@SuppressWarnings("deprecation")
	private StoryReporterBuilder getStoryReporterBuilder() 
	{
		StoryReporterBuilder result = new StoryReporterBuilder();
		result.withFormats(Format.CONSOLE, Format.HTML, Format.STATS);
		return result;
	}
	
	@Override
	@Test
	public void run() throws Throwable {
		
		Embedder embedder = configuredEmbedder();
		StoryPathResolver pathResolver = embedder.configuration().storyPathResolver();
		String storyPath = pathResolver.resolve(this.getClass());		
		int d = storyPath.lastIndexOf("/");
		storyPath = storyPath.substring(0, d) + "/" + this.storyName;
		
		try 
		{
			embedder.runStoriesAsPaths(asList(storyPath));
		} 
		finally 
		{
			embedder.generateCrossReference();
		}
	}
}