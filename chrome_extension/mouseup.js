let key_x, key_z, item, price;
let listener = function(event) {
    // 商品(item)
    
    if (key_z)
    {
        item = getSelectionText();
        console.log("商品", item);
        // 顯示：儲存商品名稱(pop up)
    }
    // 價錢(price)
    else if (key_x)
    {
        price = getSelectionText();

        console.log("價錢", price);
        let getUrl = location.href;
        console.log("getUrl", getUrl);
        let getIp, itemId, beePrice, map;
        $.getJSON('https://ipapi.co/json/')
            .then((data) =>
            {
                getIp = data.ip;
                console.log(`client ip: ${data.ip}`);
                return $.getJSON("https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/product/list");
            })
            .then(data =>
            {
                for (let i = 0; i < data.Items.length; i++)
                {
                    if (item == data.Items[i].name)
                    {
                        itemId = data.Items[i].id;
                        break;
                    }
                    // console.log(data.Items[i].name);
                }
                console.log('itemId', itemId);
                return $.getJSON(`https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/product/${itemId}/detail`);
            })
            .then(data =>
            {
                beePrice = data.Items[0].price;
                console.log(`honestbee的${item}價格: ${beePrice}`);
                return $.ajax({
                    type: "POST",
                    url: 'https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/product/add/competitor',
                    data: JSON.stringify({
                        "id": itemId, // product id
                        "source": getUrl, 
                        "price": price
                    }),
                    dataType: "json",
                    contentType: 'application/json',
                })
                // return $.post('https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/product/add/competitor', {
                //     "id": itemId, // product id
                //     "source": getUrl, 
                //     "price": price
                // })
            })
            .then(data => 
            {
                // send price
                console.log(`product/add/competitor response: ${data}`);
                let lat = 22.00;
                let lon = 13.00;
                return [lat, lon];
            })
            .then(data => 
            {
                console.log(data);
                return $.getJSON(`https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/marketing/${itemId}?latitude=${data[0]}&longitude=${data[1]}`)
            })
            .then(data => 
            {
                map = data.Items[0].address;
                console.log(`離你最近的地址: ${map}`);

                let text = `<p1>honestbee的${item}價格: $${beePrice}</p1><p>離你最近的店家地址: ${map}</p><br />訂閱此商品以便獲得更多資訊<br />email: <input id="get_email" placeholder="your email address" name="email"><br />phone: <input id="get_phone" placeholder="your phone number" name="phone"><br /><button id="subscribe">SUBSCRIBE</button><button id = "x" style="position: absolute;background: red;color: white;top: -10px;right: -10px;">X</button>`;
                let style = `position: fixed; border: 1px solid #3c3c3c; background-color: #FFE4C4; padding: 15px; top: ${event.clientY}px; left: ${event.clientX}px; z-index: 99999999`
                let popupElement = `<div id="popup-msg" style="${style}">${text}</div>`
                $('body').append(popupElement);
                $(document).on("click", "#x", function(){
                    $('#popup-msg').remove();
                })
                $(document).on("click", "#subscribe", function(){
                    let email = $("#get_email").val();
                    let phone = $("#get_phone").val();
                    console.log(email);
                    console.log(phone);
                    
                    $.ajax({
                        type: "POST",
                        url: 'https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/product/subscribe',
                        data: JSON.stringify({
                            "id": itemId,
                            "subscriber": email, // email type
                            "phone": phone, // phone number
                            "status": "1"
                        }),
                        dataType: "json",
                        contentType: 'application/json',
                    })
                    .then(data => 
                    {
                        console.log(data);
                    })
                });
            })
            .catch(err => 
            {
                console.log(err);
            })


                     
        
        // 丟api計算，顯示結果
        
    }
    
};


document.addEventListener('mouseup', listener);
document.addEventListener('keydown', (e) =>
{
    if (e.keyCode == 88) key_x = true;
    if (e.keyCode == 90) key_z = true;
});
document.addEventListener('keyup', (e) =>
{
    if (e.keyCode == 88) key_x = false;
    if (e.keyCode == 90) key_z = false;
});

function getSelectionText()
{
    let txt = '';
    if (window.getSelection)
    {
        txt = window.getSelection();
    }
    else if (document.getSelection)
    {
        txt = document.getSelection();
    }
    else if (document.selection)
    {
        txt = document.selection.createRange().text;
    }
    else return;
    return txt.toString().trim();
}

