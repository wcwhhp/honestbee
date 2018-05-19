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
    
    var productid = JSON.parse(event.body).id;
    var subscriber = JSON.parse(event.body).subscriber;
    var phone = JSON.parse(event.body).phone;
    var status = JSON.parse(event.body).status;
    
    var AWS = require('aws-sdk');
    var docClient = new AWS.DynamoDB.DocumentClient();
    var tableName = "HB_product_subscribe";
    var params = 
    {
        Item:
        {
            "id": productid,
            "subscriber": subscriber,
            "phone": phone,
            "status": status
        },
        TableName: tableName
    };
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