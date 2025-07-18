/**
 * 
 */

$(document).ready(function() {
    let isNicknameChecked = false;
    let originalNickname = $("#nickname").val();

    // 닉네임 중복확인
    $("#check_nickname").click(function() {
        let nickname = $("#nickname").val();
        let msgDiv = $("#nicknameCheckMsg");

        if (nickname === "") {
            msgDiv.text("별명을 입력해주세요.").css("color", "gray");
            isNicknameChecked = false;
            return;
        }

        $.ajax({
            url: "/users/checkNickname",
            type: "GET",
            data: { userNickname: nickname },
            success: function(response) {
                if (response === "OK") {
                    msgDiv.text("사용 가능한 별명입니다.").css("color", "green");
                    isNicknameChecked = true;
                } else {
                    msgDiv.text("이미 사용 중인 별명입니다.").css("color", "red");
                    isNicknameChecked = false;
                }
            },
            error: function() {
                msgDiv.text("서버 오류가 발생했습니다.").css("color", "red");
                isNicknameChecked = false;
            }
        });
    });

    // 폼 제출 시 빈칸 & 중복확인
    $("form").on("submit", function(e) {
        let isEmpty = false;

        $(this).find("input[type='text'], input[type='password'], input[type='email']").each(function() {
            if ($(this).val().trim() === "") {
                isEmpty = true;
                return false;
            }
        });

        if (isEmpty) {
            alert("모든 항목을 입력해주세요.");
            e.preventDefault();
            return;
        }

        let currentNickname = $("#nickname").val();
        if (currentNickname !== originalNickname && !isNicknameChecked) {
            alert("별명 중복확인을 완료해주세요.");
            e.preventDefault();
            return;
        }
    });
});