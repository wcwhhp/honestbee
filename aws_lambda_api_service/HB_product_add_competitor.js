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
    var source = JSON.parse(event.body).source;
    var price = JSON.parse(event.body).price;
    
    var AWS = require('aws-sdk');
    var docClient = new AWS.DynamoDB.DocumentClient();
    var tableName = "HB_product";
    var now_price;
    getProductByID(AWS, productid, function(result)
    {
         console.log("getProductByID result: ", result);
        
        if (result.Count == 0) 
        {
            response.statusCode = 404;
            response.body = JSON.stringify
            ({
                "message": "Product was not found."
            });
            callback(null, response);
        }
        console.log("result=> ", JSON.stringify(result));
        
        var competitors = result.Items[0].competitor;
        console.log("before competitors=> ", competitors);
        
        competitors.push({"price": price, "source": source});
        console.log("after competitors=> ", competitors);
                
        var now_price = result.Items[0].price;
        if (price < now_price)
        {
            sendEmail(AWS, process.env.manager_email, result.Items[0].name, price, source);
        }
        
        var params = 
        {
            Key:
            {
                "id": productid
            },
            UpdateExpression: "set competitor = :competitor",
            ExpressionAttributeValues:{
                ":competitor": competitors
            },
            ReturnValues:"UPDATED_NEW",
            TableName: tableName
        };
        docClient.update(params, function(err, data) 
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
    });
};

function getProductByID(AWS, productid, callback)
{
    var tableName = "HB_product";
    var docClient = new AWS.DynamoDB.DocumentClient();
    var params = 
    {
        ExpressionAttributeValues: 
        {
            ":id": productid
        }, 
        ExpressionAttributeNames: { "#productid" :"id"},
        KeyConditionExpression: "#productid = :id",
        TableName: tableName
    };
    docClient.query(params, function(err, data) 
    {
        if (err) 
        {
            console.error(err);
            callback(err);
        }
        else
        {
           console.log(data);
           callback(data);
        }
    });
}



function sendEmail(AWS, receiver, productname, price, source)
{
    var ses = new AWS.SES({"region": "us-east-1"});
    var params = {
      Destination: {
       ToAddresses: [
          receiver
       ]
      }, 
      Message: {
       Body: {
        Html: {
         Charset: "UTF-8",
         Data: "The product(" + productname + ") price is $" + price + " from: " + source
        }, 
        Text: {
         Charset: "UTF-8", 
         Data: "This is the message body in text format."
        }
       }, 
       Subject: {
        Charset: "UTF-8", 
        Data: process.env.email_subject
       }
      },
      Source: process.env.email_from_address
    }
    ses.sendEmail(params, function (err, data) {
      if (err) console.log(err, err.stack); // an error occurred
      else     console.log(data);           // successful response
    });
}