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
        let getUrl = location.href
        console.log("getUrl", getUrl);
        let getIp, itemId;
        $.getJSON('https://ipapi.co/json/')
        .then((data) => {
            getIp = data.ip;
            console.log(`查水表: ${data.ip}`);
            return $.getJSON("https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/product/list");
        })
        .then(data =>
        {
            for(let i=0; i<data.Items.length; i++)
            {
                if(item == data.Items[i].name)
                {
                    itemId = data.Items[i].id;
                    break;
                }
                // console.log(data.Items[i].name);
            }
            console.log('itemId',itemId);
            return $.getJSON(`https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/product/${itemId}/detail`);
        })
        .then(data =>
        {
            console.log(data.Items[0]);
        })
            

            // pop up div
            // let text = `<p>test1</p><p>test2</p><p>test3</p><button id="close_btn">關掉啦</button>`;
            // let style = `position: fixed; border: 1px solid #3c3c3c; background-color: #FFE4C4; padding: 15px; top: ${event.clientY}px; left: ${event.clientX}px`
            // let popupElement = `<div id="popup-msg" style="${style}">${text}</div>`
            // $('body').parent().append(popupElement);
            // $(document).on("click", "#close_btn", function(){
            //     $('#popup-msg').remove();
            // })
            
        
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

