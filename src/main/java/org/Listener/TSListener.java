package org.Listener;

import org.testng.annotations.Listeners;

import com.ts.commons.TestCaseUtil;


@Listeners({ITSListener.class})
public  abstract class TSListener extends TestCaseUtil{}