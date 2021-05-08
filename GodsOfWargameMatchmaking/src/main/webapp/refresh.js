/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var lobbies;
var tempLink;
document.getElementById("refreshList").onclick = function () {
    
    let tempInt =document.getElementsByTagName("li").length;
    for (i=0;i< tempInt;i++){
        document.getElementsByTagName("li")[0].remove();
    }
    lobbyGet();
};



function lobbyGet(){
    
    let json = fetch(window.location.href + "/lobbyUpdate") //defaults to GET supposedly
            .then( Body => Body.json() )
            .then( data => {
                //console.log(data);
                lobbies = JSON.stringify(data);
                return lobbies;
            } )
            .then(data => {
                //console.log(data);
                let lobbyList = JSON.parse(data);
                //console.log(lobbyList.gameList[0]);
                //console.log(lobbies);
                if(typeof lobbyList !== "undefined"){
                    
                    //TODO remove all the servers then re-add them with below for loop
                    for(i=0;i<lobbyList.lobbies.length;i++){
                        handleLobby(lobbyList.lobbies[i]);
                    }
                }
            });
            //.then( data => console.log(data));//Are these even necessary? I don't believe so.
            //.then(data => console.log(data));
    //console.log(json);
    return json;
    
}
function handleLobby(lobby){//no return
    let tagLI = document.createElement("li");
    //tagLI.class = "containers";
    let tagA = document.createElement("a");
    let urlBuilder = "http://" + lobby.ip + ".lobby-deployment" + ".default" + ".lobby-service" + ".godsofwargame.com" + ":30008";//concat for DNS subname distinction
    let text = document.createTextNode(lobby.ip);
    tagA.appendChild(text);
    tagA.href = urlBuilder;
    tagLI.appendChild(tagA);
    let element = document.getElementById("SERVERS");
    element.appendChild(tagLI);
}