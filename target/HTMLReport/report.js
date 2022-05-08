$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Loggin.feature");
formatter.feature({
  "line": 2,
  "name": "Loggin",
  "description": "",
  "id": "loggin",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Enable"
    },
    {
      "line": 1,
      "name": "@RegressionTest"
    },
    {
      "line": 1,
      "name": "@LogginFeature"
    }
  ]
});
formatter.scenario({
  "line": 5,
  "name": "Loggin Feature",
  "description": "",
  "id": "loggin;loggin-feature",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@TC-001"
    },
    {
      "line": 4,
      "name": "@Acceptance"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "I click on Search button on Home page",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});