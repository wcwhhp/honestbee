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
    var new_price = JSON.parse(event.body).price;
    
    var AWS = require('aws-sdk');
    var docClient = new AWS.DynamoDB.DocumentClient();
    var old_price;
    var stock;
    var productname;
    
    getProductByID(AWS, productid, function(result)
    {
        console.log("getNowPrice result: ", result);
        
        if (result.Count == 0) 
        {
            response.statusCode = 404;
            response.body = JSON.stringify
            ({
                "message": "Product was not found."
            });
            callback(null, response);
        }
        else
        {
            productname = result.Items[0].name;
            old_price = result.Items[0].price;
            stock = result.Items[0].stock;
            stock = result.Items[0].stock;
            console.log("productname: ", productname);
            console.log("old price: ", old_price);
            console.log("stock: ", stock);
        }
    
        // update product
        var tableName = "HB_product";
        var params = 
        {
            Key:{
                "id": productid
            },
            UpdateExpression: "set price=:price, last_price=:old_price",
            ExpressionAttributeValues:{
                ":price":new_price,
                ":old_price": old_price
            },
            ReturnValues:"UPDATED_NEW",
            TableName: tableName
        };
        console.log("Updating the item...");
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
                console.log("new_price: " + new_price + "; old_price: " + old_price);
                // price down
                if (new_price < old_price)
                {
                    // send email to subscriber
                    getSubscribeByID(AWS, productid, function(result)
                    {
                        console.log("send email to : ", result.Items[0].subscriber );
                        console.log("send message to : ", result.Items[0].phone );
                        if ( result.Items[0].status == 1 )
                        {
                            sendEmail(AWS, result.Items[0].subscriber, productname, old_price, new_price, stock);
                            sendMessage(AWS, result.Items[0].subscriber, productname, result.Items[0].phone);
                        }
                    })
                }
                
                console.log(data);
                response.body = JSON.stringify
                (
                    data
                );
                callback(null, response);
            }
        });
    })
};

function getProductByID(AWS, productid, callback)
{
    var docClient = new AWS.DynamoDB.DocumentClient();
    var tableName = "HB_product";
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
           console.log(err, err.stack);
           callback(err);
        }
        else
        {
           console.log(data);
           callback(data);
        }
    });
}

function getSubscribeByID(AWS, productid, callback)
{
    console.log("Get subscriber by productid: ", productid);
    var docClient = new AWS.DynamoDB.DocumentClient();
    var tableName = "HB_product_subscribe";
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
           console.log(err, err.stack);
           callback(err);
        }
        else
        {
           console.log(data);
           callback(data);
        }
    });
}

function sendEmail(AWS, receiver, productname, oldprice, newprice, stock)
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
        // Data: "<html><body><div style='background:#eeeeee'><table align='center' cellpadding='10' style='background:#ffffff; width:60%'><tr><td>	The product(" + productname + ") you are looking for has a best price now($" + oldprice + " -> $" + newprice + "), stock quantity is " + stock + ". Buy now!</td></tr></table></div></body></html>"
         Data: "The product(" + productname + ") you are looking for has a best price now($" + oldprice + " -> $" + newprice + "), stock quantity is " + stock + ". Buy now!"
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

function sendMessage(AWS, receiver, productname, phonenumber)
{
    var sns = new AWS.SNS({"region": "us-east-1"});
    var params = {
      Message: 'The product(' + productname + ') you are looking for has a best price now!',
      PhoneNumber: phonenumber,
      Subject: 'process.env.email_subject'
    };
    sns.publish(params, function (err, data) {
      if (err) console.log(err, err.stack); // an error occurred
      else     console.log(data);           // successful response
    });
}

