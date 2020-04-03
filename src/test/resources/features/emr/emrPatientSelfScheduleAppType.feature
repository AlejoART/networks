@emrPatientSSappType
  Feature: I should be able to display or hide the new appointment type

    Background: User should be logged in
      Given I navigate to emr login page
      When I enter username 'alejandroalejandro'
      When I enter password 'alejo$2020'
      And I click on login button
      And I click on view schedule link

    @emrPSSinLegend
    Scenario: Should be able to see it under Legend option
      And I click on Legend
      Then I view the Patient Self Schedule type
