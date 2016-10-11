Feature: Manipulating products

  #When we click on Admin we should be able to "Add product".
  #When we click on "Add new" we should go to the "product/form" page.
  #When we set name as "computer" and description as "blowvers" and price as "600" and click on submit button then we should be redirected to the product list and see the computer in the table.
  Scenario Outline: Add a product with success
    Given we are on the product list page
    When we click on the add new button
    Then we should be on the "product/form" page
    When we set name as <NAME>
    And we set description as <DESC>
    And we set the price as <PRICE>
    And we click on submit button at product page
    Then we are on the product list page
    And we see in the product table a product with name <NAME>, description <DESC> and price <PRICE>

    Examples: 
      | NAME       | DESC       | PRICE |
      | "computer" | "blowvers" |   600 |
