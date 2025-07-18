/**
 * 
 */
$(document).ready(function() {
	let isIdChecked = false;
	let isNicknameChecked = false;
	let isCheckEmail = false;
	
	
	// 아이디 중복확인
	$("#check_id").click(function() {
		let userId = $("#id").val();
		let msgDiv = $("#idCheckMsg");

		if (userId === "") {
			msgDiv.text("아이디를 입력해주세요.").css("color", "gray");
			isIdChecked = false;
			return;
		}

		$.ajax({
			url: "/users/checkId",
			type: "GET",
			data: { userId: userId },
			success: function(response) {
				if (response === "OK") {
					msgDiv.text("사용 가능한 아이디입니다.").css("color", "green");
					isIdChecked = true;
				} else {
					msgDiv.text("이미 사용 중인 아이디입니다.").css("color", "red");
					isIdChecked = false;
				}
			},
			error: function() {
				msgDiv.text("서버 오류가 발생했습니다.").css("color", "red");
				isIdChecked = false;
			}
		});
	});

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
	
	// 인증번호 전송
		$("#send_email").click(function() {
			const email = $("#email").val().trim();

			if (email === "") {
				alert("이메일을 입력하세요.");
				return;
			}

			$.ajax({
				type: "POST",
				url: "/users/sendCode",
				data: { email: email },
				success: function(response) {
					alert("인증번호가 이메일로 전송되었습니다.");
				},
				error: function() {
					alert("이메일 전송에 실패했습니다.");
				}
			});
		});

		// 인증번호 확인
		$("#email_verify_btn").click(function() {
			const code = $("#email_verify").val().trim();
			
			if (code === "") {
				alert("인증번호를 입력하세요.");
				return;
			}

			$.ajax({
				type: "POST",
				url: "/users/verifyCode",
				data: { code: code },
				success: function(response) {
					alert("이메일 인증 성공!");
					// 인증 성공 여부 저장
					isCheckEmail = true;
					
					$("#email_verify_btn").data("verified", true);
				},
				error: function() {
					alert("인증번호가 올바르지 않습니다.");
					$("#email_verify_btn").data("verified", false);
				}
			});
		});

	// 폼 제출 시에 빈칸 & 중복확인 & 이메일 인증 검사
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

		if (!isIdChecked) {
			alert("아이디 중복확인을 완료해주세요.");
			e.preventDefault();
			return;
		}

		if (!isNicknameChecked) {
			alert("별명 중복확인을 완료해주세요.");
			e.preventDefault();
			return;
		}
		
		if(!isCheckEmail){
			alert("이메일 인증을 완료해주세요.");
			e.preventDefault(); 
			return;
			
		}
	});
});