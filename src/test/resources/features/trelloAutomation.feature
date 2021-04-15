Feature: My store validation
  This feature demonstrates how to validate the checkout product journey
  and also validates personal information update

  @api
  Scenario: Validate that user is able to perform create, update & delete operations using post, get, put, delete endpoint url’s
    Given I'm the valid user calling endpoint "https://api.trello.com/1/boards/" with valid key "89747b547f9016dc4ed8f43898f17364" and valid token "e7e8efc6026f5f3e2592eec99512f9819d02885a93f3ba07260a8b3a56b2a179"
    When I hit the post endpoint with name "kanban board" and desc "A Kanban board is one of the tools that can be used to implement Kanban to manage work at a personal or organizational level"
    Then I should be able to create a new board
    When I hit Get endpoint
    Then I should be able to fetch the details of the created board
    When I hit the put endpoint for updating the desc "Kanban boards visually depict work at various stages of a process"
    Then I should be able to see the updated desc value
    When I hit the delete end point
    Then I should be able to delete the created board

  @api
  Scenario Outline: Validate that post endpoint throws -401Unauthorized error when user tries to provide invalid key while creating a board
    Given I'm the valid user calling endpoint "https://api.trello.com/1/boards/" with valid key "**89747b547f9016dc4ed8f43898f17364" and valid token "e7e8efc6026f5f3e2592eec99512f9819d02885a93f3ba07260a8b3a56b2a179"
    When I hit the post endpoint with name "test4545" and desc "desc name"
    Then I should see "<error>" Status code
    Examples:
      |error |
      | 401  |

  @api
  Scenario Outline: Validate the -not found error is thrown when user hits the get board endpoint with empty ID
    Given I'm the valid user calling endpoint "https://api.trello.com/1/boards/" with valid key "89747b547f9016dc4ed8f43898f17364" and valid token "e7e8efc6026f5f3e2592eec99512f9819d02885a93f3ba07260a8b3a56b2a179"
    When I hit Get endpoint with empty ID ""
    Then I should see "<error>" Status code
    Examples:
      |error |
      | 404  |


  @api
  Scenario Outline: Validate - bad request error is thrown when user hits the get board endpoint with invalid ID
    Given I'm the valid user calling endpoint "https://api.trello.com/1/boards/" with valid key "89747b547f9016dc4ed8f43898f17364" and valid token "e7e8efc6026f5f3e2592eec99512f9819d02885a93f3ba07260a8b3a56b2a179"
    When I hit Get endpoint with invalid ID "dummyid"
    Then I should see "<error>" Status code
    Examples:
      |error |
      | 400  |

  @ui
  Scenario: Validate that user is able to add card to the board
    Given I’m a valid logged in  user to "https://trello.com/login" with "nikhilesh.bangari@gmail.com" and "test1234"
    When I create a new "Kanban board"
    Then I should be able to add a Title and Cards for the stage "To DO,DEV,QA,DONE"
    When I open the added card in "To DO" phase
    Then I should be able to add description "description comments"
    And  I should be able to add members,labels,attachments,cover to the card
    Then I should be move the card to end phase adding comments at each stage
    And I should be able to delete the card
