window.onload = () =>{
    // board-button클래스를 가지고 있는 태그를 buttons에 담기
    const buttons = document.querySelectorAll('.board-button');
	const topButtons = document.querySelectorAll('.board-buttons');
	
    // select 변수 생성
    let select;

    // 각각 URL마다 select변수에 index와 매칭 시킬 수 있는 숫자 대입
    if(location.href.includes('notice')){
        select = 0;
    } else if(location.href.includes('license')){
        select = 1;
    } else if(location.href.includes('study')){
        select = 2;
    } else if(location.href.includes('guideline')){
        select = 3;
    } else if(location.href.includes('bestPost')){
        select = 4;
    }

    /* for문을 이용하여 buttons의 index와 각각의 URL마다 매칭 시킬 수 있게 만든
    // select와 비교하여 index와 select가 일치하면 배경색을 넣어줌
    // index와 select가 일치하지 않으면 addEventListener이용하여 이벤트 추가
    // 이벤트가 들어온 요소에만 배경색이 바뀌어야 하므로 function()함수를 이용하여
       this 사용 */
    for(let i = 0; i < buttons.length; i++){
        if(i == select){
            buttons[i].style.background = '#a47864';
        } else {
            buttons[i].addEventListener('mouseover', function(){
                this.style.background = '#a47864';
				this.style.cursor = 'pointer';
            });
            buttons[i].addEventListener('mouseout', function(){
                this.style.background = '';
            });
			buttons[i].addEventListener('click', function(){
				const url = buttons[i].getAttribute('data-url');
				location.href = url;
			});
        }
    }
	
	// 상단바에 있는 로그인, 회원가입, 로그아웃, 마이페이지 버튼의 URL이 꼬이는 상황이 발생하여
	// 네임을 따로뒀음
	topButtons.forEach(topButton => {
		console.log(topButton)
		topButton.addEventListener('mouseover', function(){
			topButton.style.background = '#a47864';
		});
		topButton.addEventListener('mouseout', function(){
			topButton.style.background = '';
		});
	});
	
	// 게시글 상세조회 관련 js
	const titles = document.getElementsByClassName('tdPostTitle');
	let postNo = 0;
	for(const title of titles){
		title.addEventListener('mouseover', () => {
			title.style.background = 'rgba(164, 120, 100, 0.5)'
			title.style.color = 'white';
			title.style.cursor = 'pointer';
			title.style.borderRadius = '10px';
		});
		
		title.addEventListener('mouseout', () => {
			title.style.background = '';	
			title.style.color = 'black';
		});
		
		title.addEventListener('click', function(){
			postNo = this.previousElementSibling.previousElementSibling.innerText
//			console.log(postNo)
//			console.log(location.href.substring(location.href.indexOf('page')+5))
			const index = location.href.indexOf('page');
			const page = index == -1 ? 1 : location.href.substring(location.href.indexOf('page')+5);
			location.href='/board/' + postNo + '/' + page;
		});
	}
	
	// 게시글 작성자 관련 js
	const authors = document.getElementsByClassName('tdUserId')
	for(const author of authors){
		author.addEventListener('mouseover', () => {
			author.style.background = 'rgba(164, 120, 100, 0.5)'
			author.style.color = 'white';
			author.style.cursor = 'pointer';
			author.style.borderRadius = '10px';
		});
		
		author.addEventListener('mouseout', () => {
			author.style.background = '';	
			author.style.color = 'black';
		});
		
		author.addEventListener('click', () => {
			location.href='/users/Profile'; 
		});
	}
	
	
	// 게시글 작성 관련 js
	const writeButton = document.getElementById('writeButton');
	writeButton.addEventListener('mouseover', () => {
		writeButton.style.background = '#a47864';
		writeButton.style.cursor = 'pointer';
	});
	writeButton.addEventListener('mouseout', () => {
		writeButton.style.background = '';
	});
	writeButton.addEventListener('click', () => {
		location.href='/board/write';
	});
	
	document.getElementById('postContentToList').addEventListener('click', () => {
		location.href='javascript:history.back()';
	});
	
	
}