package com.danoff.demo.cucumber;

import org.junit.runner.RunWith;

import com.danoff.demo.BaseIntegrationTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/it/resources/features")
public class CucumberIT extends BaseIntegrationTest {

}
