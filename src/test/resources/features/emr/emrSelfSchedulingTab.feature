@emrSS
  Feature: As an admin user I can access the self scheduling tab with all the available fields

    Background: User should be logged in
      Given I navigate to emr login page
      When I enter username 'alejandroalejandro
      When I enter password 'alejandro$2020'
      And I click on login button
      Then I view the emr dashboard page
      And I click on view schedule link
      And I click on manage calendars link
      And I click on self scheduling tab

    @emrSScalendarSelOnly
    Scenario: Should be able to see only the calendar selection field only
      Then I view the calendar selection field only

    @emrSSdisplayFieldsWhenCalendarSelected
    Scenario: Should be able to see the rest of the fields when a calendar is selected
      And I select a calendar
      Then I view the the other hidden fields

    @emrSScustomizeDefaultTimes
    Scenario: From monday to sunday default times should be from 7am - 6pm and unavailable only saturday and sunday
      And I select a calendar
      And I select Customize in the Scheduling Availability section
      Then I view the default availability times for the week

