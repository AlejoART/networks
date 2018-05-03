$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("/Users/sayed.maudoudi/Desktop/webpt-cucumber-starter/src/test/resources/features/bing/bing.feature");
formatter.feature({
  "line": 1,
  "name": "As a bing user, I want to be able to search for bing map",
  "description": "",
  "id": "as-a-bing-user,-i-want-to-be-able-to-search-for-bing-map",
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
  "name": "I navigate to bing page",
  "keyword": "Given "
});
formatter.match({
  "location": "BingSteps.i_navigate_to_bing_page()"
});
formatter.result({formatter.result({
  "duration": 5071052727,
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
  "duration": 337620243,
  "status": "passed"
});
formatter.match({
  "location": "GoogleSteps.i_click_on_goformatter.result({
  "duration": 10033187474,
  "error_message": "java.lang.AssertionError: failed due to:  no such element: Unable to locate element: {\"method\":\"class name\",\"selector\":\"b_searchboxSubmit22323\"}\n  (Session info: headless chrome\u003d66.0.3359.139)\n  (Driver info: chromedriver\u003d2.37.544337 (8c0344a12e552148c185f7d5117db1f28d6c9e85),platform\u003dMac OS X 10.12.6 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.11.0\u0027, revision: \u0027e59cfb3\u0027, time: \u00272018-03-11T20:26:55.152Z\u0027\nSystem info: host: \u0027WPTC02T86HNGTFM\u0027, ip: \u0027fe80:0:0:0:4ce:f1f0:98de:b80b%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.12.6\u0027, java.version: \u00271.8.0_161\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, acceptSslCerts: false, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.37.544337 (8c0344a12e5521..., userDataDir: /var/folders/vt/_txsgrj51g9...}, cssSelectorsEnabled: true, databaseEnabled: false, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: MAC, platformName: MAC, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: accept, unhandledPromptBehavior: accept, version: 66.0.3359.139, webStorageEnabled: true}\nSession ID: b27631a16f0bb99d86e04e73372d9aa0\n*** Element info: {Using\u003dclass name, value\u003db_searchboxSubmit22323}\n\tat org.testng.Assert.fail(Assert.java:96)\n\tat customapplications.pageobject.bing.BingPageComponent.clickSearchIcon(BingPageComponent.java:74)\n\tat com.webpt.step.definition.google.BingSteps.i_click_on_bing_search_icon(BingSteps.java:22)\n\tat ✽.And I click on bing search icon(/Users/sayed.maudoudi/Desktop/webpt-cucumber-starter/src/test/resources/features/bing/bing.feature:8)\n",
  "status": "failed"
});
formatter.match({
  "location": "BingSteps.i_click_on_bing_maps_tab_from_the_list()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "webpt",
      "offset": 37
    }
  ],
  "location": "BingSteps.i_view_the_bing_maps_page(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "GoogleSteps.i_see_the_address_of_webpt()"
});
formatter.result({
  "status": "skipped"
});
});r.result({
  "status": "skipped"
});
});