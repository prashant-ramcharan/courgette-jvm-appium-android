@android
Feature: Test android application

  Scenario: Verify application list dialog
    Given I launch the app
    When I open app menu
    And I open Alert Dialogs menu
    And I open List dialog menu
    Then I verify Command one is shown

  Scenario: Verify application single choice list
    Given I launch the app
    When I open app menu
    And I open Alert Dialogs menu
    And I open Single choice list menu
    Then I verify Traffic is shown

  Scenario: Verify application preference
    Given I launch the app
    When I open Preference menu
    And I open Default values menu
    And I open List preference menu
    Then I verify Alpha Option 01 is shown