Feature: Flight preparation
  Scenario Outline: Flight preparation is initiated
    Given Flight with following data is prepared
      | DepartureAirport   | ArrivalAirport   | 
      | <departureAirport> | <arrivalAirport> | 
    When flight preparation request is made
    Then the flight is actually prepared 
    
	Examples: 
      | departureAirport | arrivalAirport | 
      | LBSF             | LBWN           | 