Feature: Get appointments for the given times

	@happy
	Scenario: Valid start and end date
		Given the start date: 2017-01-06 and the end date: 2017-01-07
		When I GET the appointments for these dates
		Then the Google server receives a valid GET request for appointments
		And the Outlook server receives a valid GET request for appointments
		And I receive a 200 response with four appointments