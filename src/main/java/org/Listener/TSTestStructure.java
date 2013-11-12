package org.Listener;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public interface TSTestStructure {
	@Test
	@Parameters("tcId")
	public abstract void test(@Optional("") String id);
}