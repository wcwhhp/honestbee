// // chrome.runtime.onInstalled.addListener(function()
// // {
// //     chrome.storage.sync.set(
// //     {
// //         color: '#3aa757'
// //     }, function()
// //     {
// //         console.log("The color is green.");
// //     });
// //     chrome.declarativeContent.onPageChanged.removeRules(undefined, function()
// //     {
// //         chrome.declarativeContent.onPageChanged.addRules([
// //         {
// //             conditions: [new chrome.declarativeContent.PageStateMatcher(
// //             {
// //                 pageUrl:
// //                 {
// //                     hostEquals: 'www.google.com.tw'
// //                 },
// //             })],
// //             actions: [new chrome.declarativeContent.ShowPageAction()]
// //         }]);
// //     });
// // });

// // Set click to false at beginning
// let alreadyClicked = false;
// // Declare a timer variable
// let timer;

// console.log("background page ready");  


// // Add Default Listener provided by chrome.api.*
// chrome.browserAction.onClicked.addListener(function(tab)
// {
//     // Check for previous click
//     if (alreadyClicked)
//     {
//         // Yes, Previous Click Detected

//         // Clear timer already set in earlier Click
//         clearTimeout(timer);
//         console.log("Double click");

//         // Change Icon
//         // chrome.browserAction.setIcon(
//         // {
//         //     "path": "double.png"
//         // }, function()
//         // {
//         //     console.log("Changed Icon for Double Click");
//         // });

//         //Clear all Clicks
//         alreadyClicked = false;
//         return;
//     }

//     //Set Click to  true
//     alreadyClicked = true;

//     //Add a timer to detect next click to a sample of 250
//     timer = setTimeout(function()
//     {
//         // No more clicks so, this is a single click
//         console.log("Single click");

//         // Chane Icon
//         // chrome.browserAction.setIcon(
//         // {
//         //     "path": "single.gif"
//         // }, function()
//         // {
//         //     console.log("Changed Icon for Single Click");
//         // });

//         //Clear all timers
//         clearTimeout(timer);

//         //Ignore clicks
//         alreadyClicked = false;
//     }, 250);
// });


console.log("background page ready");  

// chrome.browserAction.onClicked.addListener(function(tab) {  
//     console.log(tab);  
//     alert("使用者在"+tab.title+ "中點擊了瀏覽器按鈕");  

// });

chrome.tabs.getSelected(null,function(tab) {
    var tablink = tab.url;
    console.log(tablink);
});