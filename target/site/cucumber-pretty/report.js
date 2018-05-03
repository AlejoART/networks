$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("/Users/sayed.maudoudi/Desktop/cucumber-base/src/test/resources/features/google/google.feature");
formatter.feature({
  "line": 1,
  "name": "As a google user, I want to be able to search for google map",
  "description": "",
  "id": "as-a-google-user,-i-want-to-be-able-to-search-for-google-map",
  "keyword": "Feature"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "I navigate to google page",
  "keyword": "Given "
});
formatter.match({
  "location": "GoogleSteps.i_navigate_to_gformatter.result({formatter.result({
  "duration": 4002932668,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "should search for google map",
  "description": "",
  "id": "as-a-google-user,-i-want-to-be-able-to-search-for-google-map;should-search-for-google-map",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 5,
      "name": "@googleSearch"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "I enter \"maps\" in search text field",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I click on google search button",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I click on google maps tab from the list",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "I view the google maps page and enter \"webpt\" in the google maps search field",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "I see the address of webpt",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "maps",
      "offset": 9
    }
  ],
  "location": "GoogleSteps.i_enter_text_in_search_text_field(String)"
});
formatter.result({
  "duration": 271399184,
  "status": "passed"
});
formatter.match({
  "location": "GoogleSteps.i_click_on_goformatter.result({
  "duraformatter.result({
  "duration": 1141345201,
  "status": "passed"
});
formatter.match({
  "location": "GoogleSteps.i_click_on_google_maps_tab_from_the_list()"
});
formatter.result({
  "duration": 3469446063,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "webpt",
      "offset": 39
    }
  ],
  "location": "GoogleSteps.i_vieformatter.result({
  "duration"formatter.result({
  "duration": 12081067078,
  "error_message": "java.lang.AssertionError: failed due to:  no such element: Unable to locate element: {\"method\":\"id\",\"selector\":\"searchboxinput4444s\"}\n  (Session info: headless chrome\u003d66.0.3359.139)\n  (Driver info: chromedriver\u003d2.37.544337 (8c0344a12e552148c185f7d5117db1f28d6c9e85),platform\u003dMac OS X 10.12.6 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.11.0\u0027, revision: \u0027e59cfb3\u0027, time: \u00272018-03-11T20:26:55.152Z\u0027\nSystem info: host: \u0027WPTC02T86HNGTFM\u0027, ip: \u0027fe80:0:0:0:4ce:f1f0:98de:b80b%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.12.6\u0027, java.version: \u00271.8.0_161\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, acceptSslCerts: false, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.37.544337 (8c0344a12e5521..., userDataDir: /var/folders/vt/_txsgrj51g9...}, cssSelectorsEnabled: true, databaseEnabled: false, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: MAC, platformName: MAC, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: accept, unhandledPromptBehavior: accept, version: 66.0.3359.139, webStorageEnabled: true}\nSession ID: eedc47e705b69ecf6877f2f2683af547\n*** Element info: {Using\u003did, value\u003dsearchboxinput4444s}\n\tat org.testng.Assert.fail(Assert.java:96)\n\tat customapplications.pageobject.google.GooglePageComponent.searchGoogleMapsField(GooglePageComponent.java:95)\n\tat com.webpt.step.definition.google.GoogleSteps.i_view_the_google_maps(GoogleSteps.java:29)\n\tat ✽.Then I view the google maps page and enter \"webpt\" in the google maps search field(/Users/sayed.maudoudi/Desktop/cucumber-base/src/test/resources/features/google/google.feature:10)\n",
  "status": "failed"
});
formatter.match({
  "location": "GoogleSteps.i_see_the_address_of_webpt()"
});
formatter.result({
  "status": "skipped"
});
});