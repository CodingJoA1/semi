window.onload = () => {

	
//DOMContentLoaded : HTML 문서가 로드되고 DOM 트리가 완성되었을때
    const buttons = document.querySelectorAll('.board-button');
	const topButtons = document.querySelectorAll('.board-buttons');
    
    buttons.forEach(button => {
        button.addEventListener('mouseover', function(){
            button.style.background = '#a47864';
			button.style.cursor = 'pointer';
        });
        button.addEventListener('mouseout', function(){
            button.style.background = '';
        });
    });
	console.log(topButtons)
	topButtons.forEach(topButton => {
		topButton.addEventListener('mouseover', function(){
			topButton.style.background = '#a47864';
		});
		topButton.addEventListener('mouseout', function(){
			topButton.style.background = '';
		});
	});
	
	document.getElementById('notice-board').addEventListener('click', () => {
		location.href='/board/noticeBoard';
	});
	
	document.getElementById('license-review-board').addEventListener('click', () => {
		location.href='/board/licenseBoard';
	});
	
	document.getElementById('study-board').addEventListener('click', () => {
		location.href='/board/studyBoard';
	});
	
	document.getElementById('guideline-board').addEventListener('click', () => {
		location.href='/board/guidelineBoard';
	});
	
	document.getElementById('bestPost-board').addEventListener('click', () => {
		location.href='/board/bestPostBoard';
	});
	
	const fileArea = document.querySelector('.fileArea');
	document.getElementById('addFile').addEventListener('click', () => {
		const newDiv = document.createElement('div');
		newDiv.innerHTML = '<label class="fileLabel">첨부파일</label> <input type="file" class="fileUrl"/>';
		fileArea.append(newDiv);
	});
}