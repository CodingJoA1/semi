/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("deleteModal");
    const openBtn = document.getElementById("openDeleteModal");
    const deleteBtn = document.getElementById("delete_user_btn");

    // 모달 열기
    openBtn.addEventListener("click", (e) => {
        e.preventDefault();
        modal.style.display = "flex";
    });

    // 바깥 클릭 시 닫기
    window.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });
	
	// 탈퇴 버튼 클릭
	deleteBtn.addEventListener("click", () => {
	    const pw = document.getElementById("de_password").value;
	    if (!pw) {
	        alert("비밀번호를 입력하세요.");
	        return;
	    }
	});
})