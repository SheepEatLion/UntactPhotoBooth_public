/*!
* Start Bootstrap - Freelancer v7.0.3 (https://startbootstrap.com/theme/freelancer)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-freelancer/blob/master/LICENSE)
*/
//
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 72,
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });
	// 이미지 삽입 태그와 버튼 연결
	const browseBtn = document.querySelector('.browse-btn');
	const realInput = document.querySelector('#real-input');
	
	browseBtn.addEventListener('click', ()=>{
		realInput.click();
	});

	// 이미지 미리보기

    // 이미지 미리보기 2
    /*
    $('#real-input').change(selectedFileHandler);

    selectedFileHandler = function(e) {
        var files = event.target.files;
        var htmlStr = '';

        for (var x=0; x<files.length; x++){
            var reader = new FileReader();
            reader.onloadend = function(e) {
                var quote_base64_img = '\'' + e.target.result + '\'';
                htmlStr += '<a href="showImg(' + files[x] + ')">' + files[x].name + '</a><br>';
            };
            reader.readAsDataURL(files[x]);
        }
    }*/

});

// 설정 변수
let width= 640,
    height= 0,
    filter= "none";

// DOM 앨리멘트들
const video = document.querySelector("#video");
const canvas = document.querySelector("#canvas");
const photos = document.querySelector("#photos");
const photoButton = document.querySelector("#take-photo");
const clearButton = document.querySelector("#clear-button");
const photoFilter = document.querySelector("#photo-filter");
console.log(photoFilter);

// 웹캠 권한 받은 후 스트리밍
const startWebcam = () => {
    if(navigator.mediaDevices.getUserMedia){
    navigator.mediaDevices.getUserMedia({video:true})
    .then(stream => {
        //Set video/canvas height
        height = video.videoHeight /(video.videoWidth / width);
        video.setAttribute('width', width);
        video.setAttribute('height', height);
        canvas.setAttribute('width', width);
        canvas.setAttribute('height', height);
        video.srcObject = stream;
    })
    .catch(err => {
        console.log("카메라를 가져오지 못했습니다. ", err);
    });
    }
};

// 포토 필터 변경
photoFilter.addEventListener('change', function(e){
    //어떤 필터가 선택 되었는지 그 값을 가져온다.
    var obj = document.getElementsByName("filter");
    var checked_index = -1;
    for(i = 0; i < obj.length; i++){
        if(obj[i].checked){
            checked_index = i;
            filter = obj[i].value;
        }
    }
    //이렇게 나온 필터의 값을 비디오 스타일에 적용 
    video.style.filter = filter;
    //console.log(filter);
    e.preventDefault();
});

// 캡쳐 버튼
photoButton.addEventListener('click', function(e){
    takePicture();
    e.preventDefault();
}, false);

// 캡쳐 메서드
function takePicture(){
    const context = canvas.getContext('2d');
    height = video.videoHeight /(video.videoWidth / width);
    if(width && height){
        canvas.width = width;
        canvas.height = height;
        //캔버스에 그리기
        context.drawImage(video, 0, 0, width, height); 
        //캔버스로붙 실제 이미지를 가져오기 
        const imgUrl = canvas.toDataURL('image/png');
        //엘리먼트를 만들고 가져온 이미지를 출력하기 
        const img = document.createElement('img');
        img.setAttribute('src', imgUrl);
        img.style.filter = filter;
        //photos Div 안에 추가하기  --> 여기에 추가 말고 저장 기능이 들어와야함. (준영)
        // 저장 기능 포기 --> 어펜드하고 리셋하면서 1개씩 붙게끔 하자.
        if (photos) {
            photos.innerHTML = "";
        }
        photos.appendChild(img)
        
    }
}

// ---- 사진 분석 페이지 ----
// 미리보기 스크립트
function setImage(e) {
    var reader = new FileReader();
    reader.onload = function(e){
        var img = document.createElement("img");
        img.setAttribute("src", e.target.result);
        var container = document.getElementById("imagePreview");
        while(container.hasChildNodes()){
        container.removeChild(container.firstChild);
        }
        document.querySelector("div#imagePreview").appendChild(img);
    };
    reader.readAsDataURL(e.target.files[0]);
}