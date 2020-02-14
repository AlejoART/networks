@emrLogin
  Feature: As an emr user I login in the page

    @emrLoginPage
    Scenario: Should be able to login to emr page
      Given I navigate to emr login page
      When I enter username 'alejandroalejandro
      When I enter password 'alejandro$2020'
      And I click on login button
      Then I view the emr dashboard page

