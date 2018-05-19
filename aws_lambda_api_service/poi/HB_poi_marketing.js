
let geolib = require('geolib');
exports.handler = (event, context, callback) => {
  
    console.log('Received input:', JSON.stringify(event, null, 2));

    // Initial HTTP response
    let response =
    {
      statusCode: 200,
      headers:
      {
          'Access-Control-Allow-Origin': '*'
      }
    };
    
    var client_latitude = event.queryStringParameters.latitude;
    var client_longitude = event.queryStringParameters.longitude;
    
    var AWS = require('aws-sdk');
    var docClient = new AWS.DynamoDB.DocumentClient();
    
    var temp_length = 0;
    var min_length = 0;
    var min_poi = "";

    var tableName = "HB_product_poi";
    var params = 
    {
        TableName: tableName
    };
    docClient.scan(params, function(err, data) 
    {
        if (err) 
        {
            console.error(err);
            response.body = JSON.stringify
            ({
                "code": err.statusCode,
                "message": err.message
            });
            callback(null, response);
        }
        else
        {
           console.log(data);
		   
			var poi_item = data.Items;
			for(var i = 0; i < data.Count; i++)
			{					
                temp_length = geolib.getDistance(
                               {latitude: client_latitude, longitude: client_longitude},
                               {latitude: poi_item[i].latitude, longitude: poi_item[i].longitude}
                              );
				console.log("Get temp_length: ", temp_length);
				
				// set initial value
				if (i == 0)
				{				
					console.log("Set initial geo distance info.");    	
                    min_poi = poi_item[i].poi_id;
					min_length = temp_length;
				}
				
				// Compare
                if (temp_length < min_length)
                {
                    min_poi = poi_item[i].poi_id;
					min_length = temp_length;
					console.log("NEW min_poi: ", min_poi, " | NEW min_length: ", min_length);
                }
				console.log("min_poi: ", min_poi, " | min_length: ", min_length);                
            }
			console.log("[Final] min_poi: ", min_poi, " | min_length: ", min_length);      
            
            // Get GPS info and return
            var tableName = "HB_product_poi";
            var params = 
            {
                ExpressionAttributeValues: 
                {
                    ":poi_id": min_poi
                }, 
                ExpressionAttributeNames: { "#poi_id" :"poi_id"},
                KeyConditionExpression: "#poi_id = :poi_id",
                TableName: tableName
            };
            docClient.query(params, function(err, data) 
            {
               if (err) 
                {
                   console.log(err, err.stack);
                   response.body = JSON.stringify
                    ({
                        "code": err.statusCode,
                        "message": err.message
                    });
                    callback(null, response);
                }
                else
                {
                   console.log(data);
                    response.body = JSON.stringify
                    (
                        data
                    );
                    callback(null, response);
                }
            });
        }
    });   
};