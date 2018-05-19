'use strict'
exports.handler = (event, context, callback) => 
{
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
    
    var source = JSON.parse(event.body).source;
    var productname = JSON.parse(event.body).productname;
    var time = JSON.parse(event.body).time;
    var price = JSON.parse(event.body).price;
    var ip = JSON.parse(event.body).ip;
    var email = JSON.parse(event.body).email;
    
    var AWS = require('aws-sdk');
    var docClient = new AWS.DynamoDB.DocumentClient();
    var tableName = "HB_browse_data";
    var params = 
    {
        Item:
        {
            "source": source,
            "productname": productname,
            "time": time,
            "price": price,
            "ip": ip,
            "email": email
        },
        TableName: tableName
    };
    console.log("Adding a new item...");
    docClient.put(params, function(err, data) 
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
            response.body = JSON.stringify
            (
                data
            );
            callback(null, response);
        }
    });
};