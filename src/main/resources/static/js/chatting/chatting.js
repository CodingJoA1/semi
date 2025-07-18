$(document).ready(function(){

    const username = "userName";

    $("#disconn").on("click", (e) => {
        disconnect();
    })

    $("#sendButton").on("click", (e) => {
        send();
    });

    const websocket = new WebSocket("ws://localhost:8080/ws/chat");

    websocket.onmessage = onMessage;
    websocket.onopen = onOpen;
    websocket.onclose = onClose;

    function send(){

        let msg = document.getElementById("message");
        websocket.send(username + ":" + msg.value);
        msg.value = '';
    }
/* 
    //채팅창에서 나갔을 때
    function onClose(evt) {
        var str = username + ": 님이 방을 나가셨습니다.";
        websocket.send(str);
    }

    //채팅창에 들어왔을 때
    function onOpen(evt) {
        var str = username + ": 님이 	입장하셨습니다.";
        websocket.send(str);
    } */

    function onMessage(msg) {
        var data = msg.data;
        var sessionId = null;
        //데이터를 보낸 사람
        var message = null;
        var arr = data.split(":");

        var cur_session = username;

        //현재 세션에 로그인 한 사람
        sessionId = arr[0];
        message = arr[1];


            //로그인 한 클라이언트와 타 클라이언트를 분류하기 위함
            if(sessionId == cur_session){
                var str = "<div class='rChat chatting'>";
                str += "<div class='dsib name'>"+sessionId+"</div>";
                str += "<br>&emsp;<div class='dsib content'>"+message+"</div>";
                str += "</div>";
                $("#chat").append(str);
            }
            else{
            	var str = "<div class='lChat chatting'>";
                str += "<div class='dsib name'>"+sessionId+"</div>";
                str += "<br>&emsp;<div class='dsib content'>"+message+"</div>";
                str += "</div>";
                $("#chat").append(str);
            }
        }
    })