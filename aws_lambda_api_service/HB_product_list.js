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
    
    var AWS = require('aws-sdk');
    var docClient = new AWS.DynamoDB.DocumentClient();
    var tableName = "HB_product";
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
            response.body = JSON.stringify
            (
                data
            );
            callback(null, response);
        }
    });
};