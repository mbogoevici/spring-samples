<script type="text/javascript">
	dojo.require("dojo.data.ItemFileWriteStore");
	dojo.require("dojox.grid.DataGrid");

	var hotels = {};
	
	hotels.grid = null;

	hotels.jsonStore = null;

	hotels.init = function() {
		console.debug("init called");
		
		dojo.connect(dojo.byId("addBtn"),"onclick",hotels,"addHotel");
		
		dojo.xhrGet({
			
			url : "hotels",

			content : {searchString:"", pageSize:500},
					
			handleAs: "json",
					
			headers: {"Accept" : "application/json"},
					
			// The LOAD function will be called on a successful response.
			load: function(response, ioArgs){
				
				console.debug("received response");
				hotels.jsonStore = new dojo.data.ItemFileWriteStore({data : {identity:"id", label:"id", items : response.hotelList}});


				var layout= [
								{ field: "id", width: "20px", name: "ID" },
								{ field: "name", width: "auto", name: "Name" }

							];
						       

							hotels.grid = new dojox.grid.DataGrid({
								query: { name: '*' },
								store: hotels.jsonStore,
								structure: layout,
								rowsPerPage: 500
							}, 'gridNode');
							hotels.grid.startup();
			
			},
			
			// The ERROR function will be called in an error case.
			error: function(response, ioArgs) {
				console.error("Received error from JSON request."+response);
			}
		});
	};

	hotels.addHotel = function() {
		dojo.xhrPost({
			
			url : "hotels/new",

			content : { name : "AAA New Hotel", address : "777 Techwood Drive", 
						city : "Atlanta", state : "GA", zip : "30022", country : "USA", price : 500 },
			
			handleAs: "json",
					
			headers: {"Accept" : "application/json"},

			// The LOAD function will be called on a successful response.
			load: function(response, ioArgs){
				
				console.debug("received response for add request: "+response);

				hotels.jsonStore.newItem(response.hotel);
				
			},
			
			// The ERROR function will be called in an error case.
			error: function(response, ioArgs) {
				console.error("Received error from JSON request."+response);
			}
		});
	};
	
	dojo.addOnLoad(hotels.init);
</script>