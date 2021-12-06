@android
Feature: Test android application

  Scenario: Verify application list dialog on Pixel 4
    Given I have a Pixel_4a device
    When I open app menu
    And I open Alert Dialogs menu
    And I open List dialog menu
    Then I verify Command one is shown

  Scenario: Verify application single choice list on Nexus 6
    Given I have a Nexus_6 device
    When I open app menu
    And I open Alert Dialogs menu
    And I open Single choice list menu
    Then I verify Traffic is shown