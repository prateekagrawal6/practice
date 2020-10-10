# Practice

#Resource endpoints:
FE:
 • Index/ Home Page:
	http://localhost:9090/

TravelUi - AF KL Airways 
	You can get fare between Origin and Destination as desired. ( Fares are dynamic )

BE:	
 • Retrieve a confined list of airports:

	 • http://localhost:9090/airports

Query params as provided:

   size: the size of the result
   page: the page to be selected in the paged response
   lang: the language, supported ones are nl and en
   term: A search term that searches through code, name and description.
   
Example : http://localhost:9090/airports?term=RPR        [get Airport details for code RPR]
 
# Search a specific airport:

	• http://localhost:9090/search/code

Query params:

term: A search term that searches through code, name and description.

Example : http://localhost:9090/search/code?term=AMS [get Airport details for term AMS]

# Retrieve a fare details:
 • Retrieve a fare details based on Source and Destination
	
	• http://localhost:9090/fares/{source}/{destination}
Query params

 • currency: the requested resulting currency, supported ones are EUR and USD

	Example : http://localhost:9090/fares/RPR/AMS          [get fare details between RPR and AMS]
			  http://localhost:9090/fares/RPR/AMS?currency=USD  [get fare details between RPR and AMS in USD	

# Statics or metrics about the overall calls involved:
 • Retrieve statics about the calls involved
	
	• http://localhost:9090/rest/metrics 
	
