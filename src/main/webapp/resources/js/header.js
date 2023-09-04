window.addEventListener('scroll', function() {
    let nav = document.getElementById('top'); // 네비게이션 바의 ID를 가져옵니다.
    var scrollPosition = window.pageYOffset; // 스크롤 위치를 가져옵니다.
    var opacity;
    if (scrollPosition < window.innerHeight) {
      opacity = scrollPosition / window.innerHeight; // 스크롤 위치에 따라 투명도를 계산합니다.
    } else {
      opacity = 1; // 100vh를 넘어가면 투명도를 1로 설정합니다.
    }
    nav.style.backgroundColor = 'rgba(11, 23, 39, ' + opacity + ')'; // 네비게이션 바의 배경색을 설정합니다.
});