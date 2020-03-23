@emrSS
  Feature: As an admin user I can access the self scheduling tab with all the available fields

    Background: User should be logged in
      Given I navigate to emr login page
      When I enter username 'alejandroalejandro'
      When I enter password 'alejandro$2020'
      And I click on login button
      Then I view the emr dashboard page
      And I click on view schedule link
      And I click on manage calendars link

    @emrSScalendarSelOnly
    Scenario: Should be able to see only the calendar selection field only
      And I click on self scheduling tab
      Then I view the calendar selection field only

    @emrSSdisplayFieldsWhenCalendarSelected
    Scenario: Should be able to see the rest of the fields when a calendar is selected
      And I click on self scheduling tab
      And I select a calendar
      Then I view the the other hidden fields

    @emrSScustomizeDefaultTimes
    Scenario: From monday to sunday default times should be from 7am - 6pm
      And I click on self scheduling tab
      And I select a calendar
      And I select Customize in the Scheduling Availability section
      Then I view the default availability times for the week

    @emrSSMultipleSSnotAllowed
    Scenario: Should not allow Multiple Self Scheduling Calendars
      And I click on self scheduling tab
      And I type a calendar name 'Les Miles'
      And I select another therapist
      And I click on save button
      Then I view the warning message

    @emrSSDisplayOnlyActiveCalendars
    Scenario: Should only display active calendars
      And I click on Calendar Settings and User permission tab
      And I type a calendar name in calendar settings tab 'Abigail Schumacher - Clinic2'
      And I view that the calendar is active
      And I click on self scheduling tab
      And I type a calendar name 'Abigail Schumacher - Clinic2'
      Then I view that the calendar is displayed

